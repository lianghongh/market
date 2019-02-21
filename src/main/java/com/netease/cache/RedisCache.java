package com.netease.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {

    private static JedisConnectionFactory factory;
    private String id;
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();


    public RedisCache(String id)
    {
        if (id == null)
            throw new IllegalArgumentException("Cache instances require an ID");
        logger.info("RedisCache id={}",id);
        this.id=id;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        JedisConnection connection = null;
        try {
            connection = factory.getConnection();
            connection.set(serializer.serialize(o), serializer.serialize(o1));
            logger.info("put key: {} value: {}", o, o1);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public Object getObject(Object o) {
        Object result = null;
        JedisConnection connection = null;
        try {
            connection = factory.getConnection();
            result = serializer.deserialize(connection.get(serializer.serialize(o)));
            if (result == null) {
                removeObject(o);
                return null;
            }
            logger.info("{} getObject: {}",id,o);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public Object removeObject(Object o) {
        JedisConnection connection = null;
        Object result = null;
        try {
            connection = factory.getConnection();
            result = connection.expire(serializer.serialize(o), 0);
            logger.info("id: {} remove key: {}", id, o);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public void clear() {
        JedisConnection connection=null;
        try {
            connection=factory.getConnection();
            connection.flushDb();
            connection.flushAll();
            logger.info("Redis FlushAll");
        }catch (JedisConnectionException e){
            e.printStackTrace();
        }finally {
            if(connection!=null)
                connection.close();
        }
    }

    @Override
    public int getSize() {
        int result = 0;
        JedisConnection connection = null;
        try {
            connection = factory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
            logger.info("{} size: {}",id,result);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }

    public static void setFactory(JedisConnectionFactory factory){
        RedisCache.factory=factory;
    }
}

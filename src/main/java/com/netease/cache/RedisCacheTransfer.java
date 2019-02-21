package com.netease.cache;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisCacheTransfer {

    private JedisConnectionFactory factory;

    public JedisConnectionFactory getFactory() {
        return factory;
    }

    public void setFactory(JedisConnectionFactory factory) {
        this.factory = factory;
        RedisCache.setFactory(factory);
    }
}

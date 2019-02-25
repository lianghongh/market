package com.netease.dao;

import com.netease.dao.impl.ImageDaoImpl;
import com.netease.dao.impl.ProductDaoImpl;
import com.netease.domain.Image;
import com.netease.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDaoImpl productDao;

    @Autowired
    @Qualifier("imageDaoImpl")
    private ImageDaoImpl imageDao;

    @Autowired
    private JedisConnectionFactory factory;


    @Test
    public void testGetProductById()
    {
        Product p=productDao.getProductById(23);
        Assert.assertNotNull(p);
        System.out.println(p);
    }

    @Test
    public void testGetProductByName()
    {
        Product p = productDao.getProductByName("点灯牌哦");
        Assert.assertNotNull(p);
        System.out.println(p);
    }

    @Test
    public void testInsertProduct()
    {
        Product p = new Product();
        p.setProductId(777);
        p.setProductName("笔记本电脑mac");
        p.setProductPrice(12345);
        p.setProductDescription("这是一台笔记本电脑，可以上网、玩游戏");
        p.setProductAbstract("Mac笔记本电脑，轻薄续航强");
        List<Image> images = new ArrayList<>();
        Image image = new Image();
        image.setProductId(777);
        image.setUrl("http://www.bilibili.com/");
        image.setName("厉害.jpg");
        images.add(image);
        image=new Image();
        image.setProductId(777);
        image.setName("可以啊.png");
        image.setUrl("http://www.github.com/");
        images.add(image);
        p.setImages(images);
        productDao.insertProduct(p);
        Product q = productDao.getProductById(777);
        System.out.println(q);
    }

    @Test
    public void testDeleteProductById()
    {
        Product d = productDao.getProductById(23);
        productDao.deleteProductById(23);
        Assert.assertNull(productDao.getProductById(23));
        List<Image> images=d.getImages();
        for(int i=0;i<images.size();i++)
            Assert.assertNull(imageDao.getImageById(images.get(i).getId()));
    }

    @Test
    public void testDeleteProductByName()
    {
        Product d = productDao.getProductByName("点灯牌哦");
        productDao.deleteProductByName("点灯牌哦");
        Assert.assertNull(productDao.getProductByName("点灯牌哦"));
        List<Image> images=d.getImages();
        for(int i=0;i<images.size();i++)
            Assert.assertNull(imageDao.getImageById(images.get(i).getId()));
    }

    @Test
    public void testUpdateProduct()
    {
        Product p = productDao.getProductById(23);
        p.setProductPrice(200);
        p.setProductDescription("超级厉害！");
        Image image = new Image();
        image.setProductId(23);
        image.setName("图片.png");
        image.setUrl("qwertyu");
        p.getImages().add(image);
        productDao.updateProduct(p);
        Product q = productDao.getProductById(23);
        System.out.println(q);
    }

    @Test
    public void testAllProducts()
    {
        List<Product> list=productDao.getAllProducts();
        for(Product p:list)
            System.out.println(p);
    }

    @Test
    public void testSerial()
    {
        JedisConnection connection = factory.getConnection();
        Product p = productDao.getProductById(23);
        System.out.println("序列化前："+p);
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
        connection.set(serializer.serialize(p.getProductName()),serializer.serialize(p));
        Object o = serializer.deserialize(connection.get(serializer.serialize(p.getProductName())));
        System.out.println("序列化后："+o);
    }

}

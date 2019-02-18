package com.netease.dao;

import com.netease.dao.impl.ImageDaoImpl;
import com.netease.domain.Image;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ImageDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("imageDaoImpl")
    private ImageDaoImpl imageDao;

    @Test
    public void testSelect()
    {
        Image image = imageDao.getImageById(10);
        Assert.assertNotNull(image);
        Assert.assertEquals(23,image.getProductId());
        Assert.assertEquals("image10.png",image.getName());
        Assert.assertEquals("sdsdsfd", image.getUrl());
    }

    @Test
    public void testInsert()
    {
        Image image=new Image();
        image.setName("lianghong");
        image.setProductId(123134);
        image.setUrl("xxxx");
        imageDao.insertImage(image);
    }

    @Test
    public void testDelete()
    {
        imageDao.deleteImageById(1);
        Assert.assertNull(imageDao.getImageById(1));
    }

    @Test
    public void testUpdate()
    {
        Image image=new Image();
        image.setId(10);
        image.setName("qqq");
        image.setUrl("aaaaaa");
        image.setProductId(564253);
        imageDao.updateImage(image);
        Image p = imageDao.getImageById(10);
        Assert.assertEquals(image.getId(),p.getId());
        Assert.assertEquals(image.getName(),p.getName());
        Assert.assertEquals(image.getProductId(),p.getProductId());
        Assert.assertEquals(image.getUrl(),p.getUrl());
    }
}

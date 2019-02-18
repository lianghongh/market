package com.netease.dao;

import com.netease.dao.impl.ShoppingInfoDaoImpl;
import com.netease.domain.ShoppingInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.util.List;

public class ShoppingInfoDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("shoppingInfoDaoImpl")
    private ShoppingInfoDaoImpl dao;


    @Test
    public void testSelect()
    {
        ShoppingInfo info = dao.getShoppingInfoById(1);
        Assert.assertNotNull(info);
        System.out.println(info);
    }

    @Test
    public void testInsert()
    {
        ShoppingInfo info=new ShoppingInfo();
        info.setProductId(23);
        info.setUserId("zxcvbbn");
        info.setShoppingCount(123);
        info.setShoppingPrice(45.7);
        info.setShoppingTime(Timestamp.valueOf("2019-02-01 10:45:23"));
        dao.insertShoppingInfo(info);
    }

    @Test
    public void testDelete()
    {
        ShoppingInfo info = dao.getShoppingInfoById(1);
        Assert.assertNotNull(info);
        dao.deleteShoppingInfo(info.getId());
        Assert.assertNull(dao.getShoppingInfoById(info.getId()));
    }

    @Test
    public void testList()
    {
        List<ShoppingInfo> infoList = dao.findShoppingInfoById("xxxxxx");
        for(ShoppingInfo info:infoList)
            System.out.println(info);
    }
}

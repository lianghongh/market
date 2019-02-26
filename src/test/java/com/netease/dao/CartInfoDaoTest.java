package com.netease.dao;

import com.netease.dao.impl.CartInfoDaoImpl;
import com.netease.domain.CartInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class CartInfoDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("cartInfoDaoImpl")
    private CartInfoDaoImpl dao;

    @Test
    public void testSelect()
    {
        CartInfo info = dao.getCartInfoById(1);
        Assert.assertNotNull(info);
        System.out.println(info);
    }

    @Test
    public void testInsert()
    {
        CartInfo info=new CartInfo();
        info.setProductId(23);
        info.setProductName("qqq");
        info.setCartCount(100);
        info.setUserId("aaaaaa");
        info.setCartPrice(1000);
        dao.insertCartInfo(info);
    }

    @Test
    public void testDelete()
    {
        CartInfo info = dao.getCartInfoById(1);
        Assert.assertNotNull(info);
        dao.deleteCartInfo(info.getId());
        Assert.assertNull(dao.getCartInfoById(info.getId()));
    }

    @Test
    public void testList()
    {
        List<CartInfo> list = dao.findCartInfoByUserId("xxxxxx");
        for(CartInfo info:list)
            System.out.println(info);

    }

}

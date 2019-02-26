package com.netease.dao;

import com.netease.dao.impl.CartInfoDaoImpl;
import com.netease.dao.impl.ShoppingInfoDaoImpl;
import com.netease.dao.impl.UserDaoImpl;
import com.netease.domain.CartInfo;
import com.netease.domain.ShoppingInfo;
import com.netease.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDaoImpl dao;

    @Autowired
    @Qualifier("shoppingInfoDaoImpl")
    private ShoppingInfoDaoImpl shoppingInfoDao;

    @Autowired
    @Qualifier("cartInfoDaoImpl")
    private CartInfoDaoImpl cartInfoDao;

    @Test
    public void testGetUserById()
    {
        User u = dao.getUserById("ec880b01599c36fe9f9bc56b06ba53bd");
        Assert.assertNotNull(u);
        System.out.println(u);
    }

    @Test
    public void testGetUserByNickname()
    {
        User kk = dao.getUserByNickname("李昂鸿");
        Assert.assertNotNull(kk);
        System.out.println(kk);
    }

    @Test
    public void testInsertUser()
    {
        User u = new User();
        u.setUserId("qwersdfx");
        u.setNickname("利弊");
        u.setBalance(10002);
        u.setPassword("489eb94856614eb50daa8189cc217e79");
        u.setLoginTime(Timestamp.valueOf("2018-02-01 12:23:10"));
        u.setLogoutTime(Timestamp.valueOf("2019-01-10 12:56:00"));
        List<CartInfo> cartInfoList = new ArrayList<>();
        List<ShoppingInfo> shoppingInfoList = new ArrayList<>();
        CartInfo c = new CartInfo();
        c.setUserId("qwersdfx");
        c.setProductId(100);
        c.setCartCount(20);
        c.setCartPrice(30);
        c.setCartTime(Timestamp.valueOf(LocalDateTime.now()));
        cartInfoList.add(c);
        c.setUserId("qwersdfx");
        c.setProductId(4512);
        c.setCartCount(300);
        c.setCartPrice(90);
        c.setCartTime(Timestamp.valueOf(LocalDateTime.now()));
        cartInfoList.add(c);
        ShoppingInfo info = new ShoppingInfo();
        info.setUserId("qwersdfx");
        info.setProductId(111);
        info.setShoppingPrice(109);
        info.setShoppingCount(201);
        info.setShoppingTime(Timestamp.valueOf(LocalDateTime.now()));
        shoppingInfoList.add(info);
        u.setCart(cartInfoList);
        u.setShoppingInfoList(shoppingInfoList);
        dao.insertUser(u);
        User p = dao.getUserById("qwersdfx");
        Assert.assertNotNull(p);
        System.out.println(u);
        System.out.println(p);
    }

    @Test
    public void testUpdatePassword()
    {
        dao.updatePassword("李昂鸿","1234567890");
        User u = dao.getUserByNickname("李昂鸿");
        Assert.assertEquals("1234567890",u.getPassword());
    }

    @Test
    public void testUpdateNickname()
    {
        dao.updateNickname("李昂鸿","威震天");
        Assert.assertNull(dao.getUserByNickname("李昂鸿"));
        Assert.assertNotNull(dao.getUserByNickname("威震天"));
    }

    @Test
    public void testUpdateInfo()
    {
        User u = dao.getUserByNickname("李昂鸿");
        CartInfo info=new CartInfo();
        info.setUserId("xxxxxx");
        info.setProductId(1111);
        info.setCartCount(22);
        info.setCartPrice(33);
        ShoppingInfo info1=new ShoppingInfo();
        info1.setUserId("xxxxxx");
        info1.setProductId(1111);
        info1.setShoppingTime(Timestamp.valueOf(LocalDateTime.now()));
        info1.setShoppingCount(100);
        info1.setShoppingPrice(2000);
        u.getCart().add(info);
        u.getShoppingInfoList().add(info1);

        System.out.println(u);
        dao.updateInfo(u);
        User p = dao.getUserByNickname("李昂鸿");
        Assert.assertNotNull(p);
        System.out.println(p);
    }

    @Test
    public void testDeleteUserByNickname()
    {
        User u = dao.getUserByNickname("李昂鸿");
        dao.deleteUserByNickname("李昂鸿");
        Assert.assertNull(dao.getUserByNickname("李昂鸿"));
        List<ShoppingInfo> shoppingInfoList=u.getShoppingInfoList();
        List<CartInfo> cartInfoList = u.getCart();
        for(ShoppingInfo info:shoppingInfoList)
            Assert.assertNull(shoppingInfoDao.getShoppingInfoById(info.getId()));

        for(CartInfo info:cartInfoList)
            Assert.assertNull(cartInfoDao.getCartInfoById(info.getId()));
    }

    @Test
    public void testDeleteUserById()
    {
        User u = dao.getUserById("ec880b01599c36fe9f9bc56b06ba53bd");
        dao.deleteUserById("ec880b01599c36fe9f9bc56b06ba53bd");
        Assert.assertNull(dao.getUserById("ec880b01599c36fe9f9bc56b06ba53bd"));
        List<ShoppingInfo> shoppingInfoList=u.getShoppingInfoList();
        List<CartInfo> cartInfoList = u.getCart();
        for(ShoppingInfo info:shoppingInfoList)
            Assert.assertNull(shoppingInfoDao.getShoppingInfoById(info.getId()));

        for(CartInfo info:cartInfoList)
            Assert.assertNull(cartInfoDao.getCartInfoById(info.getId()));
    }
}

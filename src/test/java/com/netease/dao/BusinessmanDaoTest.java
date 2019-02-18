package com.netease.dao;

import com.netease.dao.impl.BusinessmanDaoImpl;
import com.netease.dao.impl.InventoryDaoImpl;
import com.netease.domain.Businessman;
import com.netease.domain.Inventory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BusinessmanDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("businessmanDaoImpl")
    private BusinessmanDaoImpl dao;

    @Autowired
    @Qualifier("inventoryDaoImpl")
    private InventoryDaoImpl inventoryDao;


    @Test
    public void testGetBusinessmanByUserId()
    {
        Businessman businessman = dao.getBusinessmanByUserId("cccccc");
        Assert.assertNotNull(businessman);
        System.out.println(businessman);
    }

    @Test
    public void testGetBusinessmanByNickname()
    {
        Businessman businessman = dao.getBusinessmanByNickname("管理员");
        Assert.assertNotNull(businessman);
        System.out.println(businessman);
    }

    @Test
    public void testInsertBusinessman()
    {
        Businessman businessman=new Businessman();
        businessman.setUserId("yyyyyy");
        businessman.setNickname("天下第一");
        businessman.setBalance(1023);
        businessman.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
        businessman.setPassword("asaasddscfds");
        businessman.setLogoutTime(Timestamp.valueOf(LocalDateTime.now()));

        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.setUserId("yyyyyy");
        inventory.setProductId(55);
        inventory.setCount(100);
        inventory.setHasSold(90);
        inventories.add(inventory);
        businessman.setInventoryList(inventories);

        System.out.println(businessman);
        dao.insertBusinessman(businessman);
        System.out.println(dao.getBusinessmanByUserId(businessman.getUserId()));
    }

    @Test
    public void testUpdatePassword()
    {
        dao.updatePassword("管理员","12345");
        Assert.assertEquals("12345",dao.getBusinessmanByNickname("管理员").getPassword());
    }

    @Test
    public void testUpdateNickname()
    {
        dao.updateNickname("管理员","大美女");
        Assert.assertNull(dao.getBusinessmanByNickname("管理员"));
        Assert.assertNotNull(dao.getBusinessmanByNickname("大美女"));
    }

    @Test
    public void testDeleteByNickname()
    {
        Businessman businessman = dao.getBusinessmanByNickname("管理员");
        dao.deleteByNickname("管理员");
        Assert.assertNull(dao.getBusinessmanByNickname("管理员"));
        for(Inventory inventory:businessman.getInventoryList())
            Assert.assertNull(inventoryDao.getInventoryById(inventory.getId()));
    }

    @Test
    public void testDeleteByUserId()
    {
        Businessman businessman = dao.getBusinessmanByUserId("cccccc");
        dao.deleteByUserId("cccccc");
        Assert.assertNull(dao.getBusinessmanByUserId("cccccc"));
        for(Inventory inventory:businessman.getInventoryList())
            Assert.assertNull(inventoryDao.getInventoryById(inventory.getId()));
    }

    @Test
    public void testUpdateInfo()
    {
        Businessman businessman = dao.getBusinessmanByUserId("dddddd");
        Inventory inventory=new Inventory();
        inventory.setUserId("dddddd");
        inventory.setProductId(12);
        inventory.setCount(1000);
        inventory.setHasSold(12);
        businessman.getInventoryList().add(inventory);

        dao.updateInfo(businessman);
        System.out.println(dao.getBusinessmanByUserId(businessman.getUserId()));
    }
}

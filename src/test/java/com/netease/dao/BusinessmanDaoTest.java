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
        Businessman businessman = dao.getBusinessmanByUserId("c913c903b8ad38138b64ca3a187282d2");
        Assert.assertNotNull(businessman);
        System.out.println(businessman);
    }

    @Test
    public void testGetBusinessmanByNickname()
    {
        Businessman businessman = dao.getBusinessmanByNickname("林俊杰");
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
        dao.updatePassword("林俊杰","12345");
        Assert.assertEquals("12345",dao.getBusinessmanByNickname("林俊杰").getPassword());
    }

    @Test
    public void testUpdateNickname()
    {
        dao.updateNickname("林俊杰","大美女");
        Assert.assertNull(dao.getBusinessmanByNickname("林俊杰"));
        Assert.assertNotNull(dao.getBusinessmanByNickname("大美女"));
    }

    @Test
    public void testDeleteByNickname()
    {
        Businessman businessman = dao.getBusinessmanByNickname("林俊杰");
        dao.deleteByNickname("林俊杰");
        Assert.assertNull(dao.getBusinessmanByNickname("林俊杰"));
        for(Inventory inventory:businessman.getInventoryList())
            Assert.assertNull(inventoryDao.getInventoryById(inventory.getId()));
    }

    @Test
    public void testDeleteByUserId()
    {
        Businessman businessman = dao.getBusinessmanByUserId("c913c903b8ad38138b64ca3a187282d2");
        dao.deleteByUserId("c913c903b8ad38138b64ca3a187282d2");
        Assert.assertNull(dao.getBusinessmanByUserId("c913c903b8ad38138b64ca3a187282d2"));
        for(Inventory inventory:businessman.getInventoryList())
            Assert.assertNull(inventoryDao.getInventoryById(inventory.getId()));
    }

    @Test
    public void testUpdateInfo()
    {
        Businessman businessman = dao.getBusinessmanByUserId("c913c903b8ad38138b64ca3a187282d2");
        Inventory inventory=new Inventory();
        inventory.setUserId("c913c903b8ad38138b64ca3a187282d2");
        inventory.setProductId(12);
        inventory.setCount(1000);
        inventory.setHasSold(12);
        businessman.getInventoryList().add(inventory);

        dao.updateInfo(businessman);
        System.out.println(dao.getBusinessmanByUserId(businessman.getUserId()));
    }
}

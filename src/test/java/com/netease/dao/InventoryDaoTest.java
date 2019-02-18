package com.netease.dao;

import com.netease.dao.impl.InventoryDaoImpl;
import com.netease.domain.Inventory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class InventoryDaoTest extends BaseDaoTest {

    @Autowired
    @Qualifier("inventoryDaoImpl")
    private InventoryDaoImpl dao;

    @Test
    public void testSelect()
    {
        Inventory inventory=dao.getInventoryById(1);
        Assert.assertNotNull(inventory);
        System.out.println(inventory);
    }

    @Test
    public void testInsert()
    {
        Inventory inventory=new Inventory();
        inventory.setUserId("qqqqqq");
        inventory.setCount(200);
        inventory.setProductId(354);
        inventory.setHasSold(0);
        dao.insertInventory(inventory);
    }

    @Test
    public void testDelete()
    {
        Inventory inventory = dao.getInventoryById(1);
        Assert.assertNotNull(inventory);
        dao.deleteInventory(inventory.getId());
        Assert.assertNull(dao.getInventoryById(inventory.getId()));
    }

    @Test
    public void testList()
    {
        List<Inventory> list = dao.findInventoryByUserId("xxxxxx");
        for(Inventory inventory:list)
            System.out.println(inventory);
    }
}

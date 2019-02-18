package com.netease.dao.impl;

import com.netease.dao.InventoryDao;
import com.netease.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("inventoryDaoImpl")
public class InventoryDaoImpl implements InventoryDao {

    @Autowired
    @Qualifier("inventoryDao")
    private InventoryDao inventoryDao;


    @Override
    public Inventory getInventoryById(int id) {
        return inventoryDao.getInventoryById(id);
    }

    @Override
    public void insertInventory(Inventory inventory) {
        inventoryDao.insertInventory(inventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        inventoryDao.updateInventory(inventory);
    }

    @Override
    public void deleteInventory(int id) {
        inventoryDao.deleteInventory(id);
    }

    @Override
    public List<Inventory> findInventoryByUserId(String user_id) {
        return inventoryDao.findInventoryByUserId(user_id);
    }
}

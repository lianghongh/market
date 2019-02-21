package com.netease.dao.impl;

import com.netease.dao.InventoryDao;
import com.netease.domain.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("inventoryDaoImpl")
public class InventoryDaoImpl implements InventoryDao {

    private static final Logger logger = LoggerFactory.getLogger(InventoryDaoImpl.class);
    @Autowired
    @Qualifier("inventoryDao")
    private InventoryDao inventoryDao;


    @Override
    public Inventory getInventoryById(int id) {
        logger.debug("id: {}",id);
        return inventoryDao.getInventoryById(id);
    }

    @Override
    public void insertInventory(Inventory inventory) {
        logger.debug("{}",inventory);
        inventoryDao.insertInventory(inventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        logger.debug("{}",inventory);
        inventoryDao.updateInventory(inventory);
    }

    @Override
    public void deleteInventory(int id) {
        logger.debug("id: {}",id);
        inventoryDao.deleteInventory(id);
    }

    @Override
    public List<Inventory> findInventoryByUserId(String user_id) {
        logger.debug("userId: {}",user_id);
        return inventoryDao.findInventoryByUserId(user_id);
    }
}

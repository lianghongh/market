package com.netease.dao.impl;

import com.netease.dao.BusinessmanDao;
import com.netease.domain.Businessman;
import com.netease.domain.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("businessmanDaoImpl")
public class BusinessmanDaoImpl implements BusinessmanDao {
    private static final Logger logger = LoggerFactory.getLogger(BusinessmanDaoImpl.class);

    @Autowired
    @Qualifier("businessmanDao")
    private BusinessmanDao dao;

    @Autowired
    @Qualifier("inventoryDaoImpl")
    private InventoryDaoImpl inventoryDao;


    @Override
    public Businessman getBusinessmanByUserId(String user_id) {
        Businessman businessman=dao.getBusinessmanByUserId(user_id);
        logger.debug("{}",businessman);
        return businessman;
    }

    @Override
    public Businessman getBusinessmanByNickname(String nickname) {
        Businessman businessman=dao.getBusinessmanByNickname(nickname);
        logger.debug("{}",businessman);
        return businessman;
    }

    @Override
    public void insertBusinessman(Businessman businessman) {
        dao.insertBusinessman(businessman);
        logger.debug("{}",businessman);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
            inventoryDao.insertInventory(list.get(i));
    }

    @Override
    public void updatePassword(String nickname, String new_password) {
        dao.updatePassword(nickname,new_password);
        logger.debug("nickname: {} new_password: {}",nickname,new_password);
    }

    @Override
    public void updateNickname(String nickname, String name) {
        dao.updateNickname(nickname,name);
        logger.debug("nickname: {} new_name: {}",nickname,name);
    }

    @Override
    public void updateInfo(Businessman businessman) {
        dao.updateInfo(businessman);
        logger.debug("{}",businessman);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
        {
            inventoryDao.deleteInventory(list.get(i).getId());
            inventoryDao.insertInventory(list.get(i));
        }
    }

    @Override
    public void deleteByNickname(String nickname) {
        Businessman businessman = this.getBusinessmanByNickname(nickname);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
            inventoryDao.deleteInventory(list.get(i).getId());
        dao.deleteByNickname(nickname);
        logger.debug("nickname: {}",nickname);
    }

    @Override
    public void deleteByUserId(String user_id) {
        Businessman businessman = this.getBusinessmanByUserId(user_id);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
            inventoryDao.deleteInventory(list.get(i).getId());
        dao.deleteByUserId(user_id);
        logger.debug("userId: {}",user_id);
    }
}

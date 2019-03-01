package com.netease.dao.impl;

import com.netease.dao.BusinessmanDao;
import com.netease.domain.Businessman;
import com.netease.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("businessmanDaoImpl")
public class BusinessmanDaoImpl implements BusinessmanDao {

    @Autowired
    @Qualifier("businessmanDao")
    private BusinessmanDao dao;

    @Autowired
    @Qualifier("inventoryDaoImpl")
    private InventoryDaoImpl inventoryDao;


    @Override
    public Businessman getBusinessmanByUserId(String user_id) {
        Businessman businessman=dao.getBusinessmanByUserId(user_id);
        return businessman;
    }

    @Override
    public Businessman getBusinessmanByNickname(String nickname) {
        Businessman businessman=dao.getBusinessmanByNickname(nickname);
        return businessman;
    }

    @Override
    public void insertBusinessman(Businessman businessman) {
        dao.insertBusinessman(businessman);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
            inventoryDao.insertInventory(list.get(i));
    }

    @Override
    public void updatePassword(String nickname, String new_password) {
        dao.updatePassword(nickname,new_password);
    }

    @Override
    public void updateNickname(String nickname, String name) {
        dao.updateNickname(nickname,name);
    }

    @Override
    public void updateInfo(Businessman businessman) {
        dao.updateInfo(businessman);
        List<Inventory> oldList = inventoryDao.findInventoryByUserId(businessman.getUserId());
        for(int i=0;i<oldList.size();i++)
            inventoryDao.deleteInventory(oldList.get(i).getId());
        List<Inventory> newList = businessman.getInventoryList();
        for(int i=0;i<newList.size();i++)
            inventoryDao.insertInventory(newList.get(i));
    }

    @Override
    public void deleteByNickname(String nickname) {
        Businessman businessman = this.getBusinessmanByNickname(nickname);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
            inventoryDao.deleteInventoryHard(list.get(i).getId());
        dao.deleteByNickname(nickname);
    }

    @Override
    public void deleteByUserId(String user_id) {
        Businessman businessman = this.getBusinessmanByUserId(user_id);
        List<Inventory> list = businessman.getInventoryList();
        for(int i=0;i<list.size();i++)
            inventoryDao.deleteInventoryHard(list.get(i).getId());
        dao.deleteByUserId(user_id);
    }

    @Override
    public List<Businessman> getAllBusinessmans() {
        return dao.getAllBusinessmans();
    }
}

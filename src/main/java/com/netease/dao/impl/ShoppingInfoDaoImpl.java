package com.netease.dao.impl;

import com.netease.dao.ShoppingInfoDao;
import com.netease.domain.ShoppingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shoppingInfoDaoImpl")
public class ShoppingInfoDaoImpl implements ShoppingInfoDao {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingInfoDaoImpl.class);

    @Autowired
    @Qualifier("shoppingInfoDao")
    private ShoppingInfoDao shoppingInfoDao;


    @Override
    public ShoppingInfo getShoppingInfoById(int id) {
        logger.info("id: {}",id);
        return shoppingInfoDao.getShoppingInfoById(id);
    }

    @Override
    public void insertShoppingInfo(ShoppingInfo shoppingInfo) {
        logger.info("{}",shoppingInfo);
        shoppingInfoDao.insertShoppingInfo(shoppingInfo);
    }

    @Override
    public void deleteShoppingInfo(int id) {
        logger.info("id: {}",id);
        shoppingInfoDao.deleteShoppingInfo(id);
    }

    @Override
    public List<ShoppingInfo> findShoppingInfoById(String user_id) {
        logger.info("userId: {}",user_id);
        return shoppingInfoDao.findShoppingInfoById(user_id);
    }


}

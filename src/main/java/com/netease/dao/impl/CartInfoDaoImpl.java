package com.netease.dao.impl;

import com.netease.dao.CartInfoDao;
import com.netease.domain.CartInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartInfoDaoImpl")
public class CartInfoDaoImpl implements CartInfoDao {

    private static final Logger logger = LoggerFactory.getLogger(CartInfoDaoImpl.class);

    @Autowired
    @Qualifier("cartInfoDao")
    private CartInfoDao dao;


    @Override
    public CartInfo getCartInfoById(int id) {
        logger.debug("id: {}",id);
        return dao.getCartInfoById(id);
    }

    @Override
    public List<CartInfo> findCartInfoByUserId(String user_id) {
        logger.debug("userId: {}",user_id);
        return dao.findCartInfoByUserId(user_id);
    }

    @Override
    public void insertCartInfo(CartInfo info) {
        logger.debug("{}",info);
        dao.insertCartInfo(info);
    }

    @Override
    public void deleteCartInfo(int id) {
        logger.debug("id: {}",id);
        dao.deleteCartInfo(id);
    }
}

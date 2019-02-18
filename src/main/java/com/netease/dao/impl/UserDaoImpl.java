package com.netease.dao.impl;

import com.netease.dao.UserDao;
import com.netease.domain.CartInfo;
import com.netease.domain.ShoppingInfo;
import com.netease.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("userDao")
    private UserDao dao;

    @Autowired
    @Qualifier("shoppingInfoDaoImpl")
    private ShoppingInfoDaoImpl shoppingInfoDao;

    @Autowired
    @Qualifier("cartInfoDaoImpl")
    private CartInfoDaoImpl cartInfoDao;


    @Override
    public User getUserById(String userId) {
        return dao.getUserById(userId);
    }

    @Override
    public User getUserByNickname(String nickname) {
        return dao.getUserByNickname(nickname);
    }

    @Override
    public void insertUser(User u) {
        dao.insertUser(u);
        List<ShoppingInfo> shoppingInfoList = u.getShoppingInfoList();
        List<CartInfo> cartInfoList = u.getCart();
        for(int i=0;i<shoppingInfoList.size();i++)
            shoppingInfoDao.insertShoppingInfo(shoppingInfoList.get(i));
        for(int i=0;i<cartInfoList.size();i++)
            cartInfoDao.insertCartInfo(cartInfoList.get(i));
    }

    @Override
    public void updatePassword(String nickname, String new_password) {
        dao.updatePassword(nickname,new_password);
    }

    @Override
    public void updateNickname(String nickname, String new_name) {
        dao.updateNickname(nickname,new_name);
    }

    @Override
    public void updateInfo(User u) {
        dao.updateInfo(u);
        List<ShoppingInfo> shoppingInfoList=u.getShoppingInfoList();
        List<CartInfo> cartInfoList=u.getCart();
        for(int i=0;i<shoppingInfoList.size();i++)
        {
            shoppingInfoDao.deleteShoppingInfo(shoppingInfoList.get(i).getId());
            shoppingInfoDao.insertShoppingInfo(shoppingInfoList.get(i));
        }
        for(int i=0;i<cartInfoList.size();i++)
        {
            cartInfoDao.deleteCartInfo(cartInfoList.get(i).getId());
            cartInfoDao.insertCartInfo(cartInfoList.get(i));
        }
    }

    @Override
    public void deleteUserByNickname(String nickname) {
        User u = this.getUserByNickname(nickname);
        List<ShoppingInfo> shoppingInfoList = u.getShoppingInfoList();
        List<CartInfo> cartInfoList = u.getCart();
        for(int i=0;i<shoppingInfoList.size();i++)
            shoppingInfoDao.deleteShoppingInfo(shoppingInfoList.get(i).getId());
        for(int i=0;i<cartInfoList.size();i++)
            cartInfoDao.deleteCartInfo(cartInfoList.get(i).getId());
        dao.deleteUserByNickname(nickname);
    }

    @Override
    public void deleteUserById(String user_id) {
        User u = this.getUserById(user_id);
        List<ShoppingInfo> shoppingInfoList = u.getShoppingInfoList();
        List<CartInfo> cartInfoList = u.getCart();
        for(int i=0;i<shoppingInfoList.size();i++)
            shoppingInfoDao.deleteShoppingInfo(shoppingInfoList.get(i).getId());
        for(int i=0;i<cartInfoList.size();i++)
            cartInfoDao.deleteCartInfo(cartInfoList.get(i).getId());
        dao.deleteUserById(user_id);
    }
}

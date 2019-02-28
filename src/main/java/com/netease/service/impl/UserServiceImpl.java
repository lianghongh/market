package com.netease.service.impl;

import com.netease.dao.impl.ProductDaoImpl;
import com.netease.dao.impl.UserDaoImpl;
import com.netease.domain.CartInfo;
import com.netease.domain.Product;
import com.netease.domain.ShoppingInfo;
import com.netease.domain.User;
import com.netease.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDaoImpl userDao;

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDaoImpl productDao;


    @Override
    public User getUserByName(String name) {
        return userDao.getUserByNickname(name);
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Transactional
    @Override
    public void addToCart(String name,int productId,int count) {
        User user = userDao.getUserByNickname(name);
        CartInfo info=new CartInfo();
        Product product = productDao.getProductById(productId);
        info.setUserId(user.getUserId());
        info.setProductId(product.getProductId());
        info.setProductName(product.getProductName());
        info.setCartCount(count);
        info.setCartTime(Timestamp.valueOf(LocalDateTime.now()));
        info.setCartPrice(product.getProductPrice());
        user.getCart().add(info);
        logger.info("添加产品{} ID:{}",product.getProductName(),product.getProductId());
        userDao.updateInfo(user);
    }

    @Transactional
    @Override
    public void removeFromCart(String name,int id) {
        User user = userDao.getUserByNickname(name);
        List<CartInfo> result = new ArrayList<>();
        for(CartInfo info:user.getCart()) {
            if(info.getId()!=id)
                result.add(info);
        }
        user.setCart(result);
        userDao.updateInfo(user);
    }

    @Transactional
    @Override
    public boolean purchase(String name) {
        User u = userDao.getUserByNickname(name);
        double total=0;
        for(CartInfo info:u.getCart())
        {
            Product product = productDao.getProductById(info.getProductId());
            total+=product.getProductPrice()*info.getCartCount();
        }
        if(u.getBalance()<total)
        {
            logger.error("用户{}: 您的余额不足！",name);
            return false;
        }
        for(CartInfo info:u.getCart())
        {
            ShoppingInfo shoppingInfo = new ShoppingInfo();
            shoppingInfo.setUserId(u.getUserId());
            shoppingInfo.setProductId(info.getProductId());
            shoppingInfo.setProductName(info.getProductName());
            shoppingInfo.setShoppingCount(info.getCartCount());
            shoppingInfo.setShoppingPrice(productDao.getProductById(info.getProductId()).getProductPrice());
            shoppingInfo.setShoppingTime(Timestamp.valueOf(LocalDateTime.now()));
            u.getShoppingInfoList().add(shoppingInfo);
        }
        u.setCart(new ArrayList<>());
        u.setBalance(u.getBalance()-total);
        userDao.updateInfo(u);
        logger.info("用户{}：购买成功！",name);
        return true;
    }

    @Transactional
    @Override
    public boolean updatePassword(String nickname, String new_password) {
        userDao.updatePassword(nickname,new_password);
        User u = userDao.getUserByNickname(nickname);
        if(u.getPassword().equals(new_password))
        {
            logger.info("用户{}：密码更新成功！",nickname);
            return true;
        }
        logger.error("用户{}：密码更新失败，请重新更新密码！",nickname);
        return false;
    }

    @Transactional
    @Override
    public boolean updateNickname(String nickname, String new_name) {
        userDao.updateNickname(nickname,new_name);
        if (userDao.getUserByNickname(new_name)!=null)
        {
            logger.info("用户{}：更新用户名成功，新用户名{}",nickname,new_name);
            return true;
        }
        logger.error("用户{}：更新用户名失败！",nickname);
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

}

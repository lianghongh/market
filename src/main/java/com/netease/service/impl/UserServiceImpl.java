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
@Transactional(rollbackFor = {Exception.class})
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


    @Override
    public List<Product> getShoppingProducts(String name) {
        User u = userDao.getUserByNickname(name);
        List<ShoppingInfo> shoppingInfoList = u.getShoppingInfoList();
        List<Product> result = new ArrayList<>();
        for(ShoppingInfo info:shoppingInfoList)
            result.add(productDao.getProductById(info.getProductId()));
        return result;
    }

    @Override
    public List<Product> getCartProducts(String name) {
        User u = userDao.getUserByNickname(name);
        List<CartInfo> cartInfoList = u.getCart();
        List<Product> result = new ArrayList<>();
        for(CartInfo info:cartInfoList)
            result.add(productDao.getProductById(info.getProductId()));
        return result;
    }

    @Override
    public void addToCart(String name,String productName,int count) {
        User user = userDao.getUserByNickname(name);
        CartInfo info=new CartInfo();
        Product product = productDao.getProductByName(productName);
        info.setUserId(user.getUserId());
        info.setProductId(product.getProductId());
        info.setCartCount(count);
        info.setCartTime(Timestamp.valueOf(LocalDateTime.now()));
        info.setCartPrice(product.getProductPrice());
        user.getCart().add(info);
        logger.info("添加产品{} ID:{}",product.getProductName(),product.getProductId());
        userDao.updateInfo(user);
    }

    @Override
    public Product removeFromCart(String name,String productName,int count) {
        User user = userDao.getUserByNickname(name);
        Product product = productDao.getProductByName(productName);
        List<CartInfo> result = new ArrayList<>();
        for(CartInfo info:user.getCart()) {
            if(info.getProductId()==product.getProductId())
            {
                if(info.getCartCount()>count)
                {
                    info.setCartCount(info.getCartCount()-count);
                    result.add(info);
                    logger.info("删除商品{} 数量：{}",productName,count);
                }
            }
            else
                result.add(info);
        }
        user.setCart(result);
        userDao.updateInfo(user);
        return product;
    }

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
            shoppingInfo.setShoppingCount(info.getCartCount());
            shoppingInfo.setShoppingPrice(productDao.getProductById(info.getProductId()).getProductPrice());
            shoppingInfo.setShoppingTime(Timestamp.valueOf(LocalDateTime.now()));
            u.getShoppingInfoList().add(shoppingInfo);
        }
        u.setCart(new ArrayList<>());
        userDao.updateInfo(u);
        logger.info("用户{}：购买成功！",name);
        return true;
    }

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
}

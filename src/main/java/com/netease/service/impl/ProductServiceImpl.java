package com.netease.service.impl;

import com.netease.dao.impl.ImageDaoImpl;
import com.netease.dao.impl.ProductDaoImpl;
import com.netease.dao.impl.UserDaoImpl;
import com.netease.domain.Image;
import com.netease.domain.Product;
import com.netease.domain.ShoppingInfo;
import com.netease.domain.User;
import com.netease.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDaoImpl productDao;

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDaoImpl userDao;

    @Autowired
    @Qualifier("imageDaoImpl")
    private ImageDaoImpl imageDao;


    @Override
    public List<Product> listProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProduct(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> notBuy(String username) {
        User user = userDao.getUserByNickname(username);
        List<Product> all=productDao.getAllProducts();
        List<Product> result = new ArrayList<>();
        for(Product p:all)
        {
            boolean add=true;
            for(ShoppingInfo info:user.getShoppingInfoList())
            {
                if(p.getProductId()==info.getProductId())
                {
                    add=false;
                    break;
                }
            }
            if(add)
                result.add(p);
        }
        return result;
    }

    @Override
    public List<Image> findImages(int productId) {
        return imageDao.findImageByProductId(productId);
    }

    @Transactional
    @Override
    public void updateInfo(Product p) {
        productDao.updateProduct(p);
    }


    @Transactional
    @Override
    public void addProduct(Product p) {
        productDao.insertProduct(p);
    }

}

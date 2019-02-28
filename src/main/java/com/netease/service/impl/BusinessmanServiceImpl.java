package com.netease.service.impl;

import com.netease.dao.impl.BusinessmanDaoImpl;
import com.netease.dao.impl.ProductDaoImpl;
import com.netease.domain.Businessman;
import com.netease.domain.Inventory;
import com.netease.domain.Product;
import com.netease.service.BusinessmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("businessmanServiceImpl")
public class BusinessmanServiceImpl implements BusinessmanService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessmanServiceImpl.class);

    @Autowired
    @Qualifier("businessmanDaoImpl")
    private BusinessmanDaoImpl businessmanDao;

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDaoImpl productDao;


    @Override
    public Businessman getBusinessmanByName(String name) {
        return businessmanDao.getBusinessmanByNickname(name);
    }

    @Override
    public Businessman getBusinessmanById(String userId) {
        return businessmanDao.getBusinessmanByUserId(userId);
    }

    @Override
    public List<Product> getInventoryProducts(String name) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        List<Inventory> inventories = businessman.getInventoryList();
        List<Product> result = new ArrayList<>();
        for(Inventory inventory:inventories)
        {
            Product p=productDao.getProductById(inventory.getProductId());
            result.add(p);
        }
        return result;
    }

    @Override
    public void addInventory(String name, Product product,int count) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        Inventory inventory=new Inventory();
        inventory.setUserId(businessman.getUserId());
        inventory.setProductId(product.getProductId());
        inventory.setCount(count);
        inventory.setHasSold(0);
        businessman.getInventoryList().add(inventory);
        logger.info("添加商品{}，添加数量{}",product.getProductName(),count);
    }

    @Override
    public Product removeFromInventory(String name, String productName, int count) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        Product p = productDao.getProductByName(productName);
        List<Inventory> inventoryList=businessman.getInventoryList();
        List<Inventory> r=new ArrayList<>();
        for(Inventory inventory:inventoryList)
        {
            if(inventory.getProductId()==p.getProductId())
            {
                if(inventory.getCount()>count)
                {
                    inventory.setCount(inventory.getCount()-count);
                    r.add(inventory);
                    logger.info("删除商品{}，剩余存量：{}",productName,inventory.getCount());
                }
            }
            else
                r.add(inventory);
        }
        businessman.setInventoryList(r);
        businessmanDao.updateInfo(businessman);
        return p;
    }

    @Override
    public void updateInventory(String name, int productId, Inventory inventory) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        for(Inventory i:businessman.getInventoryList())
        {
            if(i.getProductId()==productId)
            {
                i.setProductId(inventory.getProductId());
                i.setProductName(inventory.getProductName());
                i.setCount(inventory.getCount());
                i.setHasSold(inventory.getHasSold());
                break;
            }
        }
        businessmanDao.updateInfo(businessman);
    }

    @Override
    public boolean updatePassword(String name, String new_password) {
        businessmanDao.updatePassword(name,new_password);
        Businessman b = businessmanDao.getBusinessmanByNickname(name);
        if(b.getPassword().equals(new_password))
        {
            logger.info("商家{}更新密码成功！",name);
            return true;
        }
        logger.error("商家{}更新密码失败！请重新操作！",name);
        return false;
    }

    @Override
    public boolean updateNickname(String name, String new_name) {
        businessmanDao.updateNickname(name,new_name);
        Businessman b = businessmanDao.getBusinessmanByNickname(new_name);
        if(b!=null)
        {
            logger.info("商家{}更新昵称成功！",name);
            return true;
        }
        logger.error("商家{}更新昵称失败！请重新更新",name);
        return false;

    }
}

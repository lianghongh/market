package com.netease.service.impl;

import com.netease.dao.impl.BusinessmanDaoImpl;
import com.netease.dao.impl.InventoryDaoImpl;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    @Qualifier("inventoryDaoImpl")
    private InventoryDaoImpl inventoryDao;


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

    @Transactional
    @Override
    public void addInventory(String name, Product product,int count) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        Inventory inventory=new Inventory();
        inventory.setUserId(businessman.getUserId());
        inventory.setProductId(product.getProductId());
        inventory.setCount(count);
        inventory.setHasSold(0);
        businessman.getInventoryList().add(inventory);
        businessmanDao.updateInfo(businessman);
        logger.info("添加商品{}，添加数量{}",product.getProductName(),count);
    }

    @Transactional
    @Override
    public Product removeFromInventory(String name, int productId) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        Product p = productDao.getProductById(productId);
        List<Inventory> inventoryList=businessman.getInventoryList();
        List<Inventory> r=new ArrayList<>();
        for(Inventory inventory:inventoryList)
        {
            if(inventory.getProductId()!=p.getProductId())
                r.add(inventory);
            else
                inventoryDao.deleteInventoryHard(inventory.getId());
        }
        businessman.setInventoryList(r);
        businessmanDao.updateInfo(businessman);
        return p;
    }

    @Transactional
    @Override
    public void updateInventory(String name, int productId, Inventory inventory) {
        Businessman businessman = businessmanDao.getBusinessmanByNickname(name);
        for(Inventory i:businessman.getInventoryList())
        {
            if(i.getProductId()==productId)
            {
                i.setProductId(inventory.getProductId());
                i.setCount(inventory.getCount());
                i.setHasSold(inventory.getHasSold());
                break;
            }
        }
        businessmanDao.updateInfo(businessman);
    }

    @Transactional
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

    @Transactional
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

    @Override
    public List<Businessman> getAllBusinessmans() {
        return businessmanDao.getAllBusinessmans();
    }
}

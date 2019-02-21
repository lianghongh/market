package com.netease.dao.impl;

import com.netease.dao.ProductDao;
import com.netease.domain.Image;
import com.netease.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Autowired
    @Qualifier("productDao")
    private ProductDao productDao;

    @Autowired
    @Qualifier("imageDaoImpl")
    private ImageDaoImpl imageDaoImpl;


    @Override
    public Product getProductById(int id) {
        logger.debug("id: {}",id);
        return productDao.getProductById(id);
    }

    @Override
    public Product getProductByName(String name) {
        logger.debug("name: {}",name);
        return productDao.getProductByName(name);
    }

    @Override
    public void insertProduct(Product product) {
        productDao.insertProduct(product);
        logger.debug("{}",product);
        List<Image> imageList = product.getImages();
        for(int i=0;i<imageList.size();i++)
            imageDaoImpl.insertImage(imageList.get(i));
    }

    @Override
    public void deleteProductById(int id) {
        Product product = this.getProductById(id);
        logger.debug("id: {}",id);
        List<Image> images = product.getImages();
        for(int i=0;i<images.size();i++)
            imageDaoImpl.deleteImageById(images.get(i).getId());
        productDao.deleteProductById(id);
    }

    @Override
    public void deleteProductByName(String name) {
        Product product = this.getProductByName(name);
        logger.debug("name: ",name);
        List<Image> images = product.getImages();
        for(int i=0;i<images.size();i++)
            imageDaoImpl.deleteImageById(images.get(i).getId());
        productDao.deleteProductByName(name);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
        logger.debug("{}",product);
        List<Image> images=product.getImages();
        for(int i=0;i<images.size();i++)
        {
            imageDaoImpl.deleteImageById(images.get(i).getId());
            imageDaoImpl.insertImage(images.get(i));
        }
    }

}

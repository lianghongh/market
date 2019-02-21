package com.netease.dao.impl;

import com.netease.dao.ImageDao;
import com.netease.domain.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imageDaoImpl")
public class ImageDaoImpl implements ImageDao {

    private static final Logger logger = LoggerFactory.getLogger(ImageDaoImpl.class);

    @Autowired
    @Qualifier("imageDao")
    private ImageDao imageDao;

    @Override
    public Image getImageById(int id) {
        logger.debug("id: {}",id);
        return imageDao.getImageById(id);
    }

    @Override
    public void deleteImageById(int id) {
        logger.debug("id: {}",id);
        imageDao.deleteImageById(id);
    }

    @Override
    public void updateImage(Image image) {
        logger.debug("{}",image);
        imageDao.updateImage(image);
    }

    @Override
    public void insertImage(Image image) {
        logger.debug("{}",image);
        imageDao.insertImage(image);
    }

    @Override
    public List<Image> findImageByProductId(int product_id) {
        logger.debug("productId: {}",product_id);
        return imageDao.findImageByProductId(product_id);
    }
}

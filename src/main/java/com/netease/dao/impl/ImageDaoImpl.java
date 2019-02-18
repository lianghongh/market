package com.netease.dao.impl;

import com.netease.dao.ImageDao;
import com.netease.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imageDaoImpl")
public class ImageDaoImpl implements ImageDao {


    @Autowired
    @Qualifier("imageDao")
    private ImageDao imageDao;

    @Override
    public Image getImageById(int id) {
        return imageDao.getImageById(id);
    }

    @Override
    public void deleteImageById(int id) {
        imageDao.deleteImageById(id);
    }

    @Override
    public void updateImage(Image image) {
        imageDao.updateImage(image);
    }

    @Override
    public void insertImage(Image image) {
        imageDao.insertImage(image);
    }

    @Override
    public List<Image> findImageByProductId(int product_id) {
        return imageDao.findImageByProductId(product_id);
    }
}

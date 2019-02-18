package com.netease.dao;

import com.netease.domain.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imageDao")
public interface ImageDao {

    public Image getImageById(int id);

    public void deleteImageById(int id);

    public void updateImage(Image image);

    public void insertImage(Image image);

    public List<Image> findImageByProductId(int product_id);
}

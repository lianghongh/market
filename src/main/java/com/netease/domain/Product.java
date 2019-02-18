package com.netease.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Product implements Serializable{

    private int productId;
    private String productName;
    private String productAbstract;
    private String productDescription;
    private double productPrice;
    private List<Image> images;
    private Timestamp updateTime;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAbstract() {
        return productAbstract;
    }

    public void setProductAbstract(String productAbstract) {
        this.productAbstract = productAbstract;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Product productId="+productId);
        builder.append(", productName=" + productName);
        builder.append(", productAbstract=" + productAbstract);
        builder.append(", productDescription=" + productDescription);
        builder.append(", productPrice=" + productPrice);
        builder.append(", images=" + images);
        builder.append(", updateTime=" + updateTime+"]");
        return builder.toString();
    }
}

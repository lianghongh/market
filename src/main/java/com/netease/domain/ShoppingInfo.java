package com.netease.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShoppingInfo implements Serializable{

    private int id;
    private String userId;
    private int productId;
    private int shoppingCount;
    private double shoppingPrice;
    private Timestamp shoppingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getShoppingCount() {
        return shoppingCount;
    }

    public void setShoppingCount(int shoppingCount) {
        this.shoppingCount = shoppingCount;
    }

    public double getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(double shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public Timestamp getShoppingTime() {
        return shoppingTime;
    }

    public void setShoppingTime(Timestamp shoppingTime) {
        this.shoppingTime = shoppingTime;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("[ShoppingInfo id=" + id);
        builder.append(", userId=" + userId);
        builder.append(", productId=" + productId);
        builder.append(", shoppingCount=" + shoppingCount);
        builder.append(", shoppingPrice=" + shoppingPrice);
        builder.append(", shoppingTime=" + shoppingTime + "]");
        return builder.toString();
    }
}

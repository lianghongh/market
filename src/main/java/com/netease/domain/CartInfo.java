package com.netease.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class CartInfo implements Serializable {

    private int id;
    private String userId;
    private int productId;
    private int cartCount;
    private double cartPrice;
    private Timestamp cartTime;

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

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(double cartPrice) {
        this.cartPrice = cartPrice;
    }

    public Timestamp getCartTime() {
        return cartTime;
    }

    public void setCartTime(Timestamp cartTime) {
        this.cartTime = cartTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[CartInfo id=" + id);
        builder.append(", userId=" + userId);
        builder.append(", productId=" + productId);
        builder.append(", cartCount=" + cartCount);
        builder.append(", cartPrice=" + cartPrice);
        builder.append(", cartTime=" + cartTime+"]");
        return builder.toString();
    }
}

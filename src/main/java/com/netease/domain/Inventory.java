package com.netease.domain;

import java.io.Serializable;

public class Inventory implements Serializable {

    private int id;
    private String userId;
    private int productId;
    private int count;
    private int hasSold;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHasSold() {
        return hasSold;
    }

    public void setHasSold(int hasSold) {
        this.hasSold = hasSold;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Inventory id="+id);
        builder.append(", userId=" + userId);
        builder.append(", productId=" + productId);
        builder.append(", count=" + count);
        builder.append(", hasSold=" + hasSold+"]");
        return builder.toString();
    }
}

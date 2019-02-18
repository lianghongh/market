package com.netease.domain;

import java.io.Serializable;

public class Image implements Serializable {

    private int id;
    private int productId;
    private String name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Image id="+id);
        builder.append(", productId=" + productId);
        builder.append(", name=" + name);
        builder.append(", url=" + url+"]");
        return builder.toString();
    }
}

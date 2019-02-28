package com.netease.service;

import com.netease.domain.Image;
import com.netease.domain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> listProducts();

    public Product getProduct(int id);

    public List<Product> notBuy(String username);

    public List<Image> findImages(int productId);

    public void updateInfo(Product p);

}

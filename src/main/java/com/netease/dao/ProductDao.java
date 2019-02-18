package com.netease.dao;

import com.netease.domain.Product;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public interface ProductDao {

    public Product getProductById(int id);

    public Product getProductByName(String name);

    public void insertProduct(Product product);

    public void deleteProductById(int id);

    public void deleteProductByName(String name);

    public void updateProduct(Product product);

}

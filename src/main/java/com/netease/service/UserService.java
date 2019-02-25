package com.netease.service;

import com.netease.domain.Product;
import com.netease.domain.User;

import java.util.List;

public interface UserService {

    public User getUserByName(String name);

    public User getUserById(String userId);

    public List<Product> getShoppingProducts(String name);

    public List<Product> getCartProducts(String name);

    public void addToCart(String name,String productName,int count);

    public Product removeFromCart(String name,String productName,int count);

    public boolean purchase(String name);

    public boolean updatePassword(String nickname, String new_password);

    public boolean updateNickname(String nickname, String new_name);


}

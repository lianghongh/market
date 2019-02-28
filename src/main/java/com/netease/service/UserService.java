package com.netease.service;

import com.netease.domain.User;

import java.util.List;

public interface UserService {

    public User getUserByName(String name);

    public User getUserById(String userId);

    public void addToCart(String name,int productId,int count);

    public void removeFromCart(String name,int productId);

    public boolean purchase(String name);

    public boolean updatePassword(String nickname, String new_password);

    public boolean updateNickname(String nickname, String new_name);

    public List<User> getAllUsers();

}

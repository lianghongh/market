package com.netease.service;

import com.netease.domain.Businessman;
import com.netease.domain.Inventory;
import com.netease.domain.Product;

import java.util.List;

public interface BusinessmanService {

    public Businessman getBusinessmanByName(String name);

    public Businessman getBusinessmanById(String userId);

    public List<Product> getInventoryProducts(String name);

    public void addInventory(String name, Product product,int count);

    public Product removeFromInventory(String name, int productId);

    public void updateInventory(String name, int productId,Inventory inventory);

    public boolean updatePassword(String name, String new_password);

    public boolean updateNickname(String name, String new_name);
}

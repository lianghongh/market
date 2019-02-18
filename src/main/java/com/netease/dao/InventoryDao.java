package com.netease.dao;

import com.netease.domain.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("inventoryDao")
public interface InventoryDao {

    public Inventory getInventoryById(int id);

    public void insertInventory(Inventory inventory);

    public void updateInventory(Inventory inventory);

    public void deleteInventory(int id);

    public List<Inventory> findInventoryByUserId(String user_id);


}

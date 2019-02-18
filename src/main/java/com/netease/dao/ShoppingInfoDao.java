package com.netease.dao;

import com.netease.domain.ShoppingInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shoppingInfoDao")
public interface ShoppingInfoDao {

    public ShoppingInfo getShoppingInfoById(int id);

    public void insertShoppingInfo(ShoppingInfo shoppingInfo);

    public void deleteShoppingInfo(int id);

    public List<ShoppingInfo> findShoppingInfoById(String user_id);


}

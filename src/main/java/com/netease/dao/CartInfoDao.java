package com.netease.dao;

import com.netease.domain.CartInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartInfoDao")
public interface CartInfoDao {

    public CartInfo getCartInfoById(int id);

    public List<CartInfo> findCartInfoByUserId(String user_id);

    public void insertCartInfo(CartInfo info);

    public void deleteCartInfo(int id);

}

package com.netease.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Businessman implements Serializable{

    private String userId;
    private String nickname;
    private String password;
    private double balance;
    private List<Inventory> inventoryList;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private Timestamp updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Businessman userId=" + userId);
        builder.append(", nickname=" + nickname);
        builder.append(", password=" + password);
        builder.append(", balance=" + balance);
        builder.append(", inventoryList=" + inventoryList);
        builder.append(", loginTime=" + loginTime);
        builder.append(", logoutTime=" + logoutTime);
        builder.append(", updateTime=" + updateTime+"]");
        return builder.toString();
    }
}

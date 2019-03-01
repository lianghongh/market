package com.netease.dao;

import com.netease.domain.Businessman;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("businessmanDao")
public interface BusinessmanDao {

    public Businessman getBusinessmanByUserId(String user_id);

    public Businessman getBusinessmanByNickname(String nickname);

    public void insertBusinessman(Businessman businessman);

    public void updatePassword(@Param("nickname") String nickname, @Param("password") String new_password);

    public void updateNickname(@Param("nickname") String nickname, @Param("name") String name);

    public void updateInfo(Businessman businessman);

    public void deleteByNickname(String nickname);

    public void deleteByUserId(String user_id);

    public List<Businessman> getAllBusinessmans();

}

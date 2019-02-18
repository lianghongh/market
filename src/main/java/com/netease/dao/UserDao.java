package com.netease.dao;

import com.netease.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {

    public User getUserById(String userId);

    public User getUserByNickname(String nickname);

    public void insertUser(User u);

    public void updatePassword(@Param("nickname") String nickname, @Param("password") String new_password);

    public void updateNickname(@Param("nickname") String nickname, @Param("name") String new_name);

    public void updateInfo(User u);

    public void deleteUserByNickname(String nickname);

    public void deleteUserById(String user_id);


}

package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.UserInfo;

public interface UserInfoDao {
  
	void deleteById(Integer uid);

    void insertUser(UserInfo record);

    List<UserInfo> findUserSelective(UserInfo record);
    
    List<UserInfo> findUserMHSelective(UserInfo record);

    void updateUserByIdSelective(UserInfo record);

    void updateUserById(UserInfo record);
}
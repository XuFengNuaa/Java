package com.nuaa.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nuaa.bean.UserInfo;
import com.nuaa.dao.UserInfoDao;
import com.nuaa.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private final UserInfoDao userInfoDao;  //注入dao层
	
	public UserServiceImpl(UserInfoDao userInfoDao){
			this.userInfoDao = userInfoDao;
	}

	@Override
	public void deleteById(Integer uid) {
		userInfoDao.deleteById(uid);
		
	}

	@Override
	public void insertUser(UserInfo record) {
		userInfoDao.insertUser(record);
		
	}

	@Override
	public List<UserInfo> findUserSelective(UserInfo record) {
		
		return userInfoDao.findUserSelective(record);
	}

	@Override
	public List<UserInfo> findUserMHSelective(UserInfo record) {
		
		return userInfoDao.findUserMHSelective(record);
	}

	@Override
	public void updateUserByIdSelective(UserInfo record) {
		
		userInfoDao.updateUserByIdSelective(record);
		
	}

	@Override
	public void updateUserById(UserInfo record) {

		userInfoDao.updateUserById(record);
		
	}
	
}

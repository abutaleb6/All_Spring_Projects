package com.ibcsprimax.bepza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibcsprimax.bepza.dao.UserDao;
import com.ibcsprimax.bepza.model.UserRole;
import com.ibcsprimax.bepza.model.Users;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Users userList(String username) {
		return this.userDao.findByUserName(username);
	}

	@Override
	public UserRole userRoleList(String username) {
		return this.userDao.roleFindByUserName(username);
	}

}

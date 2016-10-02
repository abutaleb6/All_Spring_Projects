package com.ibcsprimax.bepza.dao;

import com.ibcsprimax.bepza.model.UserRole;
import com.ibcsprimax.bepza.model.Users;

public interface UserDao {

	Users findByUserName(String username);
	
	UserRole roleFindByUserName(String username);

}
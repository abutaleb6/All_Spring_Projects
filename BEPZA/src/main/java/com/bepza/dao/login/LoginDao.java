package com.bepza.dao.login;

import com.bepza.model.login.Users;

public interface LoginDao {
	Users findByUserName(String username);
}

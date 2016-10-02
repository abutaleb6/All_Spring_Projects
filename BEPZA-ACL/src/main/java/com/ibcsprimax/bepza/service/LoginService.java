package com.ibcsprimax.bepza.service;

import com.ibcsprimax.bepza.model.UserRole;
import com.ibcsprimax.bepza.model.Users;

public interface LoginService {
	public Users userList(String username);
	public UserRole userRoleList(String username);
}

package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.model.UserProfile;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findBySso(String sso);	
	
}
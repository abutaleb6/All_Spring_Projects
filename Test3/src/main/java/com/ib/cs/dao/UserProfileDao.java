package com.ib.cs.dao;

import java.util.List;

import com.ib.cs.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}

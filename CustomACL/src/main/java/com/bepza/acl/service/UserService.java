package com.bepza.acl.service;

import java.util.List;

import com.bepza.acl.model.User;

public interface UserService {
	public void addUser(User user);

	public List<User> listUsers();

	public User getUser(String username);

	public void deleteUser(User user);

	public User findById(int id);
}

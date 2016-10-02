package com.bepza.dao.admin;

import java.util.List;

import com.bepza.model.login.Users;

public interface UsersDAO {
	public void addUser(Users user);

	public void updateUser(Users user);

	public List<Users> listUsers();

	public Users getUserById(int id);

	public void removeUser(int id);
}

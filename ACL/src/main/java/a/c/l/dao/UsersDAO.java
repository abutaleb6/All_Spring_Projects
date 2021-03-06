package a.c.l.dao;

import java.util.List;

import a.c.l.model.Users;

public interface UsersDAO {
	public void addUser(Users user);

	public void updateUser(Users user);

	public List<Users> listUsers();

	public Users getUserById(int id);

	public void removeUser(int id);
}

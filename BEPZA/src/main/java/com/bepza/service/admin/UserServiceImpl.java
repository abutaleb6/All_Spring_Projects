package com.bepza.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepza.dao.admin.UsersDAO;
import com.bepza.model.login.Users;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	private UsersDAO usersDao;
	

	public UsersDAO getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	@Transactional
	public void addUser(Users user) {
		this.usersDao.addUser(user);
	}

	@Override
	@Transactional
	public void updateUser(Users user) {
		this.usersDao.updateUser(user);
	}

	@Override
	@Transactional
	public List<Users> listUsers() {		
		return this.usersDao.listUsers();
	}

	@Override
	@Transactional
	public Users getUserById(int id) {		
		return this.usersDao.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.usersDao.removeUser(id);
	}
	
	public String test(){
		return "Test";
	}

}

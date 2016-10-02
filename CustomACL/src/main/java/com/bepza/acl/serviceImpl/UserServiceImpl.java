package com.bepza.acl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepza.acl.dao.UserDao;
import com.bepza.acl.model.User;
import com.bepza.acl.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public List<User> listUsers() {
		return userDao.listUsers();
	}

	public User getUser(String username) {
		return userDao.getUser(username);
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

}

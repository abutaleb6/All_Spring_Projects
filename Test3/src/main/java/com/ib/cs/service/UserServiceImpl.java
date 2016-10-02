package com.ib.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ib.cs.dao.UserDao;
import com.ib.cs.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void save(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}
	
	@Transactional
	public User findById(int id) {
		return dao.findById(id);
	}

	@Transactional
	public User findBySso(String sso) {
		return dao.findBySSO(sso);
	}
	
}

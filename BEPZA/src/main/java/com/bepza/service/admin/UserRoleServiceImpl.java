package com.bepza.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepza.dao.admin.UserRoleDAO;
import com.bepza.model.login.UserRole;


@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	private UserRoleDAO userRoleDao;

	public UserRoleDAO getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDAO userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Override
	@Transactional
	public void addUserRole(UserRole userRole) {
		this.userRoleDao.addUserRole(userRole);
	}

	@Override
	@Transactional
	public void updateUserRole(UserRole userRole) {
		this.userRoleDao.updateUserRole(userRole);
	}

	@Override
	@Transactional
	public List<UserRole> listUserRoles() {		
		return this.userRoleDao.listUserRoles();
	}

	@Override
	@Transactional
	public UserRole getUserRoleById(int id) {
		return this.userRoleDao.getUserRoleById(id);
	}

	@Override
	@Transactional
	public void removeUserRole(int id) {
		this.userRoleDao.removeUserRole(id);
	}
}

package a.c.l.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a.c.l.dao.UserRoleDAO;
import a.c.l.model.UserRoles;

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
	public void addUserRole(UserRoles userRole) {
		this.userRoleDao.addUserRole(userRole);
	}

	@Override
	@Transactional
	public void updateUserRole(UserRoles userRole) {
		this.userRoleDao.updateUserRole(userRole);
	}

	@Override
	@Transactional
	public List<UserRoles> listUserRoles() {		
		return this.userRoleDao.listUserRoles();
	}

	@Override
	@Transactional
	public UserRoles getUserRoleById(int id) {
		return this.userRoleDao.getUserRoleById(id);
	}

	@Override
	@Transactional
	public void removeUserRole(int id) {
		this.userRoleDao.removeUserRole(id);
	}
}

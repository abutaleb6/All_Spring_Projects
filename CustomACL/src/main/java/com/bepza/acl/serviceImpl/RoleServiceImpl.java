package com.bepza.acl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepza.acl.dao.RoleDao;
import com.bepza.acl.model.Role;
import com.bepza.acl.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	public List<Role> listRoles() {
		return roleDao.listRoles();
	}

	public Role getRole(int roleid) {
		return roleDao.getRole(roleid);
	}

	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	public Role findByType(String authority) {
		return roleDao.findByType(authority);
	}

}

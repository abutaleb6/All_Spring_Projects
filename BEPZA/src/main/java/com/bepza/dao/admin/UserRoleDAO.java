package com.bepza.dao.admin;

import java.util.List;

import com.bepza.model.login.UserRole;

public interface UserRoleDAO {
	public void addUserRole(UserRole userRole);

	public void updateUserRole(UserRole userRole);

	public List<UserRole> listUserRoles();

	public UserRole getUserRoleById(int id);

	public void removeUserRole(int id);
}

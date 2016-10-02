package a.c.l.service;

import java.util.List;

import a.c.l.model.UserRoles;

public interface UserRoleService {
	public void addUserRole(UserRoles userRole);
	public void updateUserRole(UserRoles userRole);
	public List<UserRoles> listUserRoles();
	public UserRoles getUserRoleById(int id);
	public void removeUserRole(int id);
}

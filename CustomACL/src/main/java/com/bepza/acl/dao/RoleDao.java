package com.bepza.acl.dao;

import java.util.List;

import com.bepza.acl.model.Role;

public interface RoleDao {
	public void addRole(Role role);

	public List<Role> listRoles();

	public Role getRole(int roleid);

	public void deleteRole(Role role);

	public Role findByType(String type);
}

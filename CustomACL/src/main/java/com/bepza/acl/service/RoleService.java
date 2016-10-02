package com.bepza.acl.service;

import java.util.List;

import com.bepza.acl.model.Role;

public interface RoleService {
	public void addRole(Role role);

	public List<Role> listRoles();

	public Role getRole(int roleid);

	public void deleteRole(Role role);

	public Role findByType(String authority);
}

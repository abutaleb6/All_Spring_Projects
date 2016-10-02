package com.bepza.acl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bepza.acl.model.Role;
import com.bepza.acl.service.RoleService;


@Component
public class RoleToUserProfileConverter implements Converter<Object, Role>{

	@Autowired
	RoleService roleService;

	/*
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Role convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Role role = null;
		role= roleService.getRole(id);
		System.out.println("Profile : "+role);
		return role;
	}
	
	/*
	 * Gets UserProfile by type
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	/*
	public UserProfile convert(Object element) {
		String type = (String)element;
		UserProfile profile= userProfileService.findByType(type);
		System.out.println("Profile ... : "+profile);
		return profile;
	}
	*/

}
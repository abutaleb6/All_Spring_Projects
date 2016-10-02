package com.bepza.acl.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.secure.spi.GrantedPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepza.acl.model.RequestMap;
import com.bepza.acl.model.Role;
import com.bepza.acl.model.User;
import com.bepza.acl.service.UserService;

@Service("aclUserDetailsService")
public class ACLUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId)
			throws UsernameNotFoundException {
		User user = userService.getUser(ssoId);
		System.out.println("User : " + user);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(),
				user.isEnabled() == true, true, true, true,
				getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getUserRole()) {
			System.out.println("UserProfile : " + role);
			authorities.add(new SimpleGrantedAuthority("ROLE_"
					+ role.getAuthority()));
		}
		System.out.print("authorities :" + authorities);
		return authorities;
	}

}

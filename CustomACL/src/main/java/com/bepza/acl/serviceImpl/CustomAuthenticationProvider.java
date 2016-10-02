package com.bepza.acl.serviceImpl;

public class CustomAuthenticationProvider {
	public synchronized String getReturnStringMethod() {
		// get data from database (call your method)
		if (true) {
			return "ROLE_ADMIN";
		}
		else if(false){
			return "ROLE_DBA";
		} else {
			return "ROLE_USER";
		}
	}
	
	public synchronized String getURL() {
		// get data from database (call your method)
		if (getReturnStringMethod().equals("ROLE_ADMIN")) {
			return "/admin/**";
		} 
		else if (getReturnStringMethod().equals("ROLE_DBA")) {
			return "/newUser";
		}
		else {
			return "/";
		}
	}
}

package com.bepza.acl.serviceImpl;

import java.util.Collection;

import org.springframework.security.config.http.FilterInvocationSecurityMetadataSourceParser;

public class CustomSecurityURL extends FilterInvocationSecurityMetadataSourceParser {
	public Collection getAttributes(Object object){
		return null;
	}
	
	public boolean supports(Class clazz) {
		return true;
	}
	
	public Collection getAllConfigAttributes() {
		return null;
	}
}

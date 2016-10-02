package com.bepza.acl.enam;

public enum URLMapType {
	SLASH("/"),
	LOGIN("/login");
	
	String urlMapType;
	
	private URLMapType(String urlMapType){
		this.urlMapType = urlMapType;
	}
	
	public String getUrlMapType(){
		return urlMapType;
	}
	
}

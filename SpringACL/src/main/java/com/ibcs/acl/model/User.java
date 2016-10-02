package com.ibcs.acl.model;


/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 * 
 */

public class User {

	private String userid;
	private String pass;
	private String email;
	private int grpid;
	private int id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrpid() {
		return grpid;
	}

	public void setGrpid(int grpid) {
		this.grpid = grpid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}

package com.ibcs.acl.model;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 * 
 */
public class AuthUser implements java.io.Serializable {

	private int id;

	private String password;

	private String email;

	private String userid;

	private int roleid;

	public AuthUser() {
	}

	public AuthUser(int id) {
		this.id = id;
	}

	public AuthUser(int id, String password, String email, String userid,
			int roleid) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.userid = userid;
		this.roleid = roleid;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
}

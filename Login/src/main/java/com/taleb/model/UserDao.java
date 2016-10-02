package com.taleb.model;

import javax.sql.DataSource;

public interface UserDao {
	public void setDataSource(DataSource ds);
	public User getUser(String name);
}

package com.taleb.model;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserJdbcTemplate implements UserDao {
	 private DataSource dataSource;
	   private JdbcTemplate jdbcTemplateObject;
	   
	  
	
	@Override
	 public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	@Override
	public User getUser(String name) {
		String SQL = "select * from users where user_name = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{name}, new UserMapper());
	      return user;
	}

}

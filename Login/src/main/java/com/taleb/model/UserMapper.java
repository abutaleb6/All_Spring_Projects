package com.taleb.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
	      user.setUsername(rs.getString("user_name"));
	      user.setPassword(rs.getString("user_password"));
	      return user;
	}

}

package com.ibcsPrimax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ibcsPrimax.model.User;

public class TestService {
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	private Map<String, String> sqlQueryMap;
	
	public static String SQL_LOGIN = null;	

	public Map<String, String> getSqlQueryMap() {
		return sqlQueryMap;
	}

	public void setSqlQueryMap(Map<String, String> sqlQueryMap) {
		this.sqlQueryMap = sqlQueryMap;
	}

	public NamedParameterJdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}

	public void setJdbcTemplateObject(
			NamedParameterJdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}

	public String testMethod() {
		return "success";
	}

	public int handleLogin(String userName, String password) throws Exception {

		int userId = -1;

		try {
			Map<String, Object> argsMap = new HashMap<String, Object>();
			argsMap.put("user_name", userName);
			argsMap.put("user_password", password);
			// Map<String, Object> loginResponseMap =
			//Map<String, Object> loginResponseMap = jdbcTemplateObject.queryForMap(SQL_LOGIN, argsMap);
			
			List <Map<String, Object>> loginResponseMap= jdbcTemplateObject.queryForList(SQL_LOGIN, argsMap);
			
			if (loginResponseMap.size() > 0
					&& loginResponseMap.get(0).get("USER_NAME").equals(userName)
					&& loginResponseMap.get(0).get("USER_PASSWORD").equals(password)) {
				userId = Integer.parseInt(loginResponseMap.get(0).get("USER_ID")
						.toString());
			}

			/*
			 * if (userName.equals("test") && password.equals("1234")) { userId
			 * = 1; }
			 */
		} catch (Exception ex) {
			throw new Exception("Error Authentcating user");
		}

		return userId;
	}

	public void init() {
		//SQL_LOGIN = "select user_id, user_name, user_password from users where user_name = :user_name and user_password = :user_password";
		 SQL_LOGIN = sqlQueryMap.get("sql.user.login");
		// "select user_id, user_name, user_password from users where user_name = 'test' and user_password = 'test'";
	}
}

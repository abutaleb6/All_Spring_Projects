package com.websystique.springsecurity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.websystique.springsecurity.model.UserProfile;


public class TestService {
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	private Map<String, String> sqlQueryMap;

	public static String SQL_MST_INS = null;	

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

	public List<UserProfile> handleEmpInsert(String type) throws Exception {
		UserProfile role = new UserProfile();
		role.setType(type);
		List<UserProfile> roleList = new ArrayList<UserProfile>();	
		try {

			// Insert Employee_mst Table
			Map<String, Object> argsMap = new HashMap<String, Object>();
			argsMap.put("type", type);
			
			jdbcTemplateObject.update(SQL_MST_INS, argsMap);	
			
			roleList.add(role);

		} catch (Exception ex) {
			throw new Exception("Error Insert Role");
		}

		return roleList;
	}	
	public void init() {
		SQL_MST_INS = sqlQueryMap.get("sql.emp.mst.ins");		
		 //SQL_MST_INS = "INSERT INTO USER_PROFILE (type)  VALUES ('ABC5')";
	}	
	
}

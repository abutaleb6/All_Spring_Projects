package com.extjs.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.Map;

import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;

import com.google.gson.Gson;

public class CustomerService {
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	private Map<String, String> sqlQueryMap;

	public static String SQL_MST_INS = null;
	public static String SQL_MST_UPDATE = null;
	public static String SQL_MST_ID_SEL = null;

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

	public List<Customer> handleEmpInsert(Customer emp) throws Exception {

		return null;
	}

	public int selectEmployeeId(String empName, String empAddress)
			throws Exception {
		int empId = 0;

		try {
			Map<String, Object> argsMap = new HashMap<String, Object>();
			argsMap.put("emp_name", empName);
			argsMap.put("address", empAddress);

			ResultSetWrappingSqlRowSet rs = (ResultSetWrappingSqlRowSet) jdbcTemplateObject
					.queryForRowSet(SQL_MST_ID_SEL, argsMap);

			ResultSet resultSet = rs.getResultSet();

			List<Customer> employee = new ArrayList<Customer>();
			while (resultSet.next()) {
				empId = resultSet.getInt(1);
			}

			// List<Employee> seblTaskNoteList =
			// JdbcUtils.mapRows(Employee.class, Employee.getRs2BeanMap(),
			// rs.getResultSet());

		} catch (Exception ex) {
			throw new Exception("Error Fatched Employee Id");
		}

		return empId;
	}

	public void init() {
		SQL_MST_INS = sqlQueryMap.get("sql.cus.mst.ins");
		SQL_MST_ID_SEL = sqlQueryMap.get("sql.cus.mst.id.sel");
		SQL_MST_UPDATE = sqlQueryMap.get("sql.cus.mst.update");
	}

	public List<Customer> handleRequest(String actionType, String payload)
			throws Exception {
		List<Customer> customerList = new ArrayList<Customer>();

		if (actionType.equals("SELECT")) {

			Customer cus = null;
			// JSONObject jsonObj = new JSONObject(payload);

			// select notes
			customerList = handleSelect(cus);

			// msgResponse =
			// MessageBuilder.withPayload(seblTaskNoteList).copyHeadersIfAbsent(msgHeader).build();
		} else if (actionType.equals("INSERT")) {

			Gson gson = new Gson();
			Customer cus[] = gson.fromJson(payload, Customer[].class);

			Customer customer = cus[0];

			customerList = handleInsert(customer);
		} else if (actionType.equals("UPDATE")) {

			Gson gson = new Gson();
			Customer cus[] = gson.fromJson(payload, Customer[].class);

			Customer customer = cus[0];

			customerList = handleUpdate(customer);
		}
		return customerList;
	}

	public List<Customer> handleSelect(Customer customer) throws Exception,
			NumberFormatException, SQLException {

		Map<String, Object> argsMap = new HashMap<String, Object>();

		ResultSetWrappingSqlRowSet rs = (ResultSetWrappingSqlRowSet) jdbcTemplateObject
				.queryForRowSet(SQL_MST_ID_SEL, argsMap);

		ResultSet resultSet = rs.getResultSet();

		List<Customer> cust = new ArrayList<Customer>();

		while (resultSet.next()) {
			Customer c = new Customer();
			c.setId(Integer.parseInt(rs.getString("id")));
			c.setName(rs.getString("name"));
			c.setAddress(rs.getString("address"));
			c.setMobile(rs.getString("mobile"));
			cust.add(c);
		}

		return cust;
	}

	public List<Customer> handleInsert(Customer customer) throws Exception,
			NumberFormatException, SQLException {

		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("name", customer.getName());
		argsMap.put("address", customer.getAddress());
		argsMap.put("mobile", customer.getMobile());

		jdbcTemplateObject.update(SQL_MST_INS, argsMap);

		List<Customer> cust = new ArrayList<Customer>();
		cust.add(customer);
		return cust;
	}

	public List<Customer> handleUpdate(Customer customer) throws Exception,
			NumberFormatException, SQLException {

		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("name", customer.getName());
		argsMap.put("address", customer.getAddress());
		argsMap.put("mobile", customer.getMobile());
		argsMap.put("id", customer.getId());

		jdbcTemplateObject.update(SQL_MST_UPDATE, argsMap);

		List<Customer> cust = new ArrayList<Customer>();
		cust.add(customer);
		return cust;
	}

}

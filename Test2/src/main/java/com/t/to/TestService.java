package com.t.to;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;

import com.t.model.Employee;

public class TestService {
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	private Map<String, String> sqlQueryMap;

	public static String SQL_MST_INS = null;
	public static String SQL_EDU_DTL_INS = null;
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

	public List<Employee> handleEmpInsert(Employee emp) throws Exception {
		int empId = 0;
		String empName = emp.getEmpName();
		String empAddress = emp.getEmpAddress();
		List exmNameList = emp.getExmName();
		List exmYearList = emp.getExmYear();
		List sortOrderList = emp.getSortOrder();

		List<Employee> empList = new ArrayList<Employee>();

		// convert language list to String value
		String languages = "";
		List languagesList = emp.getLanguages();
		if (languagesList.size() > 0) {
			for (int i = 0; i < languagesList.size(); i++) {

				if (languagesList.get(i).equals("java")) {
					languages += languagesList.get(i) + ", ";
				}

				if (languagesList.get(i).equals("javaScript")) {
					languages += languagesList.get(i) + ", ";
				}
				if (languagesList.get(i).equals("html")) {
					languages += languagesList.get(i) + ", ";
				}

			}
		}
		languages = languages.trim();
		if (languages.length() > 0
				&& languages.charAt(languages.length() - 1) == ',') {
			languages = languages.substring(0, languages.length() - 1);
		} else {
			languages = "";
		}
		// convert language list to String value end

		// format date for sql
		Date dt = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = formatter.parse(emp.getDob());
		} catch (Exception e) {
		}

		SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");

		String dob = targetDateFormat.format(dt).toString();
		// date format end
		try {

			// Insert Employee_mst Table
			Map<String, Object> argsMap = new HashMap<String, Object>();
			argsMap.put("emp_name", empName);
			argsMap.put("address", empAddress);
			argsMap.put("gender", emp.getGender());
			argsMap.put("dob", dob);
			argsMap.put("languages", languages);
			argsMap.put("designation", emp.getDesignation());

			jdbcTemplateObject.update(SQL_MST_INS, argsMap);
			// Employee_mst Table Insert Completed

			// retrive emp_id which inserted at now
			empId = selectEmployeeId(empName, empAddress);
			// set empId to Employee Object
			emp.setEmpId(empId);
			// Insert Employee_mst Table
			String exmName = "";
			int exmYear = 0, sortOrder = 0;
			Map<String, Object> argsMap1 = new HashMap<String, Object>();
			if (exmNameList.size() > 0) {
				for (int i = 0; i < exmNameList.size(); i++) {
					if (exmNameList.get(i) != null) {
						exmName = exmNameList.get(i).toString();
						exmYear = Integer.parseInt(exmYearList.get(i)
								.toString());
						sortOrder = Integer.parseInt(sortOrderList.get(i)
								.toString());

						argsMap1.put("employee_mst_id", empId);
						argsMap1.put("edu_name", exmName);
						argsMap1.put("passing_year", exmYear);
						argsMap1.put("sort_order", sortOrder);
						if (!exmName.equals("")) {
							jdbcTemplateObject
									.update(SQL_EDU_DTL_INS, argsMap1);
						}
					}
				}
			}
			// Employee_Edu_Dtl Table Insert Completed
			empList.add(emp);

		} catch (Exception ex) {
			throw new Exception("Error Insert Employee");
		}

		return empList;
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

			List<Employee> employee = new ArrayList<Employee>();
			while (resultSet.next()) {
				empId = resultSet.getInt(1);
			}
			 
			// List<Employee> seblTaskNoteList =
			// JdbcUtils.mapRows(Employee.class, Employee.getRs2BeanMap(), rs.getResultSet());

		} catch (Exception ex) {
			throw new Exception("Error Fatched Employee Id");
		}

		return empId;
	}

	public void init() {
		SQL_MST_INS = sqlQueryMap.get("sql.emp.mst.ins");
		SQL_EDU_DTL_INS = sqlQueryMap.get("sql.emp.edu_dtl.ins");
		SQL_MST_ID_SEL = sqlQueryMap.get("sql.emp.mst.id.sel");
	}	
	
}

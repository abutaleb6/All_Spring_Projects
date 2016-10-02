package com.t.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Employee {
	private String empName;
	private String empAddress;
	private String dob;
	private String gender;

	private List<String> exmName;
	private List<Integer> exmYear;
	private List<Integer> sortOrder;

	private String designation;

	private List languages;

	private int empId;

	private static Map<String, String> rs2BeanMap = null;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List getLanguages() {
		return languages;
	}

	public void setLanguages(List languages) {
		this.languages = languages;
	}

	public List getExmName() {
		return exmName;
	}

	public void setExmName(List exmName) {
		this.exmName = exmName;
	}

	public List getExmYear() {
		return exmYear;
	}

	public void setExmYear(List exmYear) {
		this.exmYear = exmYear;
	}

	public List getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(List sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public static final Map<String, String> getRs2BeanMap() {

		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();

			rs2BeanMap.put("id", "empId");
		}

		return rs2BeanMap;
	}

}

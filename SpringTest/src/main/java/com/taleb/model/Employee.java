package com.taleb.model;

import java.util.List;

public class Employee {
	private String empName;
	private String empAddress;
	private String dob;
	private String gender;
	
	private List exmName;
	private List exmYear;
	private List sortOrder;
	
	private String designation;
	
	private List languages;
	
	private int empId;

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
		
}

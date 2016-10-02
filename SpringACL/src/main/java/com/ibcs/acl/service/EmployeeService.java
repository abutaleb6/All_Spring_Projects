package com.ibcs.acl.service;

import java.util.List;

import com.ibcs.acl.model.Employee;

/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
* 
*/
public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int id);
	
	public void deleteEmployee(Employee employee);
}

package com.bepza.mis.cp.service;

import java.util.List;

import com.bepza.mis.cp.model.Employee;

/**
 * @author msaifullah
 *
 */
public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
}

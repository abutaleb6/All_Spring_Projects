package com.spring.hibernate.service;

import java.util.List;

import com.spring.hibernate.model.Employee;

/**
 * @author Dinesh Rajput
 *
 */
public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int id);
	
	public void deleteEmployee(Employee employee);
}

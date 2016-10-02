package com.bepza.mis.cp.dao;

import java.util.List;

import com.bepza.mis.cp.model.Employee;

/**
 * @author msaifullah
 *
 */
public interface EmployeeDao {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
}

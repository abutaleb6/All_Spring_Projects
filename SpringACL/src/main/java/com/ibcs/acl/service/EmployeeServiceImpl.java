package com.ibcs.acl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ibcs.acl.dao.EmployeeDao;
import com.ibcs.acl.model.Employee;

/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
* 
*/
@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeDao employeeDao;	

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
	}
	
	@Transactional
	public List<Employee> listEmployeess() {
		return employeeDao.listEmployeess();
	}

	@Transactional
	public Employee getEmployee(int empid) {
		return employeeDao.getEmployee(empid);
	}
	
	@Transactional
	public void deleteEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee);
	}

}

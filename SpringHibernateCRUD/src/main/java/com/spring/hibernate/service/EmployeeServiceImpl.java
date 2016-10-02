package com.spring.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.hibernate.dao.EmployeeDao;
import com.spring.hibernate.model.Employee;

/**
 * @author Dinesh Rajput
 *
 */
@Service
/*@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Configurable*/
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

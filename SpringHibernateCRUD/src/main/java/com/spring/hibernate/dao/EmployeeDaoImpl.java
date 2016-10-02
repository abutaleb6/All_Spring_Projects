package com.spring.hibernate.dao;

import java.util.List;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.hibernate.model.Employee;

/**
 * @author abu.taleb
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {		
		this.sessionFactory = sessionFactory;
	}

	public void addEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();		
		session.saveOrUpdate(employee);		
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployeess() {		
		Session session = this.sessionFactory.getCurrentSession();	
		return (List<Employee>) session.createCriteria(Employee.class).list();		
	}

	public Employee getEmployee(int empid) {
		Session session = this.sessionFactory.getCurrentSession();	
		return (Employee) session.get(Employee.class, empid);
	}

	public void deleteEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();			
		
		session.createQuery("DELETE FROM Employee WHERE empid ="+employee.getEmpId()).executeUpdate();		
	}

}

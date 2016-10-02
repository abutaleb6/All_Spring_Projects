package com.ibcs.acl.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ibcs.acl.model.Employee;

/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
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

	public Employee getEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Employee) session.get(Employee.class, id);
	}

	public void deleteEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();

		session.createQuery(
				"DELETE FROM Employee WHERE empid =" + employee.getId())
				.executeUpdate();
	}

}

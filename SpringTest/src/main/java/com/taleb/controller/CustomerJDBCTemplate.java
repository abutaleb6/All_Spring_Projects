package com.taleb.controller;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.springframework.jdbc.core.JdbcTemplate;

import com.taleb.model.Customer;
import com.taleb.model.Employee;

public class CustomerJDBCTemplate implements CustomerDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void create(String customername, String address, String email,
			String mobile) {
		String SQL = "insert into customers (cus_name, cus_address, cus_email, cus_mobile) values (?, ?, ?, ?)";
		jdbcTemplate.update(SQL, customername, address, email, mobile);
		return;
	}

	public void empInsert(String empName, String address, String gender,
			String dob, String designation, String languages) {
		String SQL = "insert into employee_mst (emp_name, address, gender, dob, languages, designation) values (?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(SQL, empName, address, gender, dob, languages,
				designation);

	}

	public void empDtlsInsert(int empMstId, List exmNameList, List exmYearList,
			List sortOrderList) {
		String exmName = "", exmYear = "", sortOrder = "";
		String SQL1 = "insert into employee_edu_dtl (employee_mst_id, edu_name, passing_year, sort_order) values (?, ?, ?, ?)";
		for (int i = 0; i < exmNameList.size(); i++) {
			exmName = exmNameList.get(i).toString();
			exmYear = exmYearList.get(i).toString();
			sortOrder = sortOrderList.get(i).toString();
			if (!exmName.equals("")) {
				jdbcTemplate
						.update(SQL1, empMstId, exmName, exmYear, sortOrder);
			}
		}
		// jdbcTemplate.update(SQL1, empMstId, exmName, exmYear, sortOrder);
		return;
	}

	/*
	 * @Override public Customer getCustomers(Integer customerid){ String SQL =
	 * "select customerid from customers where customerid= (select MAX(customerid) from customers )"
	 * ; Customer customer = jdbcTemplate.queryForObject(SQL, new
	 * CustomerMapper()); //Customer customer = jdbcTemplate.queryForObject(SQL,
	 * new Object[]{customerid}, new CustomerMapper()); return customer;
	 * 
	 * }
	 */

	@Override
	public Customer getCustomers(String customername, String address,
			String email, String mobile) {
		// String SQL =
		// "select customerid from customers where customername= 'x' AND caddress='x' AND email='x' AND mobile='x'";
		String SQL = "select cus_id from customers where cus_name= ? AND cus_address=? AND cus_email=? AND cus_mobile=?";
		// Customer customer = jdbcTemplate.queryForObject(SQL, new
		// CustomerMapper());
		Customer customer = jdbcTemplate.queryForObject(SQL, new Object[] {
				customername, address, email, mobile }, new CustomerMapper());
		return customer;

	}

	@Override
	public Customer getCustomers(Integer customerid) {
		// TODO Auto-generated method stub
		return null;
	}

	// View All Customer List
	@Override
	public List<Customer> listCustomers() {
		String SQL = "select * from customers";
		List<Customer> list = jdbcTemplate.query(SQL, new CustomerMapperAll());
		return list;
	}

	// update customer data
	public void update(Integer customerid, String customername, String address,
			String email, String mobile) {
		String SQL = "update customers set cus_name = ?, cus_address=?, cus_email=?, cus_mobile=?  where cus_id = ?";
		jdbcTemplate.update(SQL, customername, address, email, mobile,
				customerid);
		return;
	}

	@Override
	public Employee getEmployee(String name, String address) {
		String SQL = "select id from employee_mst where emp_name= ? AND address=?";
		Employee emp = jdbcTemplate.queryForObject(SQL, new Object[] { name,
				address }, new EmployeeMapper());
		return emp;
	}

}
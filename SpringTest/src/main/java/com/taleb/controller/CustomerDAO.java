package com.taleb.controller;

import java.util.List;

import javax.sql.DataSource;

import com.taleb.model.Customer;
import com.taleb.model.Employee;

public interface CustomerDAO {
	public void setDataSource(DataSource ds);

	public void create(String customername, String address, String email,
			String mobile);

	public Customer getCustomers(Integer customerid);

	public Customer getCustomers(String customername, String address,
			String email, String mobile);

	// View all data of Customer
	public List<Customer> listCustomers();

	// update Customer Data
	public void update(Integer customerid, String customername, String address,
			String email, String mobile);

	public void empInsert(String empname, String address, String gender,
			String dob, String designation, String languages);
	
	public void empDtlsInsert(int empMstId, List exmNameList,
			List exmYearList, List sortOrderList);
	
	public Employee getEmployee(String name, String address);

}
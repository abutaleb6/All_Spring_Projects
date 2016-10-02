package com.taleb.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.taleb.model.Customer;

public class CustomerMapperAll implements RowMapper<Customer> {
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerid(rs.getString("cus_id"));
		customer.setCustomername(rs.getString("cus_name"));
		customer.setAddress(rs.getString("cus_address"));
		customer.setEmail(rs.getString("cus_email"));
		customer.setMobile(rs.getString("cus_mobile"));
		return customer;
	}
}
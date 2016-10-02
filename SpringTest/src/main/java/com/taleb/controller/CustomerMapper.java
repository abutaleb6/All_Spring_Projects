package com.taleb.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.taleb.model.Customer;

public class CustomerMapper implements RowMapper <Customer> {
   public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
      Customer customer= new Customer();
      customer.setCustomerid(rs.getString("cus_id"));
      /* customer.setCustomername(rs.getString("customername"));
      customer.setAddress(rs.getString("address"));
	customer.setEmail(rs.getString("email"));
	customer.setMobile(rs.getString("mobile"));*/
      return customer;
   }
}
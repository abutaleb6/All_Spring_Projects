package com.taleb.model;

public class Customer {

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String customerid;
	public Customer() {
		super();
	}

	public Customer(String customerid, String customername, String address,
			String email, String mobile) {
		super();
		this.customerid = customerid;
		this.customername = customername;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
	}

	private String customername;
	private String address;
	private String email;
	private String mobile;

}

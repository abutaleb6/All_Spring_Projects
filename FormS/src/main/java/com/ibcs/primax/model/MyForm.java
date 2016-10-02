package com.ibcs.primax.model;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;

public class MyForm {

	private String name;
	private String email;
	private String car;
	private String radio;
	private List checkbox;
	
	private String dob;	

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public List getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(List checkbox) {
		this.checkbox = checkbox;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

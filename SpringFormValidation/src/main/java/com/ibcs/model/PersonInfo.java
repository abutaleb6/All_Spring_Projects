package com.ibcs.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class PersonInfo {

	@NotEmpty
	@Size(max = 100)
	private String name;

	@NotNull
	@Size(min = 6, max = 10)
	private String password;

	@Email
	private String email;

	//@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="msg")
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Max(value = 110)
	@Min(value = 18)	
	private Integer age;

	@NotEmpty
	private String dob;

	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Digits(integer = 8, fraction = 2)
	@Max(value = 100000)
	@Min(value = 10000)
	private Double salary;

	@URL
	private String website;

	@Size(min = 11, max = 15)
	private String mobile;
	
	@Size(max = 1000)
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

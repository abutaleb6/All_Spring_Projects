package com.ibcs.acl.model;
/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
* 
*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
* 
*/

@Entity(name = "EMPLOYEE_TEST")
public class Employee {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_Seq")
	@SequenceGenerator(name = "employee_id_Seq", sequenceName = "employee_id_Seq", allocationSize = 1, initialValue = 1)
	Integer id = 0;

	@Column(name = "emp_name")
	String name;

	@Column(name = "emp_address")
	String address;

	@Column(name = "age")
	Integer age;

	@Column(name = "salary")
	Long salary;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	

}

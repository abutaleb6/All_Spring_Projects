package com.websystique.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TALEB", catalog="BEPZA")
public class Taleb {
	
	@Id 
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_ID_Seq")
	@SequenceGenerator(name = "t_ID_Seq", sequenceName = "t_ID_Seq", allocationSize = 1, initialValue = 1)
	@Column(name = "t_ID")
	private Integer id;
	
	@Column(name="name")
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

}

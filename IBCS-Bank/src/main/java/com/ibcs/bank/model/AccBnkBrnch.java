package com.ibcs.bank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AccBnkBrnch")
public class AccBnkBrnch {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_bnk_brnch_id_Seq")
	@SequenceGenerator(name = "acc_bnk_brnch_id_Seq", sequenceName = "acc_bnk_brnch_id_Seq", allocationSize = 1, initialValue = 1)
	@Column(name = "acc_bnk_brnch_id")
	Long id;

	@Column(name = "code")
	String code;

	@Column(name = "title")
	String title;

	@Column(name = "email")
	String email;

	@Column(name = "contactNo")
	String contactNo;

	@Column(name = "contactPrsn")
	String contactPrsn;

	@Column(name = "acc_bnk_id")
	Long accbnkid;

	public Long getAccbnkid() {
		return accbnkid;
	}

	public void setAccbnkid(Long accbnkid) {
		this.accbnkid = accbnkid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactPrsn() {
		return contactPrsn;
	}

	public void setContactPrsn(String contactPrsn) {
		this.contactPrsn = contactPrsn;
	}
}

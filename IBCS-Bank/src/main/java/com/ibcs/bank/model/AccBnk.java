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
@Table(name="AccBnk")
public class AccBnk {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_bnk_id_Seq")
	@SequenceGenerator(name = "acc_bnk_id_Seq", sequenceName = "acc_bnk_id_Seq", allocationSize = 1, initialValue = 1)
	@Column(name = "acc_bnk_id")
	Long id;
	
	@Column(name="code")
	String code;
		
	@Column(name="title")
	String title;
	
	@Column(name="titleBng")
	String titleBng;
	
	@OneToMany(cascade = CascadeType.ALL)
	//@Column(name="acc_bnk_id", nullable = false)
	@JoinColumn(name = "acc_bnk_id")
	List<AccBnkBrnch> accBnkIdAccBnk;
	

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

	public String getTitleBng() {
		return titleBng;
	}

	public void setTitleBng(String titleBng) {
		this.titleBng = titleBng;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AccBnkBrnch> getAccBnkIdAccBnk() {
		return accBnkIdAccBnk;
	}

	public void setAccBnkIdAccBnk(List<AccBnkBrnch> accBnkIdAccBnk) {
		this.accBnkIdAccBnk = accBnkIdAccBnk;
	}
	
	
}

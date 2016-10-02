package com.ibcsprimax.bepza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRE_QUALIFICATION", catalog = "BEPZA")
public class PreQualification {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pre_qualification_id_Seq")
	@SequenceGenerator(name = "pre_qualification_id_Seq", sequenceName = "pre_qualification_id_Seq", allocationSize = 1, initialValue = 1)
	@Column(name = "pqf_id")
	private Integer id = 0;

	
	// auto gen
	@Column(name = "pqf_password")
	private String password;

	//general Infromation
	@Column(name = "pqf_name_applicant")
	private String nameApp;

	@Column(name = "pqf_name_firm")
	private String nameFrm;

	@Column(name = "pqf_address")
	private String address;

	@Column(name = "pqf_phone")
	private String phone;

	@Column(name = "pqf_email")
	private String email;

	@Column(name = "pqf_fax")
	private String fax;

	// Land and Factory Building
	@Column(name = "pqf_landReq")
	private Integer landReq;

	@Column(name = "pqf_spcReq")
	private Integer spcReq;

	// Name of the proposed project
	@Column(name = "pqf_proProjName")
	private String proProjName;

	// Type of Business / Industry
	@Column(name = "pqf_indType")
	private String indType;

	// Type of Products
	@Column(name = "pqf_prodType")
	private String prodType;
	
	// Type of organization
	@Column(name = "pqf_orgType")
	private String orgType;

	// Name of the Zone
	@Column(name = "pqf_nameZone")
	private String nameZone;

	// Manpower Requirements :
	@Column(name = "pqf_bangladeshi")
	private Integer bangladeshi;

	@Column(name = "pqf_nonBangladeshi")
	private Integer nonBangladeshi;

	//Investments : (All cost in '000'US $)
	@Column(name = "pqf_foregin")
	private Integer foregin;

	@Column(name = "pqf_local")
	private Integer local;

	@Column(name = "pqf_total")
	private Integer total;

	@Override
	public String toString() {
		return "id=" + id + ", nameApp=" + nameApp + ", nameFrm=" + nameFrm;
	}

}

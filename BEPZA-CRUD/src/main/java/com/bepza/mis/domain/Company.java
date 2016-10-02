package com.bepza.mis.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Company {
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String zone;
	@NotEmpty
	private String investment;
	@NotEmpty
	private String employment;
	@NotEmpty
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

}

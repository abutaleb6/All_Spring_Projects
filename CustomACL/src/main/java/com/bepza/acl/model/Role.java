package com.bepza.acl.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.bepza.acl.enam.UserRoleType;

@Entity
@Table(name = "BEPZA_ROLE", catalog = "BEPZA")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bep_role_id_Seq")
	@SequenceGenerator(name = "bep_role_id_Seq", sequenceName = "bep_role_id_Seq", allocationSize = 1, initialValue = 1)
	@Column(name = "ROLE_ID")
	private Integer id;

	@Column(name = "version")
	private Integer version;

	@NotEmpty
	@Column(name = "authority", unique=true, nullable=false)
	String authority= UserRoleType.USER.getUserRoleType();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BEPZA_ROLE_REQUEST_MAP", joinColumns = { @JoinColumn(name = "authority") }, inverseJoinColumns = { @JoinColumn(name = "url") })
	private Set<RequestMap> requestMap = new HashSet<RequestMap>();

	public Set<RequestMap> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Set<RequestMap> requestMap) {
		this.requestMap = requestMap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}

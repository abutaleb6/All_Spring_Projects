package com.bepza.acl.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bepza.acl.enam.URLMapType;

@Entity
@Table(name = "BEPZA_REQUEST_MAP", catalog = "BEPZA")
public class RequestMap {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bep_req_map_id_Seq")
	@SequenceGenerator(name = "bep_req_map_id_Seq", sequenceName = "bep_req_map_id_Seq", allocationSize = 1, initialValue = 1)
	@Column(name = "REQUEST_MAP_ID")
	private Integer id;

	@Column(name = "version")
	private Integer version;

	@Column(name = "url", unique=true, nullable=false)
	String url = URLMapType.SLASH.getUrlMapType();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

package com.bepza.mis.repository;

import java.util.List;

import com.bepza.mis.domain.Company;

public interface CompanyDao {
	Company selectById(long id);

	List<Company> selectAll();

	String insert(Company company);

	void update(Company company);

	void delete(Company company);
	
	Integer countRows();
}

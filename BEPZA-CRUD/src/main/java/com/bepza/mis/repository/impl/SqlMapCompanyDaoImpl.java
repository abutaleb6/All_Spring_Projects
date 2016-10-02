package com.bepza.mis.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.bepza.mis.domain.Company;
import com.bepza.mis.repository.CompanyDao;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class SqlMapCompanyDaoImpl extends SqlMapClientDaoSupport implements CompanyDao {
  private static final String NAMESPACE = "company.";

  @Autowired
  @Qualifier("sqlMapClient")
  public void injectSqlMapClient(SqlMapClient sqlMapClient) {
    setSqlMapClient(sqlMapClient);
  }  
  
  @Override
  public Integer countRows() {
	    return (Integer)getSqlMapClientTemplate().queryForObject(NAMESPACE + "countRows");
	  }
  
  
  @Override
  @SuppressWarnings("unchecked")
  public List<Company> selectAll() {
	  
    return getSqlMapClientTemplate().queryForList(NAMESPACE + "selectAll");
  }

  @Override
  public String insert(Company company) {
    return (String) getSqlMapClientTemplate().insert(NAMESPACE + "insert", company);
  }

  @Override
  public void update(Company company) {
    getSqlMapClientTemplate().update(NAMESPACE + "update", company);
  }

  @Override
  public void delete(Company company) {
    getSqlMapClientTemplate().delete(NAMESPACE + "delete", company);
  }

  @Override
  public Company selectById(long id) {
    return (Company) getSqlMapClientTemplate().queryForObject(NAMESPACE + "selectById", id);
  }
}

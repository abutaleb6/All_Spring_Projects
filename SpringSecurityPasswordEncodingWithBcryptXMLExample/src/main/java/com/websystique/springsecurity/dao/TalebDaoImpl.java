package com.websystique.springsecurity.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springsecurity.model.Taleb;

@Repository("tDao")
public class TalebDaoImpl {
	
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Taleb entity) {
		Session ses = sessionFactory.getCurrentSession();
		ses.persist(entity);
	}
}

package com.ibcs.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibcs.bank.model.AccBnk;
public class AccBankDAOImpl implements AccBankDAO{

	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addAccBnk(AccBnk accBnk) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(accBnk);	
	}

	@Override
	public List<AccBnk> listAccBnks() {
		Session session = this.sessionFactory.getCurrentSession();		
		return (List<AccBnk>) session.createCriteria(AccBnk.class).list();
	}

	@Override
	public AccBnk getAccBnk(int accBnkid) {
		Session session = this.sessionFactory.getCurrentSession();		
		return (AccBnk) session.get(AccBnk.class, accBnkid);
	}

	@Override
	public void deleteAccBnk(AccBnk accBnk) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("DELETE FROM AccBnk WHERE id ="+accBnk.getId()).executeUpdate();	
	}	
	
}

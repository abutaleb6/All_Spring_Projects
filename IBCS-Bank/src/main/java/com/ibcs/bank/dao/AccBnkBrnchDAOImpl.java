package com.ibcs.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ibcs.bank.model.AccBnkBrnch;

public class AccBnkBrnchDAOImpl implements AccBnkBrnchDAO {
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addAccBnkBrnch(AccBnkBrnch accBnkBr) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(accBnkBr);
	}

	@Override
	public List<AccBnkBrnch> listAccBnkBrnchs() {
		Session session = this.sessionFactory.getCurrentSession();
		return (List<AccBnkBrnch>) session.createCriteria(AccBnkBrnch.class).list();
	}

	@Override
	public AccBnkBrnch getAccBnkBrnch(int accBnkBrid) {
		Session session = this.sessionFactory.getCurrentSession();		
		return (AccBnkBrnch) session.get(AccBnkBrnch.class, accBnkBrid);
	}

	@Override
	public void deleteAccBnkBrnch(AccBnkBrnch accBnkBr) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("DELETE FROM AccBnkBrnch WHERE id ="+accBnkBr.getId()).executeUpdate();	
	}
}

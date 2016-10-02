package com.ibcsprimax.bepza.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibcsprimax.bepza.model.UserRole;
import com.ibcsprimax.bepza.model.Users;

@Repository
public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Users findByUserName(String username) {

		List<Users> users = new ArrayList<Users>();
		Session session = this.sessionFactory.getCurrentSession();
		users = session
				.createQuery("from Users where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public UserRole roleFindByUserName(String username) {
		List<UserRole> userroles = new ArrayList<UserRole>();
		Session session = this.sessionFactory.getCurrentSession();
		userroles = session
				.createQuery("from UserRole where user=?")
				.setParameter(0, username).list();

		if (userroles.size() > 0) {
			return userroles.get(0);
		} else {
			return null;
		}
	}

}
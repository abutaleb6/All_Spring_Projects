package com.bepza.dao.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bepza.model.login.UserRole;


@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(UsersDAOImpl.class);

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUserRole(UserRole userRole) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userRole);
		logger.info("UserRole saved successfully, UserRoles Details=" + userRole);
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(userRole);
		logger.info("UserRole updated successfully, UserRole Details=" + userRole);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> listUserRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserRole> userRoleList = session.createQuery("from UserRole")
				.list();
		for (UserRole userRole : userRoleList) {
			logger.info("UserRole List::" + userRole);
		}
		return userRoleList;
	}

	@Override
	public UserRole getUserRoleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserRole userRole = (UserRole) session.load(UserRole.class,
				new Integer(id));
		logger.info("UserRole loaded successfully, UserRole details="
				+ userRole);
		return userRole;
	}

	@Override
	public void removeUserRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserRole userRole = (UserRole) session.load(UserRole.class,
				new Integer(id));
		if (null != userRole) {
			session.delete(userRole);
		}
		logger.info("UserRole deleted successfully, UserRole details="
				+ userRole);
	}

}

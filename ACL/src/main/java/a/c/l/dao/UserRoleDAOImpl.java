package a.c.l.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import a.c.l.model.UserRoles;

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
	public void addUserRole(UserRoles userRole) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userRole);
		logger.info("UserRole saved successfully, UserRoles Details=" + userRole);
	}

	@Override
	public void updateUserRole(UserRoles userRole) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(userRole);
		logger.info("User updated successfully, Person Details=" + userRole);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoles> listUserRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserRoles> userRoleList = session.createQuery("from UserRoles")
				.list();
		for (UserRoles userRole : userRoleList) {
			logger.info("UserRole List::" + userRole);
		}
		return userRoleList;
	}

	@Override
	public UserRoles getUserRoleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserRoles userRole = (UserRoles) session.load(UserRoles.class,
				new Integer(id));
		logger.info("UserRole loaded successfully, UserRole details="
				+ userRole);
		return userRole;
	}

	@Override
	public void removeUserRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserRoles userRole = (UserRoles) session.load(UserRoles.class,
				new Integer(id));
		if (null != userRole) {
			session.delete(userRole);
		}
		logger.info("UserRole deleted successfully, UserRole details="
				+ userRole);
	}

}

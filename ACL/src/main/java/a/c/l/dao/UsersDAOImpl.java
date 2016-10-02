package a.c.l.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import a.c.l.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO {

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
	public void addUser(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		logger.info("User saved successfully, Users Details=" + user);
	}

	@Override
	public void updateUser(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("User updated successfully, Person Details=" + user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Users> userList = session.createQuery("from Users").list();
		for (Users user : userList) {
			logger.info("User List::" + user);
		}
		return userList;
	}

	@Override
	public Users getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Users user = (Users) session.load(Users.class, new Integer(id));
		logger.info("User loaded successfully, Person details=" + user);
		return user;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Users user = (Users) session.load(Users.class, new Integer(id));
		if (null != user) {
			session.delete(user);
		}
		logger.info("User deleted successfully, User details=" + user);
	}

}

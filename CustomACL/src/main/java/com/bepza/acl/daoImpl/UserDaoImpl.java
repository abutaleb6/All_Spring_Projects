package com.bepza.acl.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bepza.acl.dao.UserDao;
import com.bepza.acl.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		return (List<User>) session.createCriteria(User.class).list();
	}

	
	public User getUser(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		return (User) crit.uniqueResult();
	}

	
	public void deleteUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery(
				"DELETE FROM User WHERE username =" + user.getUsername())
				.executeUpdate();
	}

	
	public User findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}
	
	protected Criteria createEntityCriteria(){
		return this.sessionFactory.getCurrentSession().createCriteria(User.class);
	}

}

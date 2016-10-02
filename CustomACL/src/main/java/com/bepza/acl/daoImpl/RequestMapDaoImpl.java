package com.bepza.acl.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bepza.acl.dao.RequestMapDao;
import com.bepza.acl.model.RequestMap;

@Repository
public class RequestMapDaoImpl implements RequestMapDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	public void addRequestMap(RequestMap rm) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(rm);
	}

	
	@SuppressWarnings("unchecked")
	public List<RequestMap> listRequestMaps() {
		Session session = this.sessionFactory.getCurrentSession();
		return (List<RequestMap>) session.createCriteria(RequestMap.class)
				.list();
	}

	
	public RequestMap getRequestMap(int rmid) {
		Session session = this.sessionFactory.getCurrentSession();
		return (RequestMap) session.get(RequestMap.class, rmid);
	}

	
	public void deleteRequestMap(RequestMap rm) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("DELETE FROM RequestMap WHERE id =" + rm.getId())
				.executeUpdate();
	}

}

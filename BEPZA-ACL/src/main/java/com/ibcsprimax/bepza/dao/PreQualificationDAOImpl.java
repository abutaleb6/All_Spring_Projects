package com.ibcsprimax.bepza.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ibcsprimax.bepza.model.PreQualification;

@Repository
public class PreQualificationDAOImpl implements PreQualificationDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(PreQualificationDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPreQualFrmData(PreQualification pq) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(pq);
		logger.info("Pre Qualification Data saved successfully, Pre Qualification Form Details="
				+ pq);
	}

	@Override
	public void updatePreQualFrmData(PreQualification pq) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(pq);
		logger.info("Pre Qualification Data updated successfully, Pre Qualification Form Details="
				+ pq);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreQualification> listPreQualFrmData() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PreQualification> PreQualificationDataList = session.createQuery(
				"from PreQualification").list();
		for (PreQualification pq : PreQualificationDataList) {
			logger.info("PreQualification List::" + pq);
		}
		return PreQualificationDataList;
	}

	@Override
	public PreQualification getPreQualFrmDataById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		PreQualification p = (PreQualification) session.load(
				PreQualification.class, new Integer(id));
		logger.info("PreQualification Data loaded successfully, PreQualification Form details="
				+ p);
		return p;
	}

	@Override
	public void removePreQualFrmData(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		PreQualification p = (PreQualification) session.load(
				PreQualification.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("PreQualification Data deleted successfully, PreQualification Form details="
				+ p);
	}

}

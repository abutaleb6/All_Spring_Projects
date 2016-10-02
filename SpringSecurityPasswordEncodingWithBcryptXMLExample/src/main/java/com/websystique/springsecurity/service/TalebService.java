package com.websystique.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springsecurity.dao.TalebDaoImpl;
import com.websystique.springsecurity.model.Taleb;

@Service("talebService")
@Transactional
public class TalebService {

	TalebDaoImpl tDao;

	public TalebDaoImpl gettDao() {
		return tDao;
	}

	public void settDao(TalebDaoImpl tDao) {
		this.tDao = tDao;
	}

	public void persist(Taleb entity) {
		tDao.persist(entity);
	}
}

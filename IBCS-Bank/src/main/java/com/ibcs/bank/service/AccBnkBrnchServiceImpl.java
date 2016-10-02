package com.ibcs.bank.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibcs.bank.dao.AccBnkBrnchDAO;
import com.ibcs.bank.model.AccBnkBrnch;

public class AccBnkBrnchServiceImpl implements AccBnkBrnchService {
	AccBnkBrnchDAO accBnkBrnchDAO;

	public AccBnkBrnchDAO getAccBnkBrnchDAO() {
		return accBnkBrnchDAO;
	}

	public void setAccBnkBrnchDAO(AccBnkBrnchDAO accBnkBrnchDAO) {
		this.accBnkBrnchDAO = accBnkBrnchDAO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addAccBnkBrnch(AccBnkBrnch accBnkBrnch) {
		accBnkBrnchDAO.addAccBnkBrnch(accBnkBrnch);
	}

	@Override
	@Transactional
	public List<AccBnkBrnch> listAccBnkBrnchs() {		
		return accBnkBrnchDAO.listAccBnkBrnchs();
	}

	@Override
	@Transactional
	public AccBnkBrnch getAccBnkBrnch(int id) {		
		return accBnkBrnchDAO.getAccBnkBrnch(id);
	}

	@Override
	@Transactional
	public void deleteAccBnkBrnch(AccBnkBrnch accBnkBr) {
		accBnkBrnchDAO.deleteAccBnkBrnch(accBnkBr);
	}

}

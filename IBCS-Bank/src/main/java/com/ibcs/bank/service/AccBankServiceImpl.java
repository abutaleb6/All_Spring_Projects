package com.ibcs.bank.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibcs.bank.dao.AccBankDAO;
import com.ibcs.bank.model.AccBnk;

public class AccBankServiceImpl implements AccBankService {
	AccBankDAO accBankDAO;

	public AccBankDAO getAccBankDAO() {
		return accBankDAO;
	}

	public void setAccBankDAO(AccBankDAO accBankDAO) {
		this.accBankDAO = accBankDAO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addAccBnk(AccBnk accBnk) {
		accBankDAO.addAccBnk(accBnk);;
	}

	@Override
	@Transactional
	public List<AccBnk> listAccBnks() {
		
		return accBankDAO.listAccBnks();
	}

	@Override
	@Transactional
	public AccBnk getAccBnk(int accBnkid) {		
		return accBankDAO.getAccBnk(accBnkid);
	}

	@Override
	@Transactional
	public void deleteAccBnk(AccBnk accBnk) {
		accBankDAO.deleteAccBnk(accBnk);
	}

}

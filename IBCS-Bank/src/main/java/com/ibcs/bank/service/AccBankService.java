package com.ibcs.bank.service;

import java.util.List;

import com.ibcs.bank.model.AccBnk;

public interface AccBankService {
	public void addAccBnk(AccBnk accBnk);

	public List<AccBnk> listAccBnks();
	
	public AccBnk getAccBnk(int accBnkid);
	
	public void deleteAccBnk(AccBnk accBnkBr);
}

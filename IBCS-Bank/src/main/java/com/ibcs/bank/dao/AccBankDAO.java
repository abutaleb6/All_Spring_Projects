package com.ibcs.bank.dao;

import java.util.List;

import com.ibcs.bank.model.AccBnk;

public interface AccBankDAO {
	public void addAccBnk(AccBnk accBnk);

	public List<AccBnk> listAccBnks();
	
	public AccBnk getAccBnk(int accBnkid);
	
	public void deleteAccBnk(AccBnk accBnk);
}

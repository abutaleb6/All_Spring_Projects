package com.ibcs.bank.service;

import java.util.List;

import com.ibcs.bank.model.AccBnkBrnch;

public interface AccBnkBrnchService {
	public void addAccBnkBrnch(AccBnkBrnch accBnkBrnch);

	public List<AccBnkBrnch> listAccBnkBrnchs();
	
	public AccBnkBrnch getAccBnkBrnch(int id);
	
	public void deleteAccBnkBrnch(AccBnkBrnch accBnk);
}

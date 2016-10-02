package com.ibcs.bank.dao;

import java.util.List;

import com.ibcs.bank.model.AccBnkBrnch;

public interface AccBnkBrnchDAO {
	public void addAccBnkBrnch(AccBnkBrnch accBnkBr);

	public List<AccBnkBrnch> listAccBnkBrnchs();
	
	public AccBnkBrnch getAccBnkBrnch(int accBnkBrid);
	
	public void deleteAccBnkBrnch(AccBnkBrnch accBnkBr);
}

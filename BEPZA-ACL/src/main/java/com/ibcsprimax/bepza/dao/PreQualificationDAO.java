package com.ibcsprimax.bepza.dao;

import java.util.List;

import com.ibcsprimax.bepza.model.PreQualification;

public interface PreQualificationDAO {
	public void addPreQualFrmData(PreQualification pq);
	public void updatePreQualFrmData(PreQualification pq);
	public List<PreQualification> listPreQualFrmData();
	public PreQualification getPreQualFrmDataById(int id);
	public void removePreQualFrmData(int id);
}

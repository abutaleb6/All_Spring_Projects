package com.ibcsprimax.bepza.service;

import java.util.List;

import com.ibcsprimax.bepza.model.PreQualification;

public interface PreQualificationService {
	public void addPreQualificationData(PreQualification p);

	public void updatePreQualificationData(PreQualification p);

	public List<PreQualification> listPreQualificationData();

	public PreQualification getPreQualificationDataById(int id);

	public void removePreQualificationData(int id);
}

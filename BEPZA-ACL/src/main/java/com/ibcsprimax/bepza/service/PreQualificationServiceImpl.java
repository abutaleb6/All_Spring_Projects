package com.ibcsprimax.bepza.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibcsprimax.bepza.dao.PreQualificationDAO;
import com.ibcsprimax.bepza.model.PreQualification;
@Service("preQualificationService") 
public class PreQualificationServiceImpl implements PreQualificationService{

	PreQualificationDAO preQualificationDao;

	public void setPreQualificationDao(PreQualificationDAO preQualificationDao) {
		this.preQualificationDao = preQualificationDao;
	}

	@Override
	@Transactional
	public void addPreQualificationData(PreQualification pq) {
		this.preQualificationDao.addPreQualFrmData(pq);
	}

	@Override
	@Transactional
	public void updatePreQualificationData(PreQualification pq) {
		this.preQualificationDao.updatePreQualFrmData(pq);
	}

	@Override
	@Transactional
	public List<PreQualification> listPreQualificationData() {		
		return this.preQualificationDao.listPreQualFrmData();
	}

	@Override
	@Transactional
	public PreQualification getPreQualificationDataById(int id) {
		return this.preQualificationDao.getPreQualFrmDataById(id);
	}

	@Override
	@Transactional
	public void removePreQualificationData(int id) {
		this.preQualificationDao.removePreQualFrmData(id);		
	}
}

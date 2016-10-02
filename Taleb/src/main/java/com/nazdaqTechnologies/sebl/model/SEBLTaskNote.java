package com.nazdaqTechnologies.sebl.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SEBLTaskNote {
	private static Logger log = LoggerFactory.getLogger(SEBLTaskNote.class);

	private static Map<String, String> sql2BeanMap = null;
	private static Map<String, String> rs2BeanMap = null;

	private Integer noteId;
	private Integer taskId;
	//private String referenceId;
	private Integer userId;
	private String userLoginName;
	private Date createdOn;
	private Date createdOnX;
	private String note;

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	//	public String getReferenceId() {
	//		return referenceId;
	//	}
	//
	//	public void setReferenceId(String referenceId) {
	//		this.referenceId = referenceId;
	//	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	//	public void setCreatedOn(LocalDateTime createdOn) {
	//		this.createdOn = createdOn;
	//	}
	public void setCreatedDate(Date createdOn) {
		this.createdOn = createdOn;
		this.createdOnX = createdOn;
		//this.createdOnX = Date.from(createdOn.atZone(ZoneId.systemDefault()).toInstant());
	}

	public void setSqlCreatedOn(Timestamp createdOn) {
		this.createdOn = new Date(createdOn.getTime());
		this.createdOnX = new Date(createdOn.getTime());
	}

	public Timestamp getSqlCreatedOn() {
		return new Timestamp(createdOn.getTime());
		//	return Timestamp.valueOf(createdOn);

	}

	//	public void setSqlCreatedOn(Timestamp createdOn) {
	//		this.createdOn = createdOn.toLocalDateTime();
	//	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static final Map<String, String> getSql2BeanMap() {

		if (sql2BeanMap == null) {
			sql2BeanMap = new LinkedHashMap<String, String>();

			sql2BeanMap.put("id_task_key", "taskId");
			sql2BeanMap.put("id_user_key", "userId");
			sql2BeanMap.put("tx_note", "note");

		}

		return sql2BeanMap;
	}

	public static final Map<String, String> getRs2BeanMap() {

		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();

			rs2BeanMap.put("id_note_key", "noteId");
			rs2BeanMap.put("id_task_key", "taskId");
			rs2BeanMap.put("id_user_key", "userId");
			rs2BeanMap.put("tx_login_id", "userLoginName");
			rs2BeanMap.put("dt_created", "sqlCreatedOn");
			rs2BeanMap.put("tx_note", "note");
		}

		return rs2BeanMap;
	}

}

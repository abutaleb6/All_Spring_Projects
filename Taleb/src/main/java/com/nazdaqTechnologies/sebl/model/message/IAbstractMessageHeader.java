package com.nazdaqTechnologies.sebl.model.message;

import java.util.Date;

public interface IAbstractMessageHeader {
	
	String getMessageId();
	
	void setMessageId(String messageId);
	
	String getMessageCorrelationId();
	
	void setMessageCorrelationId(String messageCorrelationId);
	
	Date getMessageTimeStamp();
	
	void setMessageTimeStamp(Date messageTimeStamp);
	
	Integer getSenderId();
	
	void setSenderId(Integer senderId);
	
	String getPriority();
	
	void setPriority(String piority);
	
	String getApplicationId();
	
	void setApplicationId(String applicationId);
	
	String getObjectType();
	
	void setObjectType(String objectType);
	
	String getActionType();
	
	void setActionType(String actionType);
	
	String getStateType();
	
	void setStateType(String stateType);
	
	Integer getEnvId();
	
	void setEnvId(Integer envId);
	
}

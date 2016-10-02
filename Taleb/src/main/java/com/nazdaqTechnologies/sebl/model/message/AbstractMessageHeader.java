package com.nazdaqTechnologies.sebl.model.message;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractMessageHeader implements IAbstractMessageHeader {

	public static final Logger log = LoggerFactory.getLogger(AbstractMessageHeader.class);

	private String messageId;
	private String messageCorrelationId;
	private Date messageTimeStamp;
	private Integer senderId;
	private String priority;
	private String applicationId;
	private String objectType;
	private String actionType;
	private String stateType;
	private Integer envId;
	private String senderSourceIPAddress;  // added by nasrin.akter
	private String senderGatewayIPAddress;

	public AbstractMessageHeader() {
	}

	@Override
	public String getMessageId() {
		return messageId;
	}

	@Override
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Override
	public String getMessageCorrelationId() {
		return messageCorrelationId;
	}

	@Override
	public void setMessageCorrelationId(String messageCorrelationId) {
		this.messageCorrelationId = messageCorrelationId;
	}

	@Override
	public Date getMessageTimeStamp() {
		return messageTimeStamp;
	}

	@Override
	public void setMessageTimeStamp(Date messageTimeStamp) {
		this.messageTimeStamp = messageTimeStamp;
	}

	@Override
	public Integer getSenderId() {
		return senderId;
	}

	@Override
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	@Override
	public String getPriority() {
		return priority;
	}

	@Override
	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String getApplicationId() {
		return applicationId;
	}

	@Override
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String getObjectType() {
		return objectType;
	}

	@Override
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Override
	public String getActionType() {
		return actionType;
	}

	@Override
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Override
	public String getStateType() {
		return stateType;
	}

	@Override
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	@Override
	public Integer getEnvId() {
		return envId;
	}

	@Override
	public void setEnvId(Integer envId) {
		this.envId = envId;
	}

	public String getSenderSourceIPAddress() {
		return senderSourceIPAddress;
	}

	public void setSenderSourceIPAddress(String senderSourceIPAddress) {
		this.senderSourceIPAddress = senderSourceIPAddress;
	}

	public String getSenderGatewayIPAddress() {
		return senderGatewayIPAddress;
	}

	public void setSenderGatewayIPAddress(String senderGatewayIPAddress) {
		this.senderGatewayIPAddress = senderGatewayIPAddress;
	}

}

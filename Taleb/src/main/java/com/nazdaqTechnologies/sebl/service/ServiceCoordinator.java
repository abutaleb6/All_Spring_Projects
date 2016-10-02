package com.nazdaqTechnologies.sebl.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nazdaqTechnologies.core.message.Message;
import com.nazdaqTechnologies.core.message.MessageBuilder;
import com.nazdaqTechnologies.core.message.MessageHeader;
import com.nazdaqTechnologies.core.service.Service;
import com.nazdaqTechnologies.core.service.ServiceMap;
import com.nazdaqTechnologies.sebl.constants.ContentType;
import com.nazdaqTechnologies.sebl.constants.Destination;
import com.nazdaqTechnologies.sebl.constants.ServiceName;

public class ServiceCoordinator {

	private static Logger log = LoggerFactory.getLogger(ServiceCoordinator.class);

	private ServiceMap serviceMap;

	// service coordinator
	public Message<?> service(Message<?> msg) throws Exception {

		List<Message> payloadList = null;
		List<Message> msgList = new ArrayList<Message>();
		Message msgRet = null;

		try {

			if (msg.getHeader().getContentType().equals(ContentType.MULTI_MESSAGE.toString())) {

				payloadList = (List<Message>) msg.getPayload();

				if (!payloadList.isEmpty()) {

					for (Message payload : payloadList) {
						msgRet = handleMessege(payload);
						msgList.add(msgRet);
					}
				}
				msg = MessageBuilder.withPayload(msgList).setHeader(MessageHeader.SOURCE, Destination.nRemit.toString())
				        .setHeader(MessageHeader.CONTENT_TYPE, msg.getHeader().getContentType())
				        .setHeader(MessageHeader.ACTION_TYPE, msg.getHeader().getActionType())
				        .setHeader(MessageHeader.DESTINATION, Destination.nRemitClient.toString()).build();
			}
			else {

				msg = handleMessege(msg);
				
				if(msg.getHeader().getSource().equals("SA")){
					msg = MessageBuilder.withPayload(msg.getPayload()).setHeader(MessageHeader.SOURCE, Destination.nRemitSa.toString())
					        .setHeader(MessageHeader.CONTENT_TYPE, msg.getHeader().getContentType())
					        .setHeader(MessageHeader.ACTION_TYPE, msg.getHeader().getActionType())
					        .setHeader(MessageHeader.DESTINATION, Destination.nRemitClientSa.toString()).build();
				}
				else{
					msg = MessageBuilder.withPayload(msg.getPayload()).setHeader(MessageHeader.SOURCE, Destination.nRemit.toString())
					        .setHeader(MessageHeader.CONTENT_TYPE, msg.getHeader().getContentType())
					        .setHeader(MessageHeader.ACTION_TYPE, msg.getHeader().getActionType())
					        .setHeader(MessageHeader.DESTINATION, Destination.nRemitClient.toString()).build();
				}

			}

		}
		catch (Exception ex) {
			log.error("Error {}", ex);
			throw ex;
		}

		//log.info("Msg {}", msg);
		return msg;
	}

	// handle message & lookup services
	private Message handleMessege(Message msg) throws Exception {

		String serviceName = msg.getHeader().getContentType();

		String actionType = msg.getHeader().getActionType();

		Service serviceHandler;

		try {

			serviceHandler = serviceMap.getServiceByName(serviceName + ServiceName.SERVICE_POSTFIX.toString());

			log.info("Service Lookup {} -> {} ", serviceName, serviceHandler.getServiceName());

			if (serviceHandler != null) {

				msg = serviceHandler.serviceSingle(msg);
			}

		}
		catch (Exception ex) {
			log.error("Error {}", ex);
			throw ex;
		}

		return msg;

	}

	// ////// Setter methods ////////////////////

	public ServiceMap getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(ServiceMap serviceMap) {
		this.serviceMap = serviceMap;
	}

}

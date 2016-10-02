package com.nazdaqTechnologies.sebl.model.message;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractMessage<T> implements IMessage<T> {
	
	public static final Logger log = LoggerFactory.getLogger(AbstractMessage.class);

	protected AbstractMessageHeader header;
	
	private List<T> payLoad;
	
	
	public AbstractMessageHeader getHeader() {
		return header;
	}
	
	public void setHeader(AbstractMessageHeader header) {
		this.header = header;
	}
	
	public List<T> getPayLoad() {
		return payLoad;
	}
	
	public void setPayLoad(List<T> payLoad) {
		this.payLoad = payLoad;	
	}
	
	public void setPayLoad(T t) {
		if (payLoad == null) {
			payLoad = new ArrayList<T>();
		}
		payLoad.add(t);	
	}
	
}

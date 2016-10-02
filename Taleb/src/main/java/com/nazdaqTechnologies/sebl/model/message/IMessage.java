package com.nazdaqTechnologies.sebl.model.message;

import java.util.List;

public interface IMessage<T> {
	
	IAbstractMessageHeader getHeader();
	
	List<T> getPayLoad();
	
}

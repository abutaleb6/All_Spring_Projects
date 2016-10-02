package com.nazdaqTechnologies.sebl.model.message;

import java.util.Map;

public interface IJsonMessageHeader {
	
	Map<String, String> getUserDefined();
	
	void setUserDefined(Map<String, String> userDefined);
	
}

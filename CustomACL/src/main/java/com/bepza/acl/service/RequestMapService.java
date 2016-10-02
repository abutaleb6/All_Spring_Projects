package com.bepza.acl.service;

import java.util.List;

import com.bepza.acl.model.RequestMap;

public interface RequestMapService {
	public void addRequestMap(RequestMap rm);

	public List<RequestMap> listRequestMaps();
	
	public RequestMap getRequestMap(int rmid);
	
	public void deleteRequestMap(RequestMap rm);
}

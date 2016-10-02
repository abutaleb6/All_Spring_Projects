package com.bepza.acl.dao;

import java.util.List;

import com.bepza.acl.model.RequestMap;

public interface RequestMapDao {
	public void addRequestMap(RequestMap rm);

	public List<RequestMap> listRequestMaps();
	
	public RequestMap getRequestMap(int rmid);
	
	public void deleteRequestMap(RequestMap rm);
}

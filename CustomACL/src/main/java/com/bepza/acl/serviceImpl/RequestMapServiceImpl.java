package com.bepza.acl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepza.acl.dao.RequestMapDao;
import com.bepza.acl.model.RequestMap;
import com.bepza.acl.service.RequestMapService;

@Service("requestMapService")
@Transactional
public class RequestMapServiceImpl implements RequestMapService {

	@Autowired
	RequestMapDao requestMapDao;

	public void addRequestMap(RequestMap rm) {
		requestMapDao.addRequestMap(rm);
	}

	public List<RequestMap> listRequestMaps() {
		return requestMapDao.listRequestMaps();
	}

	public RequestMap getRequestMap(int rmid) {
		return requestMapDao.getRequestMap(rmid);
	}

	public void deleteRequestMap(RequestMap rm) {
		requestMapDao.deleteRequestMap(rm);
	}

}

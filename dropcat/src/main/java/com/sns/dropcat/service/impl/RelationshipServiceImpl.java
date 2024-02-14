package com.sns.dropcat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.dropcat.controller.RelationshipService;
import com.sns.dropcat.replyfomat.Result;
@Service
public class RelationshipServiceImpl implements RelationshipService {
	
	RelationshipDao relationshipDao;
	
	
	@Autowired
	public RelationshipServiceImpl(RelationshipDao relationshipDao) {
		super();
		this.relationshipDao = relationshipDao;
	}



	@Override
	public Result whofollowme(String userId) {
		Result res =relationshipDao.whofollowme(userId);
		return res;
	}



	@Override
	public Result whoifollow(String userId) {
		Result res =relationshipDao.whoifollow(userId);
		return res;
	}
	
	

}

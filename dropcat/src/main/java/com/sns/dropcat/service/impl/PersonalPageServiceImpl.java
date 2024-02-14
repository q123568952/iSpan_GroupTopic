package com.sns.dropcat.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.PersonalPageDao;
import com.sns.dropcat.model.Blacklist;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.PersonalPageService;

@Service
public class PersonalPageServiceImpl implements PersonalPageService {
	
	PersonalPageDao personalPageDao;
	
//	@Autowired
	public PersonalPageServiceImpl(PersonalPageDao personalPageDao) {
		super();
		this.personalPageDao = personalPageDao;
	}


	@Override
	@Transactional
	public Result getPersonalPageUser(String userAccount) {
		Result userResult = personalPageDao.getPersonalPageUser(userAccount);
		return userResult;
	}


	@Override
	@Transactional
	public Result getPersonalPagePost(String userAccount) {
		Result postResult = personalPageDao.getPersonalPagePost(userAccount);
		return postResult;
	}


	@Override
	@Transactional
	public Result getPersonalPageCollection(String userId) {
		Result collectionResult = personalPageDao.getPersonalPageCollection(userId);
		return collectionResult;
	}


	@Override
	public Result getPersonalPageSurfingHistory(String userId) {
		Result SurfingHistory = personalPageDao.getPersonalPageSurfingHistory(userId);
		return SurfingHistory;
	}


	@Override
	public Result getTotalFollowing(String userID) {
		Result res = personalPageDao.getTotalFollowing(userID);
		return res;
	}


	@Override
	public Result getfollowingstatuas(String userId, String userAccount) {
		Result res = personalPageDao.getfollowingstatuas(userId,userAccount);
		return res;
	}


	@Override
	public Result getblackstatuas(String userId, String userAccount) {
		Result res = personalPageDao.getblackstatuas(userId,userAccount);
		return res;
	}


	@Override
	@Transactional
	public Result insertblackList(Blacklist blackList) {
		blackList.setBlockTime(new Date());
		Result res = personalPageDao.insertblackList(blackList);
		return res;
	}


	@Override
	@Transactional
	public Result deleteblackList(Blacklist blackList) {
		Result res = personalPageDao.deleteblackList(blackList);
		return res;
	}
	
	
	
}

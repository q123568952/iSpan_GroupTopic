package com.sns.dropcat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.ExploreDao;
import com.sns.dropcat.dao.TypeKeysDao;
import com.sns.dropcat.model.typeKeys;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.ExploreService;
@Service
public class ExploreServiceImpl implements ExploreService {
	
	ExploreDao exploreDao;
	
	
	
	@Autowired
	public ExploreServiceImpl(ExploreDao exploreDao) {
		super();
		this.exploreDao = exploreDao;
		
	}



	@Override
	@Transactional
	public Result getExplorePost() {
		Result res = exploreDao.getExplorePost();
		return res;
	}



	@Override
	@Transactional
	public Result getMapPost (Double lat, Double lng, Integer dis){
		
		Result res = exploreDao.getMapPost(lat, lng, dis);
		return res;
	}



	@Override
	public Result uploadExplorePost(Integer page, Integer uploadamount, Date nowTime, String userId) {
		Result res = exploreDao.uploadExplorePost(page, uploadamount,nowTime,userId);
		return res;
	}



	@Override
	public Result uploadpostnopreferdata(Integer page, Integer uploadamount, Date nowTime, String userId,
			Integer lastNum) {
		Result res = exploreDao.uploadpostnopreferdata(page, uploadamount,nowTime,userId,lastNum);
		return res;
	}



	
	
	

}

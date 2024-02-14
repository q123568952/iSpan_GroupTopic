package com.sns.dropcat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.InformationDao;
import com.sns.dropcat.model.FollowInformation;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.model.tellYouHaveLike;
import com.sns.dropcat.respository.FollowInformationRepository;
import com.sns.dropcat.respository.InformationRespository;
import com.sns.dropcat.respository.LikeInformationRespository;
import com.sns.dropcat.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService
{

	// 查詢追蹤的人發文、誰按讚我的貼文、誰追蹤我的通知都用這1個InformationServiceImpl
	
	
	
	@Autowired
	InformationDao informationDao;
	
	
	
	@Override
	@Transactional
	public List<Information> findAll() {

	
		
		return informationDao.findAll();
	}
	
	
	
	



	
	
	
///////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	@Autowired
	InformationRespository informationRespository;
	
	

	
	@Override
	public List<Information> findUserInformationByJPA(Integer userId) {

		List<Information> x	=informationRespository.getInformationByUserId(userId);

		return x;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	LikeInformationRespository likeInformationRespository;
	

	@Override
	public List<tellYouHaveLike> find_WhoLikeYourPost(Integer userId) {

		
		List<tellYouHaveLike> z=likeInformationRespository.find_WhoLikeYourPost_By_Post_UserId(userId);
		
		
		
		
		
		
		
		return z;
	}
	
	
	
	
	
	@Autowired
	FollowInformationRepository followInformationRepository;
	

	@Override
	public List<FollowInformation> find_WhoFollowYouByJPA(Integer userId) {


	List<FollowInformation> x =followInformationRepository.getFollowInformationByUserId(userId);
		
		
		
		
		return x;
	}


	@Override
	public List<tellYouHaveLike> find_WhoOwnThisPost(Integer postContextId) {
		List<tellYouHaveLike> res =likeInformationRespository.find_WhoOwnThisPost(postContextId);
		return res;
	}
	
	
	
	
	
	

}

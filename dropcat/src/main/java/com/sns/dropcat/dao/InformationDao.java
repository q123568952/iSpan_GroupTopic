package com.sns.dropcat.dao;

import java.util.List;

import com.sns.dropcat.model.Information;
import com.sns.dropcat.queryreturncalsses.InformationJoinFollowingJoinPost;
import com.sns.dropcat.replyfomat.Result;

public interface InformationDao 
{
	
	List<Information> findAll();
	


	
	
	
	
	Result getInformationJoinFollowingJoinPost();
	

//	List<Information> findUserInformationByUserId();
	
	

}

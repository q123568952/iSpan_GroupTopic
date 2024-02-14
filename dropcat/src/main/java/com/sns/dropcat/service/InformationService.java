package com.sns.dropcat.service;

import java.util.List;

import com.sns.dropcat.model.FollowInformation;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.model.tellYouHaveLike;
import com.sns.dropcat.replyfomat.Result;

public interface InformationService 
{
	// 查詢追蹤的人發文、誰按讚我的貼文、誰追蹤我的通知都用這1個InformationService 的 interface
	
	List<Information> findAll();
	
	
	
	List<Information> findUserInformationByJPA(Integer userId);
	
	
	
	List<tellYouHaveLike> find_WhoLikeYourPost(Integer userId);
	
	
	List<FollowInformation> find_WhoFollowYouByJPA(Integer userId);



	List<tellYouHaveLike> find_WhoOwnThisPost(Integer postContextId);
	

}

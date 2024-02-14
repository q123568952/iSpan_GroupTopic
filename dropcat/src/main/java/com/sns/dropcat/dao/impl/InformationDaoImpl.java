package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.InformationDao;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.queryreturncalsses.InformationJoinFollowingJoinPost;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class InformationDaoImpl implements InformationDao
{
	
//	like session
	@PersistenceContext
	EntityManager entityManager;
	
	
	// 列出所有Information 通知的列資料
	@Override
	public List<Information> findAll() 
	{
		
		String hql = "FROM Information";
		List<Information> informationlist = entityManager.createQuery(hql, Information.class).getResultList();
		System.out.println(informationlist.toString());
		return informationlist;
		
		
		
		
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Result getInformationJoinFollowingJoinPost() 
	{

		List<InformationJoinFollowingJoinPost> getInformation=entityManager.createQuery(
				"SELECT P.createtime, FL.fanID"
				+ "P.userID, P.othersUserID"
				+ ", P.postId, P.postType"
				+ ", P.postSettingTime, P.imgURL"
				
				+ "FROM Post P "
				+ "LEFT JOIN FanList FL ON P.userId=FL.userID  "
				+ "LEFT JOIN"
				
				+ " WHERE SH.surfingUserID = 1 "
				+ "GROUP BY SH.postID ORDER BY SH.surfingTime", InformationJoinFollowingJoinPost.class)
				.getResultList();

		
		
		return null;
	}
	




	
	
	
	
	
	

}

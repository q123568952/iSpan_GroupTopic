package com.sns.dropcat.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.ExploreDao;
import com.sns.dropcat.queryreturncalsses.ExploreMap;
import com.sns.dropcat.queryreturncalsses.ExplorePost;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ExploreDaoImpl implements ExploreDao {

//	like session
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Result getExplorePost() {
//		sql:SELECT a.post_id, imgurl, COUNT(DISTINCT b.user_liked_id) likes, COUNT(DISTINCT c.comment_id) mesg 
//		FROM post_img a  LEFT JOIN likes b ON a.post_id=b.post_context_id 
//		                 LEFT JOIN comments c ON a.post_id=c.post_context_id 
//		                 LEFT JOIN post d ON a.post_id=d.post_id 
//		WHERE a.img_serial = 0 
//		GROUP BY a.post_id  
//		ORDER BY d.createtime DESC 
//		LIMIT 9 OFFSET 0;
		List<ExplorePost>results = entityManager.createQuery("SELECT a.postId, imgURL, COUNT(DISTINCT b.userLikedId) likes, COUNT(DISTINCT c.commentId) mesg FROM PostImg a  LEFT JOIN Likes b ON a.postId=b.postContextId LEFT JOIN Comments c ON a.postId=c.postContextId LEFT JOIN Post d ON a.postId=d.postId WHERE a.imgSerial = 0 GROUP BY a.postId  ORDER BY d.createtime DESC LIMIT 9 OFFSET 0",ExplorePost.class).getResultList();
		return new Result(true, MessageConstant.GET_ExplorePost_SUCCESS, results);
	}

	@Override
	public Result getMapPost(Double lat, Double lng, Integer dis) {
//		sql:SELECT a.post_id, imgurl, b.lat, b.lng, c.user_account FROM post_img a LEFT JOIN post b ON a.post_id=b.post_id LEFT JOIN user_info c ON b.user_id=c.id WHERE a.img_serial = 0 ORDER BY b.createtime DESC LIMIT 9 OFFSET 0;
//		List<ExploreMap> results = entityManager.createQuery("SELECT a.postId, imgURL, b.lat, b.lng, c.userAccount FROM PostImg a LEFT JOIN Post b ON a.postId=b.postId LEFT JOIN UserInfo c ON b.userId=c.id "
//				+ "WHERE a.imgSerial = 0 AND "
//				+ "6371.004*ACOS(SIN("
//				+ lat.toString()
//				+ "/180*PI())*SIN(b.lat/180*PI())+ COS("
//				+ lat.toString()
//				+ "/180*PI())*COS(b.lat/180*PI())*COS(("
//				+ lng.toString()
//				+ " -b.lng)/180*PI())) < "
//				+ dis.toString()
//				+ " ORDER BY b.createtime DESC LIMIT 9 OFFSET 0",ExploreMap.class).getResultList();
		List<ExploreMap> results =(List<ExploreMap>) entityManager.createNativeQuery("SELECT a.postId, imgURL, b.lat, b.lng, c.userAccount FROM PostImg a LEFT JOIN Post b ON a.postId=b.postId LEFT JOIN UserInfo c ON b.userId=c.id "
				+ "WHERE a.imgSerial = 0 AND "
				+ "6371.004*ACOS(SIN( :lat1 "
				+ "/180*PI())*SIN(b.lat/180*PI())+ COS( :lat2"
				+ "/180*PI())*COS(b.lat/180*PI())*COS((:lng1"
				+ "-b.lng)/180*PI())) < :dis1"
				+ " ORDER BY b.createtime DESC LIMIT 9 OFFSET 0",ExploreMap.class)
				.setParameter("lat1", lat).setParameter("lat2", lat)
				.setParameter("lng1", lng).setParameter("dis1", dis)
				.getResultList();
		return new Result(true, MessageConstant.GET_MapPost_SUCCESS, results);
	}

	@Override
	public Result uploadExplorePost(Integer page, Integer uploadamount, Date nowTime, String userId) {
		Integer offsetamount = 3*(page-1);
		List<ExplorePost> results;
			results = entityManager.createNativeQuery("SELECT a.postId, imgURL, COUNT(DISTINCT b.userLikedId) likes, COUNT(DISTINCT c.commentId) mesg FROM PostImg a  LEFT JOIN Likes b ON a.postId=b.postContextId LEFT JOIN Comments c ON a.postId=c.postContextId LEFT JOIN Post d ON a.postId=d.postId WHERE ((d.createtime < :nowTime and a.imgSerial = 0) AND d.postType IN (SELECT t.typeId FROM (SELECT typeId FROM UserPreferTypes WHERE userId=:userId and typeId != 0 order by score desc LIMIT 3 OFFSET 0)AS t)) GROUP BY a.postId  ORDER BY d.createtime DESC LIMIT :uploadamount OFFSET :offsetamount",ExplorePost.class)
					.setParameter("uploadamount", uploadamount)
					.setParameter("offsetamount", offsetamount)
					.setParameter("nowTime", nowTime)
					.setParameter("userId",userId)
					.getResultList();
		return new Result(true, MessageConstant.GET_UploadPost_SUCCESS, results);
	}

	@Override
	public Result uploadpostnopreferdata(Integer page, Integer uploadamount, Date nowTime, String userId,
			Integer lastNum) {
		if(page==1) {
			lastNum = 3;
		}
		Integer offsetamount = 3*(page-1)+(3-lastNum);
		List<ExplorePost> results;
		results = entityManager.createNativeQuery("SELECT a.postId, imgURL, COUNT(DISTINCT b.userLikedId) likes, COUNT(DISTINCT c.commentId) mesg FROM PostImg a  LEFT JOIN Likes b ON a.postId=b.postContextId LEFT JOIN Comments c ON a.postId=c.postContextId LEFT JOIN Post d ON a.postId=d.postId WHERE ((d.createtime < :nowTime and a.imgSerial = 0) AND d.postType not IN (SELECT t.typeId FROM (SELECT typeId FROM UserPreferTypes WHERE userId=:userId and typeId!= 0 order by score desc LIMIT 3 OFFSET 0)AS t)) GROUP BY a.postId  ORDER BY d.createtime DESC LIMIT :uploadamount OFFSET :offsetamount",ExplorePost.class)
				.setParameter("uploadamount", uploadamount)
				.setParameter("offsetamount", offsetamount)
				.setParameter("nowTime", nowTime)
				.setParameter("userId",userId)
				.getResultList();
		return new Result(true, MessageConstant.GET_UploadPost_SUCCESS, results);
	}
	
}

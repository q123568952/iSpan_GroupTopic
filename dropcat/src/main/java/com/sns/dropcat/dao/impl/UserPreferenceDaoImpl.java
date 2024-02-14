package com.sns.dropcat.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.UserPreferenceDao;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserPreferenceDaoImpl implements UserPreferenceDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Result addUserPreference(String userId, String postId) {
		try {
		entityManager.createNativeQuery("INSERT INTO UserPreferTypes (typeId, userId, score) VALUES ((SELECT postType FROM Post WHERE postId=:postId), :userId ,1) ON DUPLICATE KEY UPDATE score = score + 1")
		.setParameter("postId", postId).setParameter("userId", userId).executeUpdate();
		}catch(Exception err) {
			System.out.println(err);
			return new Result(false,MessageConstant.ADD_Preference_FAILED,null);
		}
		return new Result(false,MessageConstant.ADD_Preference_SUCCESS,null);
	}

	@Override
	public Result addUserRecord(String userid, String postid, Date date) {
		try {
			entityManager.createNativeQuery("INSERT INTO SurfingHistory (postID, surfingTime, surfingUserID) VALUES (:postid, :date, :userid)")
			.setParameter("postid", postid).setParameter("date", date).setParameter("userid", userid).executeUpdate();
			}catch(Exception err) {
				System.out.println(err);
				return new Result(false,null,null);
			}
		return new Result(true,null,null);
	}

	
}

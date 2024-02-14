package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.impl.RelationshipDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class RelationshipDaoImpl implements RelationshipDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Result whofollowme(String userId) {
		List<UserInfo> result = entityManager.createQuery("SELECT a.id, a.userAccount, a.username, a.usericon  FROM UserInfo a LEFT JOIN FollowingList b ON a.id=b.userID WHERE b.followingUserID=:userId", UserInfo.class)
		.setParameter("userId", userId).getResultList();
				return new Result(true,null,result);
	}

	@Override
	public Result whoifollow(String userId) {
		List<UserInfo> result = entityManager.createQuery("SELECT  a.id, a.userAccount, a.username, a.usericon  FROM UserInfo a LEFT JOIN FollowingList b ON a.id=b.followingUserID WHERE b.userID=:userId", UserInfo.class)
				.setParameter("userId", userId).getResultList();
						return new Result(true,null,result);
	}

	
}

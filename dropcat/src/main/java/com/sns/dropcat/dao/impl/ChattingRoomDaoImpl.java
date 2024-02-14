package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.ChattingRoomDao;
import com.sns.dropcat.queryreturncalsses.ChatFollowers;
import com.sns.dropcat.queryreturncalsses.ChatHistory;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChattingRoomDaoImpl implements ChattingRoomDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Result getChatHistory(Integer userID, Integer followingUserID) {
		List<ChatHistory> CHContent = entityManager.createQuery(
				"SELECT senderID, receiverID, messages, chattingTime FROM ChattingHistory CH LEFT JOIN UserInfo U ON CH.senderID = U.id WHERE (senderID = :userID AND receiverID = :followingUserID) OR (senderID = :followingUserID AND receiverID = :userID) ORDER BY chattingTime ASC",ChatHistory.class)
				.setParameter("followingUserID", followingUserID)
				.setParameter("userID", userID)
				.getResultList();
		return new Result(true, MessageConstant.GET_ChattingHistory_SUCCESS, CHContent);
	}

	@Override
	public Result getChatFollowers(Integer userID) {
		List<ChatFollowers> CFContent = entityManager.createQuery(
				"SELECT FL.followingUserID, U.username, U.usericon, CH.messages FROM FollowingList FL LEFT JOIN UserInfo U ON FL.followingUserID = U.id LEFT JOIN ChattingHistory CH ON FL.followingUserID = CH.receiverID WHERE FL.userID = :userID GROUP BY FL.followingUserID",ChatFollowers.class)
				.setParameter("userID", userID)
				.getResultList();
		return new Result(true, MessageConstant.GET_ChattingHistory_SUCCESS, CFContent);
	}

}

package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.PersonalPageDao;
import com.sns.dropcat.model.Blacklist;
import com.sns.dropcat.queryreturncalsses.PersonalPageCollection;
import com.sns.dropcat.queryreturncalsses.PersonalPagePost;
import com.sns.dropcat.queryreturncalsses.PersonalPageSurfingHistory;
import com.sns.dropcat.queryreturncalsses.PersonalPageUser;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PersonalPageDaoImpl implements PersonalPageDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Result getPersonalPageUser(String userAccount) {
		List<PersonalPageUser> PPUContent = entityManager.createQuery(
				"SELECT U.id ID, U.userAccount userAccount, U.usericon userIcon, COUNT(DISTINCT P.postId) postNum, COUNT(DISTINCT Fo.followingUserID) followingNum, U.username userDisplayName, U.introduction userIntroduction, COUNT(DISTINCT Fa.userID) fanNum FROM UserInfo U LEFT JOIN Post P ON U.id = P.userId LEFT JOIN FollowingList Fo ON U.id = Fo.userID LEFT JOIN FollowingList Fa ON U.id = Fa.followingUserID WHERE U.userAccount = :userAccount",PersonalPageUser.class)
				.setParameter("userAccount", userAccount)
				.getResultList();
		return new Result(true, MessageConstant.GET_PersonalPageData_SUCCESS, PPUContent);
	}

	@Override
	public Result getPersonalPagePost(String userAccount) {
		List<PersonalPagePost> PPPContent = entityManager.createQuery(
				"SELECT P.userId, P.postId, PI.imgURL, COUNT(DISTINCT L.userLikedId) numOfLikes, COUNT(DISTINCT C.commentId) numOfComment FROM Post P LEFT JOIN PostImg PI ON P.postId = PI.postId LEFT JOIN Likes L ON P.postId = L.postContextId LEFT JOIN Comments C ON P.postId = C.postContextId LEFT JOIN UserInfo d on d.id= P.userId WHERE d.userAccount = :userAccount GROUP BY P.postId ORDER BY P.createtime desc",PersonalPagePost.class)
				.setParameter("userAccount", userAccount)
				.getResultList();
		return new Result(true, MessageConstant.GET_PersonalPageData_SUCCESS, PPPContent);
	}

	@Override
	public Result getPersonalPageCollection(String userId) {
		List<PersonalPageCollection> PPCContent = entityManager.createQuery(
				"SELECT CP.postID, CP.userID, P.posttext, PI.imgURL, COUNT(DISTINCT L.userLikedId)numOfLikes, COUNT(DISTINCT C.commentId) NumOfComment FROM CollectionPost CP LEFT JOIN Post P ON CP.postID = P.postId LEFT JOIN PostImg PI ON CP.postID = PI.postId LEFT JOIN Likes L ON CP.postID = L.postContextId left JOIN Comments C ON CP.postID = C.postContextId WHERE CP.userID = :userID GROUP BY CP.postID ORDER BY CP.collectTime DESC", PersonalPageCollection.class)
				.setParameter("userID", userId)
				.getResultList();
		return new Result(true, MessageConstant.GET_PersonalPageData_SUCCESS, PPCContent);
	}

	@Override
	public Result getPersonalPageSurfingHistory(String userId) {
		List<PersonalPageSurfingHistory> PPSHContent = entityManager.createQuery(
				"SELECT SH.surfingUserID, SH.postID, P.posttext, PI.imgURL, COUNT(DISTINCT L.userLikedId) numOfLikes, COUNT(DISTINCT C.commentId) numOfComment FROM SurfingHistory SH LEFT JOIN Post P ON SH.postID = P.postId LEFT JOIN PostImg PI ON SH.postID = PI.postId LEFT JOIN Likes L ON SH.postID = L.postContextId LEFT JOIN Comments C ON SH.postID = C.postContextId WHERE SH.surfingUserID = :userID GROUP BY SH.postID ORDER BY SH.surfingTime DESC", PersonalPageSurfingHistory.class)
				.setParameter("userID", userId)
				.getResultList();
		return new Result(true, MessageConstant.GET_PersonalPageData_SUCCESS, PPSHContent);
	}

	@Override
	public Result getTotalFollowing(String userID) {
		List<Object[]> result = entityManager.createQuery("select count(*) from FollowingList where followingUserID=:userID")
				.setParameter("userID", userID).getResultList();
		return new Result(true, null, result);
	}

	@Override
	public Result getfollowingstatuas(String userId, String userAccount) {
		List<Object[]> result = entityManager.createQuery("select count(*) from FollowingList where followingUserID=(select id from UserInfo where userAccount=:userAccount) and userID=:userID")
				.setParameter("userID", userId).setParameter("userAccount", userAccount).getResultList();
		return new Result(true, null, result);
	}

	@Override
	public Result getblackstatuas(String userId, String userAccount) {
		List<Object[]> result = entityManager.createQuery("select count(*) from Blacklist where blockedUserID=(select id from UserInfo where userAccount=:userAccount) and blockerID=:userID")
				.setParameter("userID", userId).setParameter("userAccount", userAccount).getResultList();
		return new Result(true, null, result);
	}

	@Override
	public Result insertblackList(Blacklist blackList) {
		try {
			entityManager.persist(blackList);
			}catch(Exception e) {
				System.out.println(e);
				return new Result(false, null, null);
			}
			return new Result(true, null, null);
	}

	@Override
	public Result deleteblackList(Blacklist blackList) {
		entityManager.createNativeQuery("DELETE FROM Blacklist WHERE blockedUserID = :blockedUserID AND blockerID = :blockerID")
		.setParameter("blockedUserID", blackList.getBlockedUserID())
		.setParameter("blockerID", blackList.getBlockerID())
		.executeUpdate();
		return new Result(true, MessageConstant.DELETE_Liked_SUCCESS, null);
	}
	
	
	

}

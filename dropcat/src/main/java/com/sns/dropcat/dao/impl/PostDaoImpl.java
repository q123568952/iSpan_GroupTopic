package com.sns.dropcat.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.PostDao;
import com.sns.dropcat.model.FollowingList;
import com.sns.dropcat.queryreturncalsses.FollowingListandFanList;
import com.sns.dropcat.queryreturncalsses.LikeUsers;
import com.sns.dropcat.queryreturncalsses.MainPagePost;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PostDaoImpl implements PostDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Result getMainPagePost(Integer userId, Integer postPage,Integer setLimit) {
		
//		SELECT DISTINCT
//		(SELECT DISTINCT uf.id FROM UserInfo uf LEFT JOIN FollowingList ON uf.id = FollowingList.userID WHERE uf.id = 16) AS accountUser,
//		u.id ,
//		u.userAccount,
//		u.usericon,
//		u.username,
//		p.postId,
//		PostImg.imgURL,
//		p.posttext,
//		p.lat,
//		p.lng,
//		p.createtime,
//		p.edittime,
//		COUNT(DISTINCT c.commentId) AS commentCount,
//		COUNT(DISTINCT l.likeId) AS likeCount
//	FROM UserInfo u
//	LEFT JOIN Post p ON u.id = p.userId
//	LEFT JOIN Comments c ON p.postId = c.postContextId
//	LEFT JOIN Likes l ON p.postId = l.postContextId
//	LEFT JOIN PostImg pImg ON p.postId = pImg.postId
//	WHERE u.id = 16 OR u.id IN (
//		SELECT DISTINCT fl.followingUserID
//		FROM FollowingList fl
//		JOIN Post post ON fl.followingUserID = post.userId
//		WHERE fl.userID = 16
//		)
//	AND NOT EXISTS ( 
//	SELECT 1 
//	FROM Blacklist b 
//	WHERE b.blockedUserID = u.id
//	AND b.blockerID = (SELECT DISTINCT ub.id FROM UserInfo ub LEFT JOIN FollowingList ON ub.id = FollowingList.userID WHERE ub.id = 16)
//	)
//	AND pImg.imgSerial = 0
//	GROUP BY p.postId
//	ORDER BY GREATEST(p.edittime, p.createtime) DESC

//		版本2
//		SELECT DISTINCT
//		(
//		SELECT DISTINCT uf.id FROM UserInfo uf 
//		LEFT JOIN FollowingList ff ON uf.id = ff.userID 
//		WHERE uf.id = 222332
//		) 
//		AS accountUser,
//		u.id ,
//		u.userAccount,
//		u.usericon,
//		u.username,
//		p.postId,
//		pImg.imgURL,
//		p.posttext,
//		p.lat,
//		p.lng,
//		p.createtime,
//		p.edittime,
//		COUNT(DISTINCT c.commentId) AS commentCount,
//		COUNT(DISTINCT l.likeId) AS likeCount,
//		CASE WHEN EXISTS 
//			(
//				SELECT 1 FROM Likes postlike 
//				WHERE postlike.postContextId = p.postId 
//				AND postlike.userLikedId = 222332
//			) 		
//		THEN 1 ELSE 0 END AS isLiked,
//		CASE WHEN EXISTS 
//			(
//				SELECT 1 FROM CollectionPost postCollect
//				WHERE postCollect.postID = p.postId 
//				AND postCollect.userID = 222332
//			) 
//		THEN 1 ELSE 0 END AS isCollected
//	FROM UserInfo u
//	INNER JOIN Post p ON u.id = p.userId
//	LEFT JOIN Comments c ON p.postId = c.postContextId
//	LEFT JOIN Likes l ON p.postId = l.postContextId
//	LEFT JOIN PostImg pImg ON p.postId = pImg.postId AND pImg.imgSerial = 0
//	WHERE u.id = 222332 OR u.id IN (
//		SELECT DISTINCT fl.followingUserID
//		FROM FollowingList fl
//		JOIN Post post ON fl.followingUserID = post.userId
//		WHERE fl.userID = 222332
//		)
//	AND NOT EXISTS ( 
//	SELECT 1 
//	FROM Blacklist b 
//	WHERE b.blockedUserID = u.id
//	AND b.blockerID = (SELECT DISTINCT ub.id FROM UserInfo ub LEFT JOIN FollowingList fb ON ub.id = fb.userID WHERE ub.id = 222332)
//	)
//	GROUP BY p.postId
//	ORDER BY GREATEST(p.edittime, p.createtime) DESC
//	LIMIT 10 OFFSET 0
		Integer offsetamount = 10*(postPage-1);
		System.out.println(offsetamount);
		List<MainPagePost> rawResults = entityManager.createQuery("SELECT DISTINCT\r\n"
				+ "		(\r\n"
				+ "		SELECT DISTINCT uf.id FROM UserInfo uf \r\n"
				+ "		LEFT JOIN FollowingList ff ON uf.id = ff.userID \r\n"
				+ "		WHERE uf.id = :id\r\n"
				+ "		) \r\n"
				+ "		AS accountUser,\r\n"
				+ "		u.id ,\r\n"
				+ "		u.userAccount,\r\n"
				+ "		u.usericon,\r\n"
				+ "		u.username,\r\n"
				+ "		p.postId,\r\n"
				+ "		pImg.imgURL,\r\n"
				+ "		p.posttext,\r\n"
				+ "		p.lat,\r\n"
				+ "		p.lng,\r\n"
				+ "		p.createtime,\r\n"
				+ "		p.edittime,\r\n"
				+ "		COUNT(DISTINCT c.commentId) AS commentCount,\r\n"
				+ "		COUNT(DISTINCT l.likeId) AS likeCount,\r\n"
				+ "		CASE WHEN EXISTS \r\n"
				+ "			(\r\n"
				+ "				SELECT 1 FROM Likes postlike \r\n"
				+ "				WHERE postlike.postContextId = p.postId \r\n"
				+ "				AND postlike.userLikedId = :id\r\n"
				+ "			) 		\r\n"
				+ "		THEN 1 ELSE 0 END AS isLiked,\r\n"
				+ "		CASE WHEN EXISTS \r\n"
				+ "			(\r\n"
				+ "				SELECT 1 FROM CollectionPost postCollect\r\n"
				+ "				WHERE postCollect.postID = p.postId \r\n"
				+ "				AND postCollect.userID = :id\r\n"
				+ "			) \r\n"
				+ "		THEN 1 ELSE 0 END AS isCollected\r\n"
				+ "	FROM UserInfo u\r\n"
				+ "	INNER JOIN Post p ON u.id = p.userId\r\n"
				+ "	LEFT JOIN Comments c ON p.postId = c.postContextId\r\n"
				+ "	LEFT JOIN Likes l ON p.postId = l.postContextId\r\n"
				+ "	LEFT JOIN PostImg pImg ON p.postId = pImg.postId AND pImg.imgSerial = 0\r\n"
				+ "	WHERE u.id = :id OR u.id IN (\r\n"
				+ "		SELECT DISTINCT fl.followingUserID\r\n"
				+ "		FROM FollowingList fl\r\n"
				+ "		JOIN Post post ON fl.followingUserID = post.userId\r\n"
				+ "		WHERE fl.userID = :id\r\n"
				+ "		)\r\n"
				+ "	AND NOT EXISTS ( \r\n"
				+ "	SELECT 1 \r\n"
				+ "	FROM Blacklist b \r\n"
				+ "	WHERE b.blockedUserID = u.id\r\n"
				+ "	AND b.blockerID = (SELECT DISTINCT ub.id FROM UserInfo ub LEFT JOIN FollowingList fb ON ub.id = fb.userID WHERE ub.id = :id)\r\n"
				+ "	)\r\n"
				+ "	GROUP BY p.postId\r\n"
				+ "	ORDER BY GREATEST(p.edittime, p.createtime) DESC\r\n"
				+ "	LIMIT :setLimit OFFSET :setOffset", MainPagePost.class)
				.setParameter("id", userId)
				.setParameter("setLimit", setLimit)
				.setParameter("setOffset", offsetamount)
				.getResultList();
		
		return new Result(true, MessageConstant.ADD_MAINPAGEPOST_SUCCESS, rawResults);



//		List<Object[]> rawResults = entityManager.createQuery("SELECT DISTINCT u.id AS userID, u.userAccount, u.usericon, u.username, p.postId, p.userId AS postUserID, userPost.userAccount AS postUserAccount, userPost.usericon AS postUserIcon, userPost.username AS postUserName, pimg.imgURL, p.posttext, p.lng, p.lat, p.createtime, p.edittime, COUNT(DISTINCT c.commentId) AS commentCount, COUNT(DISTINCT l.likeId) AS likeCount FROM UserInfo u INNER JOIN FollowingList f ON u.id = f.userID LEFT JOIN Post p ON p.userId = f.followingUserID OR p.userId = u.id  LEFT JOIN Comments c ON p.postId = c.postContextId LEFT JOIN Likes l ON p.postId = l.postContextId LEFT JOIN UserInfo userPost ON p.userId = userPost.id LEFT JOIN PostImg pimg ON p.postId = pimg.postId WHERE pimg.imgSerial = 0 AND u.id = :id AND NOT EXISTS ( SELECT 1 FROM Blacklist b WHERE b.blockedUserID = u.id) GROUP BY u.id, u.userAccount, u.usericon, u.username, f.followingUserID, p.postId, userPost.userAccount, userPost.usericon, userPost.username,  pimg.imgURL, p.posttext, p.lng, p.lat, p.createtime, p.edittime ORDER BY GREATEST(p.edittime, p.createtime) DESC")
//				.setParameter("id", userId).getResultList();

////		將資料包成陣列
//		List<MainPagePost> finalResults = new ArrayList<>();
////		硬幹把資料一筆一筆撈出來並且放入 List<MainPagePost>
//		try {
//			rawResults.forEach((i) -> {
//				MainPagePost finalResult = new MainPagePost();
//
//				finalResult.setUserID((Integer) i[0]);
//				finalResult.setUserAccount((String) i[1]);
//				finalResult.setUsericon((String) i[2]);
//				finalResult.setUsername((String) i[3]);
//				finalResult.setPostId((Integer) i[4]);
//				finalResult.setPostUserID((Integer) i[5]);
//				finalResult.setPostUserAccount((String) i[6]);
//				finalResult.setPostUserIcon((String) i[7]);
//				finalResult.setPostUserName((String) i[8]);
//				finalResult.setImgURL((String) i[9]);
//				finalResult.setPosttext((String) i[10]);
//				finalResult.setLng((Double) i[11]);
//				finalResult.setLat((Double) i[12]);
//				finalResult.setCreatetime((Date) i[13]);
//				finalResult.setEdittime((Date) i[14]);
//				finalResult.setCommentCount((Long) i[15]);
//				finalResult.setLikeCount((Long) i[16]);
//
//				finalResults.add(finalResult);
//			});
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		System.out.println(finalResults);

//		return new Result(true, MessageConstant.ADD_MAINPAGEPOST_SUCCESS, finalResults);

//		List<MainPagePost> results = entityManager.createQuery("SELECT DISTINCT u.id AS userID, u.userAccount, u.usericon, u.username , p.postId, p.userId AS postUserID, userPost.userAccount AS postUserAccount, userPost.usericon AS postUserIcon, userPost.username AS postUserName, p.posttext, p.lng, p.lat, p.createtime, p.edittime, COUNT(DISTINCT c.commentId) AS CommentCount, COUNT(DISTINCT l.likeId) AS LikeCount FROM UserInfo u INNER JOIN FollowingList f ON u.id = f.userID INNER JOIN Post p ON p.userId = f.followingUserID LEFT JOIN Comments c ON p.postId = c.postContextId LEFT JOIN Likes l ON p.postId = l.postContextId LEFT JOIN UserInfo userPost ON p.userId = userPost.id WHERE NOT EXISTS ( SELECT 1 FROM Blacklist b WHERE b.blockedUserID = u.id ) GROUP BY u.id, u.userAccount, u.usericon, u.username, f.followingUserID, p.postId, p.edittime, p.createtime ORDER BY GREATEST(p.edittime, p.createtime) DESC"
//				, MainPagePost.class)
//				List<MainPagePost> results = entityManager.createQuery("SELECT DISTINCT u.id AS userID, u.userAccount, u.usericon, u.username , p.postId, p.userId AS postUserID, userPost.userAccount AS postUserAccount, userPost.usericon AS postUserIcon, userPost.username AS postUserName, p.posttext posttext, p.lng lng, p.lat lat, p.createtime creattime, p.edittime edittime, COUNT(DISTINCT c.commentId) AS commentCount, COUNT(DISTINCT l.likeId) AS likeCount FROM UserInfo u INNER JOIN FollowingList f ON u.id = f.userID INNER JOIN Post p ON p.userId = f.followingUserID LEFT JOIN Comments c ON p.postId = c.postContextId LEFT JOIN Likes l ON p.postId = l.postContextId LEFT JOIN UserInfo userPost ON p.userId = userPost.id WHERE NOT EXISTS ( SELECT 1 FROM Blacklist b WHERE b.blockedUserID = u.id ) GROUP BY u.id, u.userAccount, u.usericon, u.username, f.followingUserID, p.postId, p.edittime, p.createtime ORDER BY GREATEST(p.edittime, p.createtime) DESC",MainPagePost.class)
//                .getResultList();
//		System.out.println( results.get(0).getPosttext());
//		return new Result(true, MessageConstant.ADD_MAINPAGEPOST_SUCCESS, results);
	}

	@Override
	public Result getAllMainPagePost(Integer userId) {
		
		
		List<MainPagePost> rawResults = entityManager.createQuery("SELECT DISTINCT\r\n"
				+ "		(\r\n"
				+ "		SELECT DISTINCT uf.id FROM UserInfo uf \r\n"
				+ "		LEFT JOIN FollowingList ff ON uf.id = ff.userID \r\n"
				+ "		WHERE uf.id = :id\r\n"
				+ "		) \r\n"
				+ "		AS accountUser,\r\n"
				+ "		u.id ,\r\n"
				+ "		u.userAccount,\r\n"
				+ "		u.usericon,\r\n"
				+ "		u.username,\r\n"
				+ "		p.postId,\r\n"
				+ "		pImg.imgURL,\r\n"
				+ "		p.posttext,\r\n"
				+ "		p.lat,\r\n"
				+ "		p.lng,\r\n"
				+ "		p.createtime,\r\n"
				+ "		p.edittime,\r\n"
				+ "		COUNT(DISTINCT c.commentId) AS commentCount,\r\n"
				+ "		COUNT(DISTINCT l.likeId) AS likeCount,\r\n"
				+ "		CASE WHEN EXISTS \r\n"
				+ "			(\r\n"
				+ "				SELECT 1 FROM Likes postlike \r\n"
				+ "				WHERE postlike.postContextId = p.postId \r\n"
				+ "				AND postlike.userLikedId = :id\r\n"
				+ "			) 		\r\n"
				+ "		THEN 1 ELSE 0 END AS isLiked,\r\n"
				+ "		CASE WHEN EXISTS \r\n"
				+ "			(\r\n"
				+ "				SELECT 1 FROM CollectionPost postCollect\r\n"
				+ "				WHERE postCollect.postID = p.postId \r\n"
				+ "				AND postCollect.userID = :id\r\n"
				+ "			) \r\n"
				+ "		THEN 1 ELSE 0 END AS isCollected\r\n"
				+ "	FROM UserInfo u\r\n"
				+ "	INNER JOIN Post p ON u.id = p.userId\r\n"
				+ "	LEFT JOIN Comments c ON p.postId = c.postContextId\r\n"
				+ "	LEFT JOIN Likes l ON p.postId = l.postContextId\r\n"
				+ "	LEFT JOIN PostImg pImg ON p.postId = pImg.postId AND pImg.imgSerial = 0\r\n"
				+ "	WHERE u.id = :id OR u.id IN (\r\n"
				+ "		SELECT DISTINCT fl.followingUserID\r\n"
				+ "		FROM FollowingList fl\r\n"
				+ "		JOIN Post post ON fl.followingUserID = post.userId\r\n"
				+ "		WHERE fl.userID = :id\r\n"
				+ "		)\r\n"
				+ "	AND NOT EXISTS ( \r\n"
				+ "	SELECT 1 \r\n"
				+ "	FROM Blacklist b \r\n"
				+ "	WHERE b.blockedUserID = u.id\r\n"
				+ "	AND b.blockerID = (SELECT DISTINCT ub.id FROM UserInfo ub LEFT JOIN FollowingList fb ON ub.id = fb.userID WHERE ub.id = :id)\r\n"
				+ "	)\r\n"
				+ "	GROUP BY p.postId\r\n"
				+ "	ORDER BY GREATEST(p.edittime, p.createtime) DESC\r\n"
				, MainPagePost.class)
				.setParameter("id", userId)
				.getResultList();
		
		return new Result(true, MessageConstant.ADD_MAINPAGEPOST_SUCCESS, rawResults);

	}
	
	@Override
	public Result getLikeUsers(Integer postContextId) {
		List<LikeUsers> result = entityManager.createQuery("SELECT  l.postContextId postContextId, l.userLikedId userLikedId, uf.usericon usericon, uf.username username, uf.userAccount useraccount FROM UserInfo AS u LEFT JOIN Post p ON p.userId = u.id LEFT JOIN Likes AS l ON p.postId = l.postContextId LEFT JOIN UserInfo uf ON l.userLikedId = uf.id WHERE postContextId = :postContextId ORDER BY l.likeTime DESC"
				, LikeUsers.class)
				.setParameter("postContextId", postContextId)
				.getResultList();
		
		return new Result(true, MessageConstant.GET_LIKEUSER_SUCCESS, result);
	}
	
	@Override
	public Result getfollowingListandFanList(Integer userID) {
		List<FollowingListandFanList> result = entityManager.createQuery("SELECT DISTINCT u.id , fl.followingUserID FROM UserInfo u RIGHT JOIN FollowingList fl ON u.id = fl.userID WHERE fl.userID = :id",FollowingListandFanList.class)
				.setParameter("id", userID)
				.getResultList();
		return new Result(true, MessageConstant.GET_followList_SUCCESS, result);
	}
	

	@Override
	public Result insertFollowingList(FollowingList followingList) {
		try {
			entityManager.persist(followingList);
			}catch(Exception e) {
				return new Result(false, MessageConstant.ADD_folowing_FAILED, null);
			}
			return new Result(true, MessageConstant.ADD_folowing_SUCCESS, null);
	}


	@Override
	public Result deleteFollowingList(FollowingList followingList) {
		entityManager.createNativeQuery("DELETE FROM FollowingList WHERE followingUserID = :followingUserID AND userID = :userID")
		.setParameter("followingUserID", followingList.getFollowingUserID())
		.setParameter("userID", followingList.getUserID())
		.executeUpdate();
		return new Result(true, MessageConstant.DELETE_Liked_SUCCESS, null);
	}

	



	

}

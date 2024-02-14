package com.sns.dropcat.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.dropcat.dao.PopOutDao;
import com.sns.dropcat.model.CollectionPost;
import com.sns.dropcat.model.Comments;
import com.sns.dropcat.model.Likes;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.queryreturncalsses.PopOutCommentreply;
import com.sns.dropcat.queryreturncalsses.PopOutPost;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PopOutDaoImpl implements PopOutDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Result getPopOutData(String userid, String postid) {
//	sql:
//		SELECT a.post_id, c.user_account, c.username, c.usericon, b.imgurl, b.img_serial, a.lat, a.lng, a.posttext, a.createtime, d.comments, d.comment_time, e.user_account commentaccount,e.username commentpname, e.usericon commentpicon, COUNT(DISTINCT f.user_liked_id) likesnum, MAX(f.like_time)
//		,(SELECT COUNT(*) FROM likes WHERE user_liked_id=1 AND post_context_id=1) ilike,(SELECT COUNT(*) FROM collection_post WHERE collectorid=1 AND postid=1) icollect
//		FROM post a 
//		 JOIN post_img b ON a.post_id=b.post_id 
//		 JOIN user_info c ON a.user_id=c.id
//		 JOIN comments d ON a.post_id=d.post_context_id
//		 JOIN user_info e ON d.user_id=e.id
//		 JOIN likes f ON f.post_context_id=a.post_id
//		WHERE a.post_id = 1 GROUP BY b.imgurl, d.comments
//		ORDER BY d.comment_time ASC;
		List<Object[]> rawresults = entityManager.createQuery("SELECT a.postId, c.userAccount, c.username, c.usericon, JSON_OBJECTAGG(b.imgSerial, b.imgURL), a.lat, a.lng, a.posttext, a.createtime, d.comments, d.commentTime, e.userAccount commentaccount,e.username commentpname, e.usericon commentpicon, COUNT(DISTINCT f.userLikedId) likesnum, MAX(f.likeTime)"
				+ ",(SELECT COUNT(*) FROM Likes WHERE userLikedId= :userid1  AND postContextId= :postid1), (SELECT COUNT(*) FROM CollectionPost WHERE userID=:userid2 AND postID=:postid2)"
				+ " FROM Post a JOIN PostImg b ON a.postId=b.postId"
				+ " JOIN UserInfo c ON a.userId=c.id"
				+ " LEFT JOIN Comments d ON a.postId=d.postContextId"
				+ " LEFT JOIN UserInfo e ON d.userId=e.id"
				+ " LEFT JOIN Likes f ON f.postContextId=a.postId"
				+ " WHERE a.postId = :postid3 GROUP BY d.comments"
				+ " ORDER BY d.commentTime ASC").setParameter("userid1", userid).setParameter("postid1", postid).setParameter("postid2", postid).setParameter("userid2", userid).setParameter("postid3", postid).getResultList();
		
		PopOutPost finalresult = new PopOutPost();
		finalresult.setAccountname((String)rawresults.get(0)[1]);
		finalresult.setAcconticon((String)rawresults.get(0)[3]);
		finalresult.setLat((Double)rawresults.get(0)[5]);
		finalresult.setLng((Double)rawresults.get(0)[6]);
		finalresult.setPosttext((String)rawresults.get(0)[7]);
		finalresult.setPosttime((Date)rawresults.get(0)[8]);
		finalresult.setTotallike((Long)rawresults.get(0)[14]);
		finalresult.setLatestliketime((Date)rawresults.get(0)[15]);
		finalresult.setLiked((Long)rawresults.get(0)[16]);
		finalresult.setCollected((Long)rawresults.get(0)[17]);
		List<String> imgs = new ArrayList<String>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> imgmap = objectMapper.readValue((String)rawresults.get(0)[4], new TypeReference<HashMap<String, String>>() {});
			for(int i =0;i<imgmap.size();i++) {
				imgs.add(imgmap.get(String.valueOf(i)));
			}
//			imgmap.forEach((u,v)->{
//				System.out.println(Integer.parseInt(u));
//				imgs.add(Integer.parseInt(u), v);
//				
//			});
		}catch(Exception e) {
			System.out.println(e);
		}
		finalresult.setPostimg(imgs);
		List<Map<String,Object>> comments= new ArrayList<Map<String,Object>>();
		rawresults.forEach((objarr)->{
			Map<String,Object> comment = new HashMap<String, Object>();
			comment.put("comment", objarr[9]);
			comment.put("commenttime", objarr[10]);
			comment.put("commentaccount", objarr[11]);
			comment.put("commentuname", objarr[12]);
			comment.put("commentuicon", objarr[13]);
			comments.add(comment);
		});
		finalresult.setPostMessage(comments);
		
		return new Result(true, MessageConstant.GET_PopData_SUCCESS, finalresult);
	}

	@Override
	public Result insertLiked(Likes like) {
//		sql:INSERT INTO Likes(likeTime, postContextId, userLikedId) VALUES (NOW(), 1,5);
		try {
		entityManager.persist(like);
		}catch(Exception e) {
			return new Result(false, MessageConstant.ADD_Liked_FAILED, null);
		}
		return new Result(true, MessageConstant.ADD_Liked_SUCCESS, null);
	}

	@Override
	public Result deleteLiked(Likes like) {
		entityManager.createNativeQuery("DELETE FROM Likes where postContextId=:postContextId and userLikedId=:userLikedId")
		.setParameter("postContextId", like.getPostContextId()).setParameter("userLikedId", like.getUserLikedId()).executeUpdate();
		return new Result(true, MessageConstant.DELETE_Liked_SUCCESS, null);
	}

	@Override
	public Result insertCollected(CollectionPost collectionPost) {
		try {
			entityManager.persist(collectionPost);
			}catch(Exception e) {
				return new Result(false, MessageConstant.ADD_Collected_FAILED, null);
			}
			
		return new Result(true, MessageConstant.ADD_Collected_SUCCESS, null);
	}

	@Override
	public Result deleteCollected(CollectionPost collectionPost) {
		entityManager.createNativeQuery("DELETE FROM CollectionPost where postID=:postID and userID=:userID")
		.setParameter("postID", collectionPost.getPostID()).setParameter("userID", collectionPost.getUserID()).executeUpdate();
		return new Result(true, MessageConstant.DELETE_Collected_SUCCESS, null);
	}

	@Override
	public Result insertComment(Comments comments) {
		try {
			entityManager.persist(comments);
			}catch(Exception e) {
				List<PopOutCommentreply> res = entityManager.createNativeQuery("SELECT a.commentId, a.commentTime, a.comments, a.postContextId, b.userAccount, b.usericon FROM Comments a JOIN UserInfo b ON a.userId=b.id WHERE postContextId=:postContextId",PopOutCommentreply.class)
						                          .setParameter("postContextId", comments.getPostContextId()).getResultList();
				return new Result(false, MessageConstant.ADD_Comment_FAILED, res);
			}
		List<PopOutCommentreply> res = entityManager.createNativeQuery("SELECT a.commentId, a.commentTime, a.comments, a.postContextId, b.userAccount, b.usericon FROM Comments a JOIN UserInfo b ON a.userId=b.id WHERE postContextId=:postContextId",PopOutCommentreply.class)
                .setParameter("postContextId", comments.getPostContextId()).getResultList();	
		return new Result(true, MessageConstant.ADD_Comment_SUCCESS, res);
	}

	@Override
	public Result getLikeNum(String postId) {
		List<Object[]> res = entityManager.createNativeQuery("select count(*),MAX(likeTime)  from Likes where postContextId=:postId").setParameter("postId", postId).getResultList();
		return new Result(true, MessageConstant.GET_Likenum_Success, res);
	}
//以下是刪除相關

	@Override
	public Integer deletepost(String postId, String userId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM Post where postId=:postId and userId=:userId")
		.setParameter("postId", postId).setParameter("userId", userId).executeUpdate();
		return res;
	}

	@Override
	public Integer deleteCollectionPost(String postId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM CollectionPost where postID=:postID")
				.setParameter("postID", postId).executeUpdate();
		return res;
	}

	@Override
	public Integer deleteComments(String postId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM Comments where postContextId=:postID")
				.setParameter("postID", postId).executeUpdate();
		return res;
	}

	@Override
	public Integer deleteinformation(String postId, String userId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM Information where postId=:postID and userId=:userID")
				.setParameter("postID", postId).setParameter("userID", userId).executeUpdate();
		return res;
	}

	@Override
	public Integer deleteLikes(String postId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM Likes where postContextId=:postID")
				.setParameter("postID", postId).executeUpdate();
		return res;
	}

	@Override
	public List<PostImg> deletePostImg(String postId) {
		List<PostImg> imgs= entityManager.createNativeQuery("Select * FROM PostImg where postId=:postID", PostImg.class)
		.setParameter("postID", postId).getResultList();
		Integer res =entityManager.createNativeQuery("DELETE FROM PostImg where postId=:postID")
				.setParameter("postID", postId).executeUpdate();
		return imgs;
	}

	@Override
	public Integer deleteSufingHistory(String postId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM SurfingHistory where postID=:postID")
				.setParameter("postID", postId).executeUpdate();
		return res;
	}

	@Override
	public Integer deletetellYouHaveLike(String postId) {
		Integer res =entityManager.createNativeQuery("DELETE FROM tellYouHaveLike where give_You_Like_PostId=:postID")
				.setParameter("postID", postId).executeUpdate();
		return res;
	}
	
	
	
}

package com.sns.dropcat.dao;

import java.util.List;

import com.sns.dropcat.model.CollectionPost;
import com.sns.dropcat.model.Comments;
import com.sns.dropcat.model.Likes;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.replyfomat.Result;

public interface PopOutDao {
	Result getPopOutData(String userid, String postid);
	Result insertLiked(Likes like);
	Result deleteLiked(Likes like);
	Result insertCollected(CollectionPost collectionPost);
	Result deleteCollected(CollectionPost collectionPost);
	Result insertComment(Comments comments);
	Result getLikeNum(String postId);
	Integer deletepost(String postId, String userId);
	Integer deleteinformation(String postId, String userId);
	List<PostImg> deletePostImg(String postId);
	Integer deleteComments(String postId);
	Integer deleteCollectionPost(String postId);
	Integer deleteLikes(String postId);
	Integer deleteSufingHistory(String postId);
	Integer deletetellYouHaveLike(String postId);
	
}

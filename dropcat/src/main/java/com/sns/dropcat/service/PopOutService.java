package com.sns.dropcat.service;

import com.sns.dropcat.model.CollectionPost;
import com.sns.dropcat.model.Comments;
import com.sns.dropcat.model.Likes;
import com.sns.dropcat.replyfomat.Result;

public interface PopOutService {
	Result getPopOutData(String userid, String postid);
	Result insertLiked(Likes like);
	Result deleteLiked(Likes like);
	Result insertCollected(CollectionPost collectionPost);
	Result deleteCollected(CollectionPost collectionPost);
	Result insertComment(Comments comments);
	Result getLikeNum(String postId);
	Result deletepost(String postId, String userId);
}

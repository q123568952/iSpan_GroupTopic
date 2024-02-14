package com.sns.dropcat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.AwsS3Dao;
import com.sns.dropcat.dao.PopOutDao;
import com.sns.dropcat.dao.UserPreferenceDao;
import com.sns.dropcat.model.CollectionPost;
import com.sns.dropcat.model.Comments;
import com.sns.dropcat.model.Likes;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.PopOutService;

@Service
public class PopOutServiceImpl implements PopOutService {
	
	private PopOutDao popOutDao;
	private UserPreferenceDao userPreferenceDao;
	private AwsS3Dao awsS3Dao;
	
	@Autowired
	public PopOutServiceImpl(PopOutDao popOutDao,UserPreferenceDao userPreferenceDao,AwsS3Dao awsS3Dao) {
		super();
		this.popOutDao = popOutDao;
		this.userPreferenceDao = userPreferenceDao;
		this.awsS3Dao = awsS3Dao;
	}



	@Override
	@Transactional
	public Result getPopOutData(String userid, String postid) {
		Result res = popOutDao.getPopOutData(userid,postid);
		userPreferenceDao.addUserPreference(userid, postid);
		userPreferenceDao.addUserRecord(userid, postid, new Date());
		return res;
	}



	@Override
	@Transactional
	public Result insertLiked(Likes like) {
		like.setLikeTime(new Date());
		Result res = popOutDao.insertLiked(like);
		return res;
	}



	@Override
	@Transactional
	public Result deleteLiked(Likes like) {
		Result res = popOutDao.deleteLiked(like);
		return res;
	}



	@Override
	@Transactional
	public Result insertCollected(CollectionPost collectionPost) {
		collectionPost.setCollectTime(new Date());
		Result res = popOutDao.insertCollected(collectionPost);
		return res;
	}



	@Override
	@Transactional
	public Result deleteCollected(CollectionPost collectionPost) {
		Result res = popOutDao.deleteCollected(collectionPost);
		return res;
	}



	@Override
	@Transactional
	public Result insertComment(Comments comments) {
		comments.setCommentTime(new Date());
		Result res =popOutDao.insertComment(comments);
		return res;
	}



	@Override
	public Result getLikeNum(String postId) {
		Result res = popOutDao.getLikeNum(postId);
		return res;
	}



	@Override
	@Transactional
	public Result deletepost(String postId, String userId) {
		Integer postdelete = popOutDao.deletepost(postId,userId);
		if(postdelete!=0) {
			Integer collectiondelete = popOutDao.deleteCollectionPost(postId);
			Integer commentdelete = popOutDao.deleteComments(postId);
			Integer infodelete = popOutDao.deleteinformation(postId,userId);
			Integer likedelete = popOutDao.deleteLikes(postId);
			Integer tellYouHaveLikedelete = popOutDao.deletetellYouHaveLike(postId);
			Integer sufingHistoryelete = popOutDao.deleteSufingHistory(postId);
		List<PostImg> imgdelete = popOutDao.deletePostImg(postId);
		Boolean awsimgdelete= awsS3Dao.deleteawspost(imgdelete);
		return new Result(true,null,null);
		}
		return new Result(false,null,null);
	}

}

package com.sns.dropcat.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.dropcat.dao.PostDao;
import com.sns.dropcat.model.FollowingList;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.PostService;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService {

	PostDao postDao;

	@Autowired
	public PostServiceImpl(PostDao postDao) {
		super();
		this.postDao = postDao;
	}
	
	@Override
	@Transactional
	public Result getAllMainPagePost(Integer userId) {
		Result res = postDao.getAllMainPagePost(userId);
		return res;
	}

	@Override
	@Transactional
	public Result getMainPagePost(Integer userId, Integer postPage,Integer setLimit) {
		Result res = postDao.getMainPagePost(userId, postPage, setLimit);
		return res;
	}


	@Override
	@Transactional
	public Result getLikeUsers(Integer postContextId) {
		Result res = postDao.getLikeUsers(postContextId);
		return res;
	}
	
	

	@Override
	@Transactional
	public Result getfollowingListandFanList(Integer userID) {
		Result res = postDao.getfollowingListandFanList(userID);

		return res;
	}

	@Override
	@Transactional
	public Result insertFollowingList(FollowingList followingList) {
		followingList.setFollowTime(new Date());
		Result res = postDao.insertFollowingList(followingList);
		return res;
	}


	@Override
	@Transactional
	public Result deleteFollowingList(FollowingList followingList) {
		Result res = postDao.deleteFollowingList(followingList);
		return res;
	}


}

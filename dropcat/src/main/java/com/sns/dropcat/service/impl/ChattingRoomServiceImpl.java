package com.sns.dropcat.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.ChattingRoomDao;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.ChattingRoomService;

@Service
public class ChattingRoomServiceImpl implements ChattingRoomService {

	ChattingRoomDao chattingRoomDao;
	
	
	public ChattingRoomServiceImpl(ChattingRoomDao chattingRoomDao) {
		super();
		this.chattingRoomDao = chattingRoomDao;
	}

	@Override
	@Transactional
	public Result getChatHistory(Integer userID, Integer followingUserID) {
		Result CHResult = chattingRoomDao.getChatHistory(userID, followingUserID);
		return CHResult;
	}


	@Override
	@Transactional
	public Result getChatFollowers(Integer userID) {
		Result CFResult = chattingRoomDao.getChatFollowers(userID);
		return CFResult;
	}

}

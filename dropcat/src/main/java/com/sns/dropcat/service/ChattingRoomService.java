package com.sns.dropcat.service;

import com.sns.dropcat.replyfomat.Result;

public interface ChattingRoomService {
	
	Result getChatHistory(Integer userID, Integer followingUserID);
	
	Result getChatFollowers(Integer userID);
}

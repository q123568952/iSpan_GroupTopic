package com.sns.dropcat.dao;

import com.sns.dropcat.replyfomat.Result;

public interface ChattingRoomDao {
	
	Result getChatHistory(Integer userID, Integer followingUserID);
	
	Result getChatFollowers(Integer userID);
}

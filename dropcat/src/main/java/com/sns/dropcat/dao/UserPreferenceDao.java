package com.sns.dropcat.dao;

import java.util.Date;

import com.sns.dropcat.replyfomat.Result;


public interface UserPreferenceDao {
	
	Result addUserPreference(String userId, String postId);

	Result addUserRecord(String userid, String postid, Date date);
}

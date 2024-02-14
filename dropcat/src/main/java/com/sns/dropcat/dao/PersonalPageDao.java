package com.sns.dropcat.dao;

import com.sns.dropcat.model.Blacklist;
import com.sns.dropcat.replyfomat.Result;

public interface PersonalPageDao {
	
	Result getPersonalPageUser(String userAccount);
	Result getPersonalPagePost(String userAccount);
	Result getPersonalPageCollection(String userId);
	Result getPersonalPageSurfingHistory(String userId);
	Result getTotalFollowing(String userID);
	Result getfollowingstatuas(String userId, String userAccount);
	Result getblackstatuas(String userId, String userAccount);
	Result insertblackList(Blacklist blackList);
	Result deleteblackList(Blacklist blackList);

	
}

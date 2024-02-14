package com.sns.dropcat.service.impl;

import com.sns.dropcat.replyfomat.Result;

public interface RelationshipDao {

	Result whofollowme(String userId);

	Result whoifollow(String userId);

}

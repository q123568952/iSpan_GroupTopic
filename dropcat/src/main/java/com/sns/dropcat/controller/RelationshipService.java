package com.sns.dropcat.controller;

import com.sns.dropcat.replyfomat.Result;

public interface RelationshipService {

	Result whofollowme(String userId);

	Result whoifollow(String userId);
}

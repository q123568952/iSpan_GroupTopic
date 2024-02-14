package com.sns.dropcat.service;

import java.util.Date;
import java.util.List;

import com.sns.dropcat.model.typeKeys;
import com.sns.dropcat.replyfomat.Result;

public interface ExploreService {
	Result getExplorePost();
	Result getMapPost(Double lat, Double lng, Integer dis);
	Result uploadExplorePost(Integer page, Integer uploadamount, Date nowTime, String userId);
	Result uploadpostnopreferdata(Integer page, Integer uploadamount, Date nowTime, String userId, Integer lastNum);
	
}

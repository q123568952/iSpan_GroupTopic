package com.sns.dropcat.dao;

import java.util.Date;

import com.sns.dropcat.replyfomat.Result;

public interface ExploreDao {
	Result getExplorePost();
	Result getMapPost(Double lat, Double lng, Integer dis);
	Result uploadExplorePost(Integer page, Integer uploadamount, Date nowTime, String userId);
	Result uploadpostnopreferdata(Integer page, Integer uploadamount, Date nowTime, String userId, Integer lastNum);
}

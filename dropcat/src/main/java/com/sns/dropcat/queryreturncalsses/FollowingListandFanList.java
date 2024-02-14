package com.sns.dropcat.queryreturncalsses;

public class FollowingListandFanList {
	private Integer id;
	private Integer followingUserID;
	public FollowingListandFanList(Integer id, Integer followingUserID) {
		super();
		this.id = id;
		this.followingUserID = followingUserID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFollowingUserID() {
		return followingUserID;
	}
	public void setFollowingUserID(Integer followingUserID) {
		this.followingUserID = followingUserID;
	}
}

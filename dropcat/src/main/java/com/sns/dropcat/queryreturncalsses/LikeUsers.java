package com.sns.dropcat.queryreturncalsses;

public class LikeUsers {
	private Integer postContextId;
	private Integer userLikedId;
	private String usericon;
	private String username;
	private String useraccount;
	public LikeUsers(Integer postContextId, Integer userLikedId, String usericon, String username, String useraccount) {
		super();
		this.postContextId = postContextId;
		this.userLikedId = userLikedId;
		this.usericon = usericon;
		this.username = username;
		this.useraccount = useraccount;
	}
	public Integer getPostContextId() {
		return postContextId;
	}
	public void setPostContextId(Integer postContextId) {
		this.postContextId = postContextId;
	}
	public Integer getUserLikedId() {
		return userLikedId;
	}
	public void setUserLikedId(Integer userLikedId) {
		this.userLikedId = userLikedId;
	}
	public String getUsericon() {
		return usericon;
	}
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	
	
	
}

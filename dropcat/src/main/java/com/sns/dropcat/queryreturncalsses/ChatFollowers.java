package com.sns.dropcat.queryreturncalsses;

public class ChatFollowers {
	
	private Integer followingUserID;
	private String username;
	private String userIcon;
	private String messages;
	
	public ChatFollowers(Integer followingUserID, String username, String userIcon, String messages) {
		super();
		this.followingUserID = followingUserID;
		this.username = username;
		this.userIcon = userIcon;
		this.messages = messages;
	}
	public Integer getFollowingUserID() {
		return followingUserID;
	}
	public void setFollowingUserID(Integer followingUserID) {
		this.followingUserID = followingUserID;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}

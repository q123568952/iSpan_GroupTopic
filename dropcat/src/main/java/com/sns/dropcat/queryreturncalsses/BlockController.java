package com.sns.dropcat.queryreturncalsses;

public class BlockController {
	private Integer id;
	private String openState;
	private Integer blockedUserID;
	private String usericon;
	private String userAccount;
	public BlockController(Integer id, String openState, Integer blockedUserID, String usericon, String userAccount) {
		super();
		this.id = id;
		this.openState = openState;
		this.blockedUserID = blockedUserID;
		this.usericon = usericon;
		this.userAccount = userAccount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenState() {
		return openState;
	}
	public void setOpenState(String openState) {
		this.openState = openState;
	}
	public Integer getBlockedUserID() {
		return blockedUserID;
	}
	public void setBlockedUserID(Integer blockedUserID) {
		this.blockedUserID = blockedUserID;
	}
	public String getUsericon() {
		return usericon;
	}
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
}

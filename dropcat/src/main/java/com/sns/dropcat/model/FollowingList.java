package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FollowingList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer followingListID;
	private Integer userID;
	private Integer followingUserID;
	@Column(columnDefinition = "DATETIME")
	private Date followTime;
	
	public FollowingList(Integer followingListID, Integer userID, Integer followingUserID, Date followTime) {
		super();
		this.followingListID = followingListID;
		this.userID = userID;
		this.followingUserID = followingUserID;
		this.followTime = followTime;
	}

	public Integer getFollowingListID() {
		return followingListID;
	}

	public void setFollowingListID(Integer followingListID) {
		this.followingListID = followingListID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getFollowingUserID() {
		return followingUserID;
	}

	public void setFollowingUserID(Integer followingUserID) {
		this.followingUserID = followingUserID;
	}

	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}
	
	
}

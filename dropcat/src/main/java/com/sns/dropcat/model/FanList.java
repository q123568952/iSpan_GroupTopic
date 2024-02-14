package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FanList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer FanListID;
	private Integer userID;
	private Integer fanID;
	@Column(columnDefinition = "DATETIME")
	private Date followTime;
	
	public FanList(Integer fanListID, Integer userID, Integer fanID, Date followTime) {
		super();
		FanListID = fanListID;
		this.userID = userID;
		this.fanID = fanID;
		this.followTime = followTime;
	}

	public Integer getFanListID() {
		return FanListID;
	}

	public void setFanListID(Integer fanListID) {
		FanListID = fanListID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getFanID() {
		return fanID;
	}

	public void setFanID(Integer fanID) {
		this.fanID = fanID;
	}

	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}

	
}

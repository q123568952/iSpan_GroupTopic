package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blacklist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blacklistID;
	
	private Integer blockerID;
	
	private Integer blockedUserID;
	
	@Column(columnDefinition = "DATETIME NOT NULL")
	private Date blockTime;
	
	public Blacklist(Integer blacklistID, Integer blockerID, Integer blockedUserID, Date blockTime) {
		super();
		this.blacklistID = blacklistID;
		this.blockerID = blockerID;
		this.blockedUserID = blockedUserID;
		this.blockTime = blockTime;
		
	}

	public Integer getBlacklistID() {
		return blacklistID;
	}

	public void setBlacklistID(Integer blacklistID) {
		this.blacklistID = blacklistID;
	}

	public Integer getBlockerID() {
		return blockerID;
	}

	public void setBlockerID(Integer blockerID) {
		this.blockerID = blockerID;
	}

	public Integer getBlockedUserID() {
		return blockedUserID;
	}

	public void setBlockedUserID(Integer blockedUserID) {
		this.blockedUserID = blockedUserID;
	}

	public Date getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(Date blockTime) {
		this.blockTime = blockTime;
	}

	
}

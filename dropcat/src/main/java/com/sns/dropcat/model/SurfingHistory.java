package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SurfingHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer surfingHistoryID;
	private Integer surfingUserID;
    private Integer postID;
    @Column(columnDefinition = "DATETIME")
    private Date surfingTime;
    
	public SurfingHistory(Integer surfingHistoryID, Integer surfingUserID, Integer postID, Date surfingTime) {
		super();
		this.surfingHistoryID = surfingHistoryID;
		this.surfingUserID = surfingUserID;
		this.postID = postID;
		this.surfingTime = surfingTime;
	}

	public Integer getSurfingHistoryID() {
		return surfingHistoryID;
	}

	public void setSurfingHistoryID(Integer surfingHistoryID) {
		this.surfingHistoryID = surfingHistoryID;
	}

	public Integer getSurfingUserID() {
		return surfingUserID;
	}

	public void setSurfingUserID(Integer surfingUserID) {
		this.surfingUserID = surfingUserID;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public Date getSurfingTime() {
		return surfingTime;
	}

	public void setSurfingTime(Date surfingTime) {
		this.surfingTime = surfingTime;
	}

	
    
}

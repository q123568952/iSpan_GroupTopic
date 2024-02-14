package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Likes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer likeId;
	
	private Integer postContextId;
	
	private Integer userLikedId;
	
	@Column(columnDefinition = "DATETIME")
	private Date likeTime;

	public Likes(Integer likeId, Integer postContextId, Integer userLikedId, Date likeTime) {
		super();
		this.likeId = likeId;
		this.postContextId = postContextId;
		this.userLikedId = userLikedId;
		this.likeTime = likeTime;
	}

	public Integer getLikeId() {
		return likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
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

	public Date getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}

	
	
}

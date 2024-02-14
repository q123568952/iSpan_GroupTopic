package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CollectionPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer collectionPostID;
	
	
	private Integer userID;
	
	private Integer postID;
	
	@Column(columnDefinition = "DATETIME NOT NULL")
	private Date collectTime;
	
	public CollectionPost(Integer collectionPostID, Integer userID, Integer postID, Date collectTime) {
		super();
		this.collectionPostID = collectionPostID;
		this.userID = userID;
		this.postID = postID;
		this.collectTime = collectTime;
		
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getCollectionPostID() {
		return collectionPostID;
	}

	public void setCollectionPostID(Integer collectionPostID) {
		this.collectionPostID = collectionPostID;
	}

	public Integer getcollectorID() {
		return userID;
	}

	public void setcollectorID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	
}

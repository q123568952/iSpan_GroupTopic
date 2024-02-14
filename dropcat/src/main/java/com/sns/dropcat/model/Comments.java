package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	private Integer userId;
	
	private Integer postContextId;
	
	@Column(columnDefinition = "VARCHAR(5000) ")
	private String comments;
	
	@Column(columnDefinition = "DATETIME")
	private Date commentTime;

	public Comments(Integer commentId, Integer userId, Integer postContextId, String comments, Date commentTime) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.postContextId = postContextId;
		this.comments = comments;
		this.commentTime = commentTime;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPostContextId() {
		return postContextId;
	}

	public void setPostContextId(Integer postContextId) {
		this.postContextId = postContextId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	
}

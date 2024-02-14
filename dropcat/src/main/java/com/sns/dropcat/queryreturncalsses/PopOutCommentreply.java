package com.sns.dropcat.queryreturncalsses;

import java.util.Date;

public class PopOutCommentreply {
	private Integer commentId;
	private Date commenttime;
	private String comment;
	private Integer postContextId;
	private String commentaccount;
	private String commentuicon;
	public PopOutCommentreply(Integer commentId, Date commenttime, String comment, Integer postContextId,
			String commentaccount, String commentuicon) {
		super();
		this.commentId = commentId;
		this.commenttime = commenttime;
		this.comment = comment;
		this.postContextId = postContextId;
		this.commentaccount = commentaccount;
		this.commentuicon = commentuicon;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Date getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getPostContextId() {
		return postContextId;
	}
	public void setPostContextId(Integer postContextId) {
		this.postContextId = postContextId;
	}
	public String getCommentaccount() {
		return commentaccount;
	}
	public void setCommentaccount(String commentaccount) {
		this.commentaccount = commentaccount;
	}
	public String getCommentuicon() {
		return commentuicon;
	}
	public void setCommentuicon(String commentuicon) {
		this.commentuicon = commentuicon;
	}
	
}

package com.sns.dropcat.queryreturncalsses;

import java.util.Date;

import jakarta.persistence.Column;




public class InformationJoinFollowingJoinPost 
{
	
	private Integer userID;
	private Integer othersUserID;
	private Integer postId;
	private String postType;
	private Date   postSettingTime;
	private String imgURL;
	
	
	public InformationJoinFollowingJoinPost() {
		super();
	}


	public InformationJoinFollowingJoinPost(Integer userID, Integer othersUserID, Integer postId, String postType,
			Date postSettingTime, String imgURL) {
		super();
		this.userID = userID;
		this.othersUserID = othersUserID;
		this.postId = postId;
		this.postType = postType;
		this.postSettingTime = postSettingTime;
		this.imgURL = imgURL;
	}


	public Integer getUserID() {
		return userID;
	}


	public void setUserID(Integer userID) {
		this.userID = userID;
	}


	public Integer getOthersUserID() {
		return othersUserID;
	}


	public void setOthersUserID(Integer othersUserID) {
		this.othersUserID = othersUserID;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public String getPostType() {
		return postType;
	}


	public void setPostType(String postType) {
		this.postType = postType;
	}


	public Date getPostSettingTime() {
		return postSettingTime;
	}


	public void setPostSettingTime(Date postSettingTime) {
		this.postSettingTime = postSettingTime;
	}


	public String getImgURL() {
		return imgURL;
	}


	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}


	
	

	
	
	

}

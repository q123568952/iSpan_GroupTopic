package com.sns.dropcat.queryreturncalsses;

public class PersonalPagePost {
	
	private Integer userID;
	private Integer postId;
	private String imgURL;
	private Long numOfLikes;
	private Long numOfComment;
	
	public PersonalPagePost(Integer userID, Integer postId, String imgURL, Long numOfLikes, Long numOfComment) {
		super();
		this.userID = userID;
		this.postId = postId;
		this.imgURL = imgURL;
		this.numOfLikes = numOfLikes;
		this.numOfComment = numOfComment;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Long getNumOfLikes() {
		return numOfLikes;
	}

	public void setNumOfLikes(Long numOfLikes) {
		this.numOfLikes = numOfLikes;
	}

	public Long getNumOfComment() {
		return numOfComment;
	}

	public void setNumOfComment(Long numOfComment) {
		this.numOfComment = numOfComment;
	}
	
	
}

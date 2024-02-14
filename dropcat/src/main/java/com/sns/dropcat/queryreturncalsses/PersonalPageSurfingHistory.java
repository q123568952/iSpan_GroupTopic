package com.sns.dropcat.queryreturncalsses;

public class PersonalPageSurfingHistory {
	
	private Integer userID;
	private Integer postID;
	private String postText;
	private String imgURL;
	private Long numOfLikes;
	private Long numOfComment;
	
	public PersonalPageSurfingHistory(Integer userID, Integer postID, String postText, String imgURL, Long numOfLikes,
			Long numOfComment) {
		super();
		this.userID = userID;
		this.postID = postID;
		this.postText = postText;
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

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
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

package com.sns.dropcat.queryreturncalsses;

public class PersonalPageCollection {
	
	private Integer postID;
	private Integer userID;
	private String postText;
	private String imgURL;
	private Long NumOfLikes;
	private Long NumOfComment;

	public PersonalPageCollection(Integer postID, Integer userID, String postText, String imgURL, Long numOfLikes,
			Long numOfComment) {
		super();
		this.postID = postID;
		this.userID = userID;
		this.postText = postText;
		this.imgURL = imgURL;
		NumOfLikes = numOfLikes;
		NumOfComment = numOfComment;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public Long getNumOfLikes() {
		return NumOfLikes;
	}

	public void setNumOfLikes(Long numOfLikes) {
		NumOfLikes = numOfLikes;
	}

	public Long getNumOfComment() {
		return NumOfComment;
	}

	public void setNumOfComment(Long numOfComment) {
		NumOfComment = numOfComment;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	
}

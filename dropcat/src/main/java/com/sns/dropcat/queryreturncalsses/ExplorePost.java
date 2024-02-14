package com.sns.dropcat.queryreturncalsses;

public class ExplorePost {
	private Integer postId;
	private String img;
	private Long likes;
	private Long message;
	public ExplorePost(Integer postId, String img, Long likes, Long message) {
		super();
		this.postId = postId;
		this.img = img;
		this.likes = likes;
		this.message = message;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	public Long getMessage() {
		return message;
	}
	public void setMessage(Long message) {
		this.message = message;
	}
	
	
}

package com.sns.dropcat.queryreturncalsses;

import java.sql.Timestamp;	

public class MainPagePost {
	private Integer accountUser;
	private Integer id;
	private String userAccount;
	private String usericon;
	private String username;
	private Integer postId;
	private String imgURL;
	private String posttext;
	private Double lat;
	private Double lng;
	private Timestamp createtime;
	private Timestamp edittime;
	private Long commentCount;
	private Long likeCount;
	private Integer isLiked;
	private Integer isCollected;
	public MainPagePost(Integer accountUser, Integer id, String userAccount, String usericon, String username,
			Integer postId, String imgURL, String posttext, Double lat, Double lng, Timestamp createtime,
			Timestamp edittime, Long commentCount, Long likeCount, Integer isLiked, Integer isCollected) {
		super();
		this.accountUser = accountUser;
		this.id = id;
		this.userAccount = userAccount;
		this.usericon = usericon;
		this.username = username;
		this.postId = postId;
		this.imgURL = imgURL;
		this.posttext = posttext;
		this.lat = lat;
		this.lng = lng;
		this.createtime = createtime;
		this.edittime = edittime;
		this.commentCount = commentCount;
		this.likeCount = likeCount;
		this.isLiked = isLiked;
		this.isCollected = isCollected;
	}
	public Integer getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(Integer accountUser) {
		this.accountUser = accountUser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUsericon() {
		return usericon;
	}
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getPosttext() {
		return posttext;
	}
	public void setPosttext(String posttext) {
		this.posttext = posttext;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getEdittime() {
		return edittime;
	}
	public void setEdittime(Timestamp edittime) {
		this.edittime = edittime;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Integer isLiked) {
		this.isLiked = isLiked;
	}
	public Integer getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Integer isCollected) {
		this.isCollected = isCollected;
	}
	
	
}

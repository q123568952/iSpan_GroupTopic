package com.sns.dropcat.queryreturncalsses;

import java.math.BigDecimal;

public class ExploreMap {
	private Integer postId;
	private String img;
	private BigDecimal lat;
	private BigDecimal lng;
	private String userAccount;
	
	public ExploreMap(Integer postId, String img, BigDecimal lat, BigDecimal lng, String userAccount) {
		super();
		this.postId = postId;
		this.img = img;
		this.lat = lat;
		this.lng = lng;
		this.userAccount = userAccount;
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
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
}

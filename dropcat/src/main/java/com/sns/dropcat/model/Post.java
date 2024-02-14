package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Post {
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	private Integer userId;
	
	@Column(columnDefinition = "VARCHAR(2000)")
	private String posttext;
	
	@Column(columnDefinition = "DECIMAL(8,5)")
	private Double lng;
	
	@Column(columnDefinition = "DECIMAL(7,5)")
	private Double lat;
	
	@Column(columnDefinition = "DATETIME")
	private Date createtime;
	@Column(columnDefinition = "DATETIME")
	private Date edittime;
	
	private Integer postType;
	




	public Post() {
		super();
	}





public Post(Integer postId, Integer userId, String posttext, Double lng, Double lat, Date createtime, Date edittime,
		Integer postType) {
	super();
	this.postId = postId;
	this.userId = userId;
	this.posttext = posttext;
	this.lng = lng;
	this.lat = lat;
	this.createtime = createtime;
	this.edittime = edittime;
	this.postType = postType;
}





public Integer getPostId() {
	return postId;
}





public void setPostId(Integer postId) {
	this.postId = postId;
}





public Integer getUserId() {
	return userId;
}





public void setUserId(Integer userId) {
	this.userId = userId;
}





public String getPosttext() {
	return posttext;
}





public void setPosttext(String posttext) {
	this.posttext = posttext;
}





public Double getLng() {
	return lng;
}





public void setLng(Double lng) {
	this.lng = lng;
}





public Double getLat() {
	return lat;
}





public void setLat(Double lat) {
	this.lat = lat;
}





public Date getCreatetime() {
	return createtime;
}





public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}





public Date getEdittime() {
	return edittime;
}





public void setEdittime(Date edittime) {
	this.edittime = edittime;
}





public Integer getPostType() {
	return postType;
}





public void setPostType(Integer postType) {
	this.postType = postType;
}







	
	
	

	
	
	

	
	
	

}

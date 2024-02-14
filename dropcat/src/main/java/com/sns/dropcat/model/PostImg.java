package com.sns.dropcat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PostImg {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer imgId;
	private Integer postId;
	
	@Column(columnDefinition = "VARCHAR(2000) NOT NULL")
	private String imgURL;
	
	private Integer imgSerial;

	
	public PostImg() {
		super();
	}

	public PostImg(String imgURL) {
		super();
		this.imgURL = imgURL;
	}

	public PostImg(Integer imgId, Integer postId, String imgURL, Integer imgSerial) {
		super();
		this.imgId = imgId;
		this.postId = postId;
		this.imgURL = imgURL;
		this.imgSerial = imgSerial;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
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

	public Integer getImgSerial() {
		return imgSerial;
	}

	public void setImgSerial(Integer imgSerial) {
		this.imgSerial = imgSerial;
	}
	
	
}

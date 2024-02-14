package com.sns.dropcat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "exploretest")
public class Posttest {
 
	@Override
	public String toString() {
		return "Posttest [id=" + id + ", img=" + img + ", like=" + likes + ", message=" + message + "]";
	}
	@Id
     private Integer id;
	private String img;
	 private Integer likes;
	 private Integer message;
	 
	 
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getLike() {
		return likes;
	}
	public void setLike(Integer likes) {
		this.likes = likes;
	}
	public Integer getMessage() {
		return message;
	}
	public void setMessage(Integer message) {
		this.message = message;
	}
	
 
}

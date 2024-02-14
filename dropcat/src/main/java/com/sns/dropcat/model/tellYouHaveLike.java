package com.sns.dropcat.model;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class tellYouHaveLike 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 發文的用戶id
	private Integer post_userId;
	
	private Integer give_You_Like_PostId;
	
	private Integer give_You_Like_UserId;
	
	@Column(columnDefinition = "VARCHAR(20) ")
	private String give_You_Like_userAccount;
	
	@Column(columnDefinition = "VARCHAR(1000)")
	@ColumnDefault(value="'https://myjavatest20231207.s3.ap-northeast-3.amazonaws.com/defaultuser.png'")
	private String give_You_Like_Usericon;
	
	private Integer postType;
	
	
	// 按讚時間
	@Column(columnDefinition = "DATETIME")
	private Date like_Time;
	
	// 發文時間
	@Column(columnDefinition = "DATETIME")
	private Date create_Time;

	public tellYouHaveLike() {
		super();
	}

	public tellYouHaveLike(Integer id, Integer post_userId, Integer give_You_Like_PostId, Integer give_You_Like_UserId,
			String give_You_Like_userAccount, String give_You_Like_Usericon, Integer postType, Date like_Time,
			Date create_Time) {
		super();
		this.id = id;
		this.post_userId = post_userId;
		this.give_You_Like_PostId = give_You_Like_PostId;
		this.give_You_Like_UserId = give_You_Like_UserId;
		this.give_You_Like_userAccount = give_You_Like_userAccount;
		this.give_You_Like_Usericon = give_You_Like_Usericon;
		this.postType = postType;
		this.like_Time = like_Time;
		this.create_Time = create_Time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPost_userId() {
		return post_userId;
	}

	public void setPost_userId(Integer post_userId) {
		this.post_userId = post_userId;
	}

	public Integer getGive_You_Like_PostId() {
		return give_You_Like_PostId;
	}

	public void setGive_You_Like_PostId(Integer give_You_Like_PostId) {
		this.give_You_Like_PostId = give_You_Like_PostId;
	}

	public Integer getGive_You_Like_UserId() {
		return give_You_Like_UserId;
	}

	public void setGive_You_Like_UserId(Integer give_You_Like_UserId) {
		this.give_You_Like_UserId = give_You_Like_UserId;
	}

	public String getGive_You_Like_userAccount() {
		return give_You_Like_userAccount;
	}

	public void setGive_You_Like_userAccount(String give_You_Like_userAccount) {
		this.give_You_Like_userAccount = give_You_Like_userAccount;
	}

	public String getGive_You_Like_Usericon() {
		return give_You_Like_Usericon;
	}

	public void setGive_You_Like_Usericon(String give_You_Like_Usericon) {
		this.give_You_Like_Usericon = give_You_Like_Usericon;
	}

	public Integer getPostType() {
		return postType;
	}

	public void setPostType(Integer postType) {
		this.postType = postType;
	}

	public Date getLike_Time() {
		return like_Time;
	}

	public void setLike_Time(Date like_Time) {
		this.like_Time = like_Time;
	}

	public Date getCreate_Time() {
		return create_Time;
	}

	public void setCreate_Time(Date create_Time) {
		this.create_Time = create_Time;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

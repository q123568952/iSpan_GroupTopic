package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SettingInform 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	private Integer userId;
	
	@Column(columnDefinition = "VARCHAR(20) NOT NULL unique")
	private String userAccount;
	
	@Column(columnDefinition = "tinyint unsigned default 1")
	private String postInformState;
	
	@Column(columnDefinition = "tinyint unsigned default 1")
	private String followInformState;
	
	@Column(columnDefinition = "tinyint unsigned default 1")
	private String likeInformState;
	
	@Column(columnDefinition = "tinyint unsigned default 1")
	private String openState;
	
	@Column(columnDefinition = "DATETIME")
	private Date   settingInformationTime;

	
	public SettingInform() {
		super();
	}



	public SettingInform(Integer id, Integer userId, String userAccount, String postInformState,
			String followInformState, String likeInformState, String openState, Date settingInformationTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.userAccount = userAccount;
		this.postInformState = postInformState;
		this.followInformState = followInformState;
		this.likeInformState = likeInformState;
		this.openState = openState;
		this.settingInformationTime = settingInformationTime;
	}






	

	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public Integer getUserId() {
		return userId;
	}






	public void setUserId(Integer userId) {
		this.userId = userId;
	}






	public String getUserAccount() {
		return userAccount;
	}






	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}


	public String getPostInformState() {
		return postInformState;
	}



	public void setPostInformState(String postInformState) {
		this.postInformState = postInformState;
	}






	public String getFollowInformState() {
		return followInformState;
	}


	public void setFollowInformState(String followInformState) {
		this.followInformState = followInformState;
	}


	public String getLikeInformState() {
		return likeInformState;
	}

	public void setLikeInformState(String likeInformState) {
		this.likeInformState = likeInformState;
	}


	public String getOpenState() {
		return openState;
	}


	public void setOpenState(String openState) {
		this.openState = openState;
	}



	public Date getSettingInformationTime() {
		return settingInformationTime;
	}






	public void setSettingInformationTime(Date settingInformationTime) {
		this.settingInformationTime = settingInformationTime;
	}

}

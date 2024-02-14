package com.sns.dropcat.model;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FollowInformation 
{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 用戶id
	private Integer followedUserId;
	
	private Integer fansId;
	
	@Column(columnDefinition = "VARCHAR(20) ")
	private String fansUserAccount;
	
	
	
	@Column(columnDefinition = "VARCHAR(1000)")
	@ColumnDefault(value="'https://myjavatest20231207.s3.ap-northeast-3.amazonaws.com/defaultuser.png'")
	private String fansIcon;
	

	@Column(columnDefinition = "DATETIME")
	private Date fansFollowTime;


	public FollowInformation() {
		super();
	}


	public FollowInformation(Integer id, Integer followedUserId, Integer fansId, String fansUserAccount,
			String fansIcon, Date fansFollowTime) {
		super();
		this.id = id;
		this.followedUserId = followedUserId;
		this.fansId = fansId;
		this.fansUserAccount = fansUserAccount;
		this.fansIcon = fansIcon;
		this.fansFollowTime = fansFollowTime;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getFollowedUserId() {
		return followedUserId;
	}


	public void setFollowedUserId(Integer followedUserId) {
		this.followedUserId = followedUserId;
	}


	public Integer getFansId() {
		return fansId;
	}


	public void setFansId(Integer fansId) {
		this.fansId = fansId;
	}


	public String getFansUserAccount() {
		return fansUserAccount;
	}


	public void setFansUserAccount(String fansUserAccount) {
		this.fansUserAccount = fansUserAccount;
	}


	public String getFansIcon() {
		return fansIcon;
	}


	public void setFansIcon(String fansIcon) {
		this.fansIcon = fansIcon;
	}


	public Date getFansFollowTime() {
		return fansFollowTime;
	}


	public void setFansFollowTime(Date fansFollowTime) {
		this.fansFollowTime = fansFollowTime;
	}


	
	
	
	
	
	
	
	
	

}

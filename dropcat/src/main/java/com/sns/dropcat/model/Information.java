package com.sns.dropcat.model;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


// @Data = @Getter + @Setter + @RequiredArgsConstructor + @EqualsAndHashCode

//@RequiredArgsConstructor  生成final屬性的構造函數，如果沒有final就是無參構造函數
//@EqualsAndHashCode     


//@Data
@Entity
public class Information 
{
	




	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer postId;
	
	private Integer userId;
	
	private Integer othersUserID;
	
	@Column(columnDefinition = "VARCHAR(20) NOT NULL ")
	private String userAccount;

//	@Column(columnDefinition = "VARCHAR(20) ")
//	private Integer postId;
	
	@Column(columnDefinition = "VARCHAR(20)")
	private String postType;
	
	@Column(columnDefinition = "DATETIME")
	private Date  postSettingTime;



	@Column(columnDefinition = "VARCHAR(1000)")
	@ColumnDefault(value="'https://myjavatest20231207.s3.ap-northeast-3.amazonaws.com/defaultuser.png'")
	private String usericon;



	public Information() {
		super();
	}



	public Information(Integer id, Integer postId, Integer userId, Integer othersUserID, String userAccount,
			String postType, Date postSettingTime, String usericon) {
		super();
		this.id = id;
		this.postId = postId;
		this.userId = userId;
		this.othersUserID = othersUserID;
		this.userAccount = userAccount;
		this.postType = postType;
		this.postSettingTime = postSettingTime;
		this.usericon = usericon;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public Integer getOthersUserID() {
		return othersUserID;
	}



	public void setOthersUserID(Integer othersUserID) {
		this.othersUserID = othersUserID;
	}



	public String getUserAccount() {
		return userAccount;
	}



	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}



	public String getPostType() {
		return postType;
	}



	public void setPostType(String postType) {
		this.postType = postType;
	}



	public Date getPostSettingTime() {
		return postSettingTime;
	}



	public void setPostSettingTime(Date postSettingTime) {
		this.postSettingTime = postSettingTime;
	}



	public String getUsericon() {
		return usericon;
	}



	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}




	
	
	
	
	
	
	
	

}

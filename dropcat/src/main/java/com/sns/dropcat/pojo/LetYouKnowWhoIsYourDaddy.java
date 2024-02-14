package com.sns.dropcat.pojo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LetYouKnowWhoIsYourDaddy 
{
	
	
	
	
	public LetYouKnowWhoIsYourDaddy() {
		super();
	}
	
	
	


	public LetYouKnowWhoIsYourDaddy(Integer id, String userAccount, String username, String phonenumber, String email,
			String password, Date createtime, Date edittime, String usericon, String introduction, Integer gender,
			String lineId, String lineProfile) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.username = username;
		this.phonenumber = phonenumber;
		this.email = email;
		this.password = password;
		this.createtime = createtime;
		this.edittime = edittime;
		this.usericon = usericon;
		this.introduction = introduction;
		this.gender = gender;
		LineId = lineId;
		LineProfile = lineProfile;
	}





	private Integer id;
	
	private String userAccount;

	private String username;
	
	private String phonenumber;
	
	private String email;

	
	private String password;

	
	private Date createtime;
	
	private Date edittime;


	private String usericon;

	
	private String introduction;

	private Integer gender;


	private String LineId;

	
	private String LineProfile;


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





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPhonenumber() {
		return phonenumber;
	}





	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
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





	public String getUsericon() {
		return usericon;
	}





	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}





	public String getIntroduction() {
		return introduction;
	}





	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}





	public Integer getGender() {
		return gender;
	}





	public void setGender(Integer gender) {
		this.gender = gender;
	}





	public String getLineId() {
		return LineId;
	}





	public void setLineId(String lineId) {
		LineId = lineId;
	}





	public String getLineProfile() {
		return LineProfile;
	}





	public void setLineProfile(String lineProfile) {
		LineProfile = lineProfile;
	}
	
	
	

}

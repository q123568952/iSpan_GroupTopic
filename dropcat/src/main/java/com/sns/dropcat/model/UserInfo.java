package com.sns.dropcat.model;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DynamicInsert
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "VARCHAR(20) NOT NULL unique")
	private String userAccount;
	@Column(columnDefinition = "VARCHAR(20)")
	private String username;
	@Column(columnDefinition = "VARCHAR(20) NOT NULL unique")
	private String phonenumber;
	@Column(columnDefinition = "VARCHAR(200) NOT NULL unique")
	private String email;

	@Column(columnDefinition = "VARCHAR(255)")
	private String password;

	@Column(columnDefinition = "DATETIME")
	private Date createtime;
	@Column(columnDefinition = "DATETIME")
	private Date edittime;

	@Column(columnDefinition = "VARCHAR(1000)")
	@ColumnDefault(value = "'https://myjavatest20231207.s3.ap-northeast-3.amazonaws.com/defaultuser.png'")
	private String usericon;

	@Column(columnDefinition = "VARCHAR(150)")
	private String introduction;

	private Integer gender;

	@Column(columnDefinition = "VARCHAR(255)")
	private String resetToken;

	@Column(columnDefinition = "DATETIME")
	private Date tokenTime;

	public UserInfo(Integer id, String userAccount, String username, String usericon) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.username = username;
		this.usericon = usericon;
	}

	public UserInfo(Integer id, String userAccount, String username, String phonenumber, String email, String password,
			Date createtime, Date edittime, String usericon, String introduction, Integer gender) {
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
	}

	public UserInfo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getuserAccount() {
		return userAccount;
	}

	public void setuserAccount(String userAccount) {
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

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Date getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(Date tokenTime) {
		this.tokenTime = tokenTime;
	}

	public void changePassword(String newPassword) {
		this.password = newPassword;
	}

	@Override
	public String toString() {

		return "User [id=" + id + ", userAccount=" + userAccount + ", username=" + username + ", phonenumber="
				+ phonenumber + ", email=" + email + ", password=" + password + ", createtime=" + createtime
				+ ", edittime=" + edittime + ", usericon=" + usericon + ", introduction=" + introduction + ", gender="
				+ gender + "]" + "\n";

	}

// ToString方便確認資料

}

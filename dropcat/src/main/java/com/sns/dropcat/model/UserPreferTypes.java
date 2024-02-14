package com.sns.dropcat.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@DynamicInsert
public class UserPreferTypes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userPreferTypesId;
	
	
	private Integer userId;
	
	@Column(columnDefinition="Integer")
	@ColumnDefault(value="0")
	private Integer typeId;

	private Integer score;

	public UserPreferTypes(Integer userPreferTypesId, Integer userId, Integer typeId, Integer score) {
		super();
		this.userPreferTypesId = userPreferTypesId;
		this.userId = userId;
		this.typeId = typeId;
		this.score = score;
	}

	public Integer getUserPreferTypesId() {
		return userPreferTypesId;
	}

	public void setUserPreferTypesId(Integer userPreferTypesId) {
		this.userPreferTypesId = userPreferTypesId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	

	

	
	
}

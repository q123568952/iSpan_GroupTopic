package com.sns.dropcat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class typeKeys {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer typeKeyId;
	
	@Column(columnDefinition = "VARCHAR(5)")
	private String typekey;
	
	private Integer typeId;

	public typeKeys() {
		super();
	}

	public typeKeys(Integer typeKeyId, String typekey, Integer typeId) {
		super();
		this.typeKeyId = typeKeyId;
		this.typekey = typekey;
		this.typeId = typeId;
	}

	public Integer getTypeKeyId() {
		return typeKeyId;
	}

	public void setTypeKeyId(Integer typeKeyId) {
		this.typeKeyId = typeKeyId;
	}

	public String getTypekey() {
		return typekey;
	}

	public void setTypekey(String typekey) {
		this.typekey = typekey;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	
	
}

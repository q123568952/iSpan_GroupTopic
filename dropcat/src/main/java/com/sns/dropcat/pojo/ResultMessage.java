package com.sns.dropcat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class ResultMessage 
{
	

	private boolean System;
	
	private String fromName;
	
	private Object message;

	
	
	
	public boolean isSystem() {
		return System;
	}




	public void setSystem(boolean system) {
		System = system;
	}




	public String getFromName() {
		return fromName;
	}




	public void setFromName(String fromName) {
		this.fromName = fromName;
	}




	public Object getMessage() {
		return message;
	}




	public void setMessage(Object message) {
		this.message = message;
	}




	public ResultMessage() {
		super();
	}




	public ResultMessage(boolean system, String fromName, Object message) {
		super();
		System = system;
		this.fromName = fromName;
		this.message = message;
	}
	
	
	
	
	
	
	
	

}

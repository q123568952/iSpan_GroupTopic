package com.sns.dropcat.pojo;

import lombok.Data;

@Data
public class MessageForWS 
{
	
	
	private String toName;
	
	private String message;

	

	public MessageForWS() {
		super();
	}

	public MessageForWS(String toName, String message) {
		super();
		this.toName = toName;
		this.message = message;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}

package com.sns.dropcat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class ResultForWS 
{
	
	
	private boolean flag;
	
	private String message;

	public ResultForWS() {
		super();
	}

	public ResultForWS(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}

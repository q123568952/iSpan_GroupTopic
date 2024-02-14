package com.sns.dropcat.queryreturncalsses;

import java.sql.Timestamp;
import java.util.Date;

public class ChatHistory {
	
	private Integer senderID;
	private Integer receiverId;
	private String messages;
	private Timestamp chattingTime;
	public ChatHistory(Integer senderID, Integer receiverId, String messages, Timestamp chattingTime) {
		super();
		this.senderID = senderID;
		this.receiverId = receiverId;
		this.messages = messages;
		this.chattingTime = chattingTime;
	}
	public Integer getSenderID() {
		return senderID;
	}
	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public Timestamp getChattingTime() {
		return chattingTime;
	}
	public void setChattingTime(Timestamp chattingTime) {
		this.chattingTime = chattingTime;
	}
	
	
}

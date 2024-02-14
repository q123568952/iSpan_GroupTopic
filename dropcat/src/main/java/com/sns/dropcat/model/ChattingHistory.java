package com.sns.dropcat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChattingHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ChattingHistoryID;
	
	@Column(columnDefinition = "INT")
	private Integer senderID;
	
	@Column(columnDefinition = "INT")
	private Integer receiverID;
	
	@Column(columnDefinition = "VARCHAR(250)")
	private String messages;
	
	@Column(columnDefinition = "DATETIME NOT NULL")
	private Date chattingTime;

	public ChattingHistory(Integer chattingHistoryID, Integer senderID, Integer receiverID, String messages,
			Date chattingTime) {
		super();
		ChattingHistoryID = chattingHistoryID;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.messages = messages;
		this.chattingTime = chattingTime;
	}

	public Integer getChattingHistoryID() {
		return ChattingHistoryID;
	}

	public void setChattingHistoryID(Integer chattingHistoryID) {
		ChattingHistoryID = chattingHistoryID;
	}

	public Integer getSenderID() {
		return senderID;
	}

	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}

	public Integer getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Integer receiverID) {
		this.receiverID = receiverID;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Date getChattingTime() {
		return chattingTime;
	}

	public void setChattingTime(Date chattingTime) {
		this.chattingTime = chattingTime;
	}
	
	
}

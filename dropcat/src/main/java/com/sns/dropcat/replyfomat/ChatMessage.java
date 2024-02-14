package com.sns.dropcat.replyfomat;

public class ChatMessage {
    private Integer senderID;
    private String username;
    private Integer receiverID;
    private String receiverName;
    private String content;
//    private String token;
	public ChatMessage(Integer senderID, String username, Integer receiverID, String receiverName, String content,
			String token) {
		super();
		this.senderID = senderID;
		this.username = username;
		this.receiverID = receiverID;
		this.receiverName = receiverName;
		this.content = content;
//		this.token = token;
	}
	public Integer getSenderID() {
		return senderID;
	}
	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(Integer receiverID) {
		this.receiverID = receiverID;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
}


package com.sns.dropcat.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.dropcat.model.ChattingHistory;
import com.sns.dropcat.model.ChattingHistoryRepository;
import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;
import com.sns.dropcat.replyfomat.ChatMessage;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.ChattingRoomService;
import com.utils.JwtParseAllClaim;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ChattingRoomController {

	@Autowired
	private ChattingHistoryRepository chattingHistoryRepository;
	
	@Autowired
	private ChattingRoomService chattingRoomService;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@GetMapping("/chattingRoom")
	public String getPersonalPage(Model model, HttpServletRequest request) {
		LetYouKnowWhoIsYourDaddy userInfo = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(request);
		Integer userID = userInfo.getId();
		String userAccount = userInfo.getUserAccount();
		String username = userInfo.getUsername();
		model.addAttribute("userID", userID);
		model.addAttribute("userAccount", userAccount);
		model.addAttribute("username", username);
		return "ChattingRoom";
	}
	
	@GetMapping("/chattingRoom-{followingUserID}")
	public String toChosenChattingRoom(Model model, @PathVariable Integer followingUserID, HttpServletRequest request) {
		LetYouKnowWhoIsYourDaddy userInfo = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(request);
		Integer userID = userInfo.getId();
		String userAccount = userInfo.getUserAccount();
		String username = userInfo.getUsername();
		model.addAttribute("userID", userID);
		model.addAttribute("userAccount", userAccount);
		model.addAttribute("username", username);
		model.addAttribute("followingUserID", followingUserID);
		model.addAttribute("isDirected", true);
		return "ChattingRoom";
	}
	
	@GetMapping("/chatHistory/{followingUserID}")
    @ResponseBody
    public Result getChatHistory(HttpServletRequest request, @PathVariable Integer followingUserID) {
		LetYouKnowWhoIsYourDaddy userInfo = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(request);
		Integer userID = userInfo.getId();
        Result CHResult = chattingRoomService.getChatHistory(userID, followingUserID);
        return CHResult;
    }
	
	@GetMapping("/chatFollowers")
    @ResponseBody
    public Result getChatFollowers(HttpServletRequest request) {
		LetYouKnowWhoIsYourDaddy userInfo = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(request);
		Integer userID = userInfo.getId();
        Result CFResult = chattingRoomService.getChatFollowers(userID);
        return CFResult;
    }
	
	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		
		messagingTemplate.convertAndSend("/public/chat", chatMessage);
//		System.out.println(chatMessage.getToken());
//		Integer userId=JwtUtils.parseJWT(chatMessage.getToken()).get("id",Integer.class);
		ChattingHistory history = new ChattingHistory(null, null, null, null, null);
		history.setSenderID(chatMessage.getSenderID());
		history.setReceiverID(chatMessage.getReceiverID());
		history.setMessages(chatMessage.getContent());
		history.setChattingTime(new Date());
		chattingHistoryRepository.save(history);
		System.out.println("聊天紀錄存取完畢");
	}
	
	@MessageMapping("/chat.privateMessage")
    public void sendPrivateMessage(ChatMessage chatMessage) {
        // 處理發送私人消息
		messagingTemplate.convertAndSendToUser(chatMessage.getReceiverID().toString(),"/queue/reply",chatMessage);
		ChattingHistory history = new ChattingHistory(null, null, null, null, null);
		history.setSenderID(chatMessage.getSenderID());
		history.setReceiverID(chatMessage.getReceiverID());
		history.setMessages(chatMessage.getContent());
		history.setChattingTime(new Date());
		chattingHistoryRepository.save(history);
		System.out.println("聊天紀錄存取完畢");
    }
}

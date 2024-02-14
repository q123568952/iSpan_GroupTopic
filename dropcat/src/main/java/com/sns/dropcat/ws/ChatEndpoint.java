package com.sns.dropcat.ws;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.model.tellYouHaveLike;
import com.sns.dropcat.pojo.MessageForWS;
import com.sns.dropcat.service.InformationService;
import com.sns.dropcat.service.impl.InformationServiceImpl;
import com.utils.ApplicationContextRegister;
import com.utils.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;



//@ServerEndpoint(value="/chat111111", configurator = GetHttpSessionConfigurator.class)
//@Component
public class ChatEndpoint 
{
	
	
	//用來儲存每一個客户端對象對應的ChatEndpoint對象
//	 public static final Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();
	
	 public static  Map<Integer, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();
		
		
	
	// 聲明Session對象，透過該對象可以發送消息給指定的用戶
	 private Session session;
	 
	 // 聲明HttpSession對象，之前在HttpSession對象中儲存了用戶名
	 private HttpSession httpSession;
	
	 
	 

	 
	 public static InformationService informationService;
	
	 @Autowired
	 public void seter(InformationService informationService)
	 {
		 	System.out.println("這裡是WS----------执行seter方法");
	        this.informationService = informationService;
	        System.out.println(this.informationService);

	 }

	 
	 
	
	 /**
	  * 建立webSocket 連接後，被調用
	  * @param session
	  */
	  	@OnOpen
	    public void onOpen(Session session, EndpointConfig config) {
		  
		  // 1. 將局部的session物件賦值給成員 Session 保存
		  this.session=session;
		  
		  
		  // 獲取httpSession物件
		  HttpSession httpSession=(HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		  
		  this.httpSession=httpSession;
		  
		  // 從httpSession對象中獲取用戶名
//		  String username=(String) httpSession.getAttribute("user");
		  
		  
		  
		  
		  Integer username=(Integer) httpSession.getAttribute("id");
		  

		  List<tellYouHaveLike> xyz =ChatEndpoint.informationService.find_WhoLikeYourPost(username);
		  

		
		  // 將當前物件儲存到容器中
		  onlineUsers.put(username, this);
		  

		  String message2 = MessageUtils.getMessage(true, null, xyz);
		  broadcastAllUsers(message2);
		  
		  
		  //将当前在线用户的用户名推送给所有的客户端
	      //1 获取消息
//		  String message = MessageUtils.getMessage(true, null, getNames());
		 //2 调用方法进行系统消息的推送
//	     broadcastAllUsers(message);
		  
		  
	        System.out.println("WebSocket 連接已经建立。");
	    }
	  	
	  	
	  	///////////////////////////////////////////////////////////////////////////
	  	
	  	private void broadcastAllUsers(String xxxxxxxxxxx)
	  	{
	  		
	  		// 要將該消息發給所有用戶
//	  		Set<String> names=onlineUsers.keySet();
	  		
	  		Set<Integer> names=onlineUsers.keySet();
	  		
	  		for(Integer zzz:names)
	  		{
	  			// 將 ChatEndpoint 物件1個1個拿出來
	  			ChatEndpoint chatEndpoint=onlineUsers.get(zzz);
	  			
	  			try 
	  			{
					chatEndpoint.session.getBasicRemote().sendText(xxxxxxxxxxx);
				}
	  			catch (Exception e) 
	  			{
					e.printStackTrace();
					
					
				}
	  			
	  		}
	  		
	  		
	  		
	  		
	  		
	  	}
	  	
	  	//返回所有在线用户名
//	  	private Set<String> getNames()
//	  	{
//	  		return onlineUsers.keySet();
//	  	}
	  	
	  	
	  	private Set<Integer> getNames()
	  	{
	  		return onlineUsers.keySet();
	  	}
	  	
	  	
	  	
	  	
	  	
	  	/**
	     * 收到客户端消息后调用的方法
	     * @param message 客户端发送过来的消息
	     */
	    @OnMessage
	    public void onMessage(String message, Session session) throws Exception {
	        System.out.println("收到客户端消息：" + message);
	        session.getBasicRemote().sendText("服务器收到消息：" + message);
	        
	        
	        
	        
	        
	        
	        
	      //将数据转换成对象
	        try {
	            ObjectMapper mapper =new ObjectMapper();
	            MessageForWS mess = mapper.readValue(message, MessageForWS.class);
	            String toName = mess.getToName();
	            String data = mess.getMessage();
	            String username = (String) httpSession.getAttribute("user");
	            String resultMessage = MessageUtils.getMessage(false, username, data);
	            //发送数据
	            onlineUsers.get(toName).session.getBasicRemote().sendText(resultMessage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }


	        
	        
	        
	    }
	    /**
	     * 连接关闭调用的方法
	     */
	    @OnClose
	    public void onClose() {
	        System.out.println("WebSocket 连接已经关闭。");
	    }
	    
	    @OnError
	    public void onError(Throwable t) {
	        System.out.println("WebSocket 连接出现错误：" + t.getMessage());
	    }

	
	
	
	

}

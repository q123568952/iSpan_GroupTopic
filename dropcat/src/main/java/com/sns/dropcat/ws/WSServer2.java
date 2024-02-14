package com.sns.dropcat.ws;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;






@Component
@ServerEndpoint(value="/wss/{sid}",  configurator = GetHttpSessionConfigurator.class)
public class WSServer2 
{
	

	
		// 存放Session 物件
//		private static Map<String, Session> sessionMap=new HashMap(); 
		
		private static Map<Integer, Session> sessionMap=new HashMap(); 
		
		
		
		// 聲明Session對象，透過該對象可以發送消息給指定的用戶
		private Session session;
		
		// 聲明HttpSession對象，之前在HttpSession對象中儲存了用戶名
		private HttpSession httpSession;
		
		
		
		

		 /**
		  * 建立webSocket 連接後，被調用
		  * @param session
		  */
		@OnOpen
		public void onOpen(Session session , @PathParam("sid") String sid, EndpointConfig config)
		{
			
			System.out.println("後端印的訊息:這是在伺服器顯示的@OnOpen客戶端" + sid + "建立連接");

	        System.out.println("後端印的訊息@OnOpen客戶端:有新的連接總數為===" + sessionMap.size());
			
			
			 // 1. 將局部的session物件賦值給成員 Session 保存
			  this.session=session;
			  
	        
	        // 獲取httpSession物件
			  HttpSession httpSession=(HttpSession) config.getUserProperties().get(HttpSession.class.getName());
			  
			  this.httpSession=httpSession;
			  // value
//			  String userAccount=(String) httpSession.getAttribute("userAccount");
//			  System.out.println("後端印的訊息:這是在@OnOpen顯示的使用者userAccount為:" + userAccount);
//	        
			  
			  Integer userId=(Integer) httpSession.getAttribute("userId");
			  System.out.println("後端印的訊息:這是在@OnOpen顯示的使用者userId為:" + userId);
			  
			  
			  
			  
			  
			  
			  // 設置Key and Value
			  sessionMap.put(userId, session);
			  
			  System.out.println("後端印的訊息@OnOpen客戶端:有新的連接總數為===" + sessionMap.size());
			  System.out.println("後端印的訊息:這是在@OnOpen顯示的使用者session為:" + sessionMap.get(userId));
			
		}
		
		
		
		
		
		
		
		
		
		/**
	     * 收到前端客户端消息後調用的方法
	     * @param message 客户端發送過来的消息
	     */
	    @OnMessage
	    public void onMessage(String message, @PathParam("sid") String sid, Session session
	    		) throws Exception 
	    {
	    	
	    	// 收到客户端消息後往前傳到前端
//	    	session.getBasicRemote().sendText(message);
	    	
	    	
	    	
	        System.out.println("後端印的訊息:後端伺服器@OnMessage收到客户端"  + sid + "的消息："+ message);
	        
	        
//	        String userAccount=(String) httpSession.getAttribute("userAccount");
	        

	        
	    }
		
		
		
		
		
		
		
		
	    /**
	     * 連接關閉調用的方法
	     */
	    @OnClose
	    public void onClose(@PathParam("sid") String sid) {
	        System.out.println("後端印的訊息@OnClose客戶端:WebSocket 連接已經關閉。");
	        sessionMap.remove(sid);
	        
	        System.out.println("後端印的訊息@OnClose客戶端:目前sessionMap的元素總數為===" + sessionMap.size());
	        
	        
	    }
	    
	    @OnError
	    public void onError(Throwable t) {
	        System.out.println("後端印的訊息@OnError客戶端:WebSocket 連接出現錯誤：" + t.getMessage());
	    }
		
	    
	    
	    
	    
	    /**
	     * 群發
	     */
	    public void sendToAllClient(String message)
	    {
	    	
	    	Collection<Session> sessions=sessionMap.values();
	    	
	    	for(Session session:sessions)
	    	{
	    		
	    		try 
	    		{
					session.getBasicRemote().sendText(message);
				} 
	    		catch (Exception e) 
	    		{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		
	    		
	    	}
	    	
	    	
	    	
	    }
	    
	    
	    
	    /**
	     * 單發
	     * 要先讓 @OnOpen 執行
	     */
	    
	    public  void sendToOne(String xxxxxxxxxxx, Integer userId)
	    {
	    	
	    	Session session	=sessionMap.get(userId);
	    	
	    	System.out.println("這是在sendToOne方法中的userId:" + userId);
	    	System.out.println("這是在sendToOne方法中的Session:" + session);
	    	
	    	
	    	System.out.println("這裡是sendToOne----傳進來的資料-------" + xxxxxxxxxxx);
	    	
	    	
	    	try 
	    	{
	    		session.getBasicRemote().sendText(xxxxxxxxxxx);
			} 
	    	catch (IOException e)
	    	{
				
				e.printStackTrace();
				
			}
	    	
	    	
	    	
	    }
	    
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

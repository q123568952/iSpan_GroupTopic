package com.sns.dropcat.ws;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.query.Param;
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



//@Component
//@ServerEndpoint("/ws/{sid}")
public class WSServer 
{
	// 存放Session 物件
	private static Map<String, Session> sessionMap=new HashMap(); 
	
	
	
	private Session thisPersonSession;
	
	
	
	
	
	// 聲明HttpSession對象，之前在HttpSession對象中儲存了用戶名
	private HttpSession httpSession;
	
	
	
	

	 /**
	  * 建立webSocket 連接後，被調用
	  * @param session
	  */
	@OnOpen
	public void onOpen(Session session , @PathParam("id") String sid, EndpointConfig config)
	{
		
		System.out.println("@OnOpen客戶端" + sid + "建立連接");
		sessionMap.put(sid, session);
		
		
		 // 儲存現在這個單1個人的Session
        this.thisPersonSession=session;
		
        
        System.out.println("thisPersonSession=======@OnOpen====" + thisPersonSession);
        
		
        
        
        
        // 獲取httpSession物件
		  HttpSession httpSession=(HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		  
		  this.httpSession=httpSession;
        
        
        
        
		
	}
	
	
	
	
	
	
	
	
	
	/**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid, Session session) throws Exception 
    {
    	
    	// 收到客户端消息後往前傳到前端
    	session.getBasicRemote().sendText(message);
    	
    	
    	
        System.out.println("後端伺服器@OnMessage收到客户端"  + sid + "的消息："+ message);
        
        
        
         Session itIsYourSession=sessionMap.get(sid);
        
        
        
        
       
        
        System.out.println("thisPersonSession=======OnMessage============" + thisPersonSession);
        
       
//        WSServer.sendToOne(itIsYourSession, sid);
        
        
    
        
        
    }
	
	
	
	
	
	
	
	
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("WebSocket 连接已经关闭。");
        sessionMap.remove(sid);
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("WebSocket 连接出现错误：" + t.getMessage());
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
    
    public  void sendToOne(String xxxxxxxxxxx)
    {
    	
    	Session iiiiiiii =thisPersonSession;
    	
    	
//    	sessionMap.get(adasdsa);
    	
    	
    	
    	
    	
    	System.out.println("這裡是sendToOne-----------");
    	System.out.println(xxxxxxxxxxx);
    	
    	try 
    	{
    		iiiiiiii.getBasicRemote().sendText(xxxxxxxxxxx);
		} 
    	catch (IOException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	

}

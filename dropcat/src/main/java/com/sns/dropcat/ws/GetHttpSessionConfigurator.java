package com.sns.dropcat.ws;

import org.apache.tomcat.websocket.server.DefaultServerEndpointConfigurator;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;



// 專門用來取的 HttpServeletRequest
public class GetHttpSessionConfigurator extends  DefaultServerEndpointConfigurator
{

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {


		HttpSession httpSession= (HttpSession)request.getHttpSession();
		
		// 將httpSession物件儲存到配置對象中
		sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
		
		
		
		
		
		
	}

	
	
	
}

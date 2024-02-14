package com.sns.dropcat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Websocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.sns.dropcat.service.InformationService;
import com.sns.dropcat.ws.ChatEndpoint;

import jakarta.websocket.server.ServerEndpoint;


@Configuration
public class soecketForTest2024 
{
	
	// 將方法的return 值交給Spring 管理，
	// 注入ServerEndpointExporter bean對象，自動註冊使用了@ServerEndpoint 註解的bean
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
	
//	@Autowired
//	public void informationService(InformationService informationService)
//	{
//		ChatEndpoint.informationService=informationService;
//	}
	

}

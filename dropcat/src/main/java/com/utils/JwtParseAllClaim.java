package com.utils;

import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class JwtParseAllClaim 
{
	
	
	
	public static LetYouKnowWhoIsYourDaddy letYouKnowWhoIsYourDaddy(HttpServletRequest req)
	{
		
		String jwt=null;
		 
		Cookie[] mapForCookie= req.getCookies();
		
		for(int i=0; i< mapForCookie.length; i++)
		{
			
			
			if(mapForCookie[i].getName().equals("tokenFromJava"))
			{
				// 從Cookie中獲取已生成的jwt
				// 把 存在Cookie中的token賦值給jwt
				jwt=mapForCookie[i].getValue();
//				System.out.println("---------------這是在JwtParseAllClaim Class 中-----------------------");
//				System.out.println("mapForCookie[i].getValue():=====" + mapForCookie[i].getValue());
//				
//				
//				
//				System.out.println("這是我們自己設定的JWT====" + jwt);
				
			}
			
		}
		
		
		
		JwtUtils.parseJWT(jwt);
        
//        System.out.println("解析後的token-------------============================---------");
//        System.out.println(JwtUtils.parseJWT(jwt));
        
        
        // 也可以單獨獲取某個claim
        Integer userId = JwtUtils.parseJWT(jwt).get("id", Integer.class);
        String userAccount = JwtUtils.parseJWT(jwt).get("userAccount", String.class);
        String username = JwtUtils.parseJWT(jwt).get("username", String.class);
        String phonenumber = JwtUtils.parseJWT(jwt).get("phonenumber", String.class);
        String email = JwtUtils.parseJWT(jwt).get("email", String.class);
        String password = JwtUtils.parseJWT(jwt).get("password", String.class);
        String usericon = JwtUtils.parseJWT(jwt).get("usericon", String.class);
        String LineId = JwtUtils.parseJWT(jwt).get("LineId", String.class);
        String LineProfile = JwtUtils.parseJWT(jwt).get("LineProfile", String.class);
        
        
        LetYouKnowWhoIsYourDaddy thisCanLetYouKnowUserInformation=new LetYouKnowWhoIsYourDaddy();
        
        thisCanLetYouKnowUserInformation.setId(userId);
        thisCanLetYouKnowUserInformation.setUserAccount(userAccount);
        thisCanLetYouKnowUserInformation.setUsername(username);
        thisCanLetYouKnowUserInformation.setPhonenumber(phonenumber);
        thisCanLetYouKnowUserInformation.setPassword(password);
        thisCanLetYouKnowUserInformation.setUsericon(usericon);
        thisCanLetYouKnowUserInformation.setLineId(LineId);
        thisCanLetYouKnowUserInformation.setLineProfile(LineProfile);
		
		return thisCanLetYouKnowUserInformation;
		
	}
	
	
	        
	    
	
	

}

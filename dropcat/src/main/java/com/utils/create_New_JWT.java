package com.utils;

import java.util.HashMap;
import java.util.Map;

import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;

import jakarta.servlet.http.HttpServletRequest;

public class create_New_JWT 
{
	
	
	public static String giveYouNew_JWT(HttpServletRequest req)
	{
		
		
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		
		
		// 取出前1個 解析後JWT 中的資訊
		Integer userId=userInformation.getId();
		String userAccount = userInformation.getUserAccount();
        String username = userInformation.getUsername();
        String phonenumber =userInformation.getPhonenumber();
        String email = userInformation.getEmail();
        String password = userInformation.getPassword();
		
		
		
		
		//自定義的訊息
        Map<String , Object> claims = new HashMap<>();
        claims.put("id",userId);
        claims.put("userAccount",userAccount);
        claims.put("username",username);
        claims.put("phonenumber",phonenumber);
        claims.put("email",email);
        claims.put("password",password);
        

        //使用JWT，生成令牌
        // generateJwt() 參數要放入自訂義的訊息
        String jwt = JwtUtils.generateJwt(claims);
        System.out.println("--------------這是在create_New_JWT Class中----------------");
        
        System.out.println("====== 成功為1個新用戶生成1個JWT========"+ jwt);
		
        // 回傳1個新的jwt
		return jwt;
		
		
	}
	
	
	

}

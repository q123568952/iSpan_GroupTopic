package com.sns.dropcat.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.replyfomat.Result;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LogoutController 
{
	
	
	
	// 實現登出用
	//  用來刪除我們自己設定的JWT 所屬的Cookie，來實現登出的作用
	@RequestMapping("/logout")
	@ResponseBody
    public Result logout(HttpServletRequest req, HttpServletResponse resp){


		String jwt=null;
		Cookie settingCookie=null;
		 
		Cookie[] mapForCookie= req.getCookies();
		
		for(int i=0; i< mapForCookie.length; i++)
		{
			System.out.println(mapForCookie[i].getName());
			System.out.println(mapForCookie[i].getValue());
			
			if(mapForCookie[i].getName().equals("tokenFromJava"))
			{
				// 從Cookie中獲取已生成的jwt
				// 把 存在Cookie中的token賦值給jwt
				jwt=mapForCookie[i].getValue();
				
				System.out.println("mapForCookie[i].getValue()=======");
				System.out.println(mapForCookie[i].getValue());
				
				// 獲取自己設定的JWT 所在的Cookie
				settingCookie=mapForCookie[i];
				
				System.out.println("LogoutController-------這是我們自己設定的JWT所屬的Cookie====" + settingCookie);
				
				

				
			}
			
			
			
		}
		
		
		 System.out.println("印出放在Cookie中的jwt============" + jwt);
	
		 // 刪除Cookie
		settingCookie.setMaxAge(0);
	
		settingCookie.setPath("/");//该路径为创建Cookie时设定的访问路径
		resp.addCookie(settingCookie);
		System.out.println("刪除過後的Cookie====" + settingCookie);
		
		
		
		
		
		Result responseResult = Result.success("成功刪除我們自己設定的JWT所屬的Cookie，登出成功！");
        //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
        String json = JSONObject.toJSONString(responseResult);
        resp.setContentType("application/json;charset=utf-8");
        //響應
        try 
        {
//			resp.getWriter().write(json);
        	System.out.println("成功====================================");
		} 
        catch (Exception e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return Result.success("成功刪除我們自己設定的JWT所屬的Cookie，登出成功！");
 
    }
	
	
	

}

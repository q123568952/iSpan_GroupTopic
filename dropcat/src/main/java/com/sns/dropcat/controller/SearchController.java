package com.sns.dropcat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.dropcat.aop.Give_You_New_Jwt;
import com.sns.dropcat.aop.MyLog;
import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.SearchService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@RestController
public class SearchController 
{

	@Autowired
	SearchService searchService;
	
	
	// 搜尋會跳出使用者帳號
	@Give_You_New_Jwt
	@RequestMapping("/findUserInSearchController")
	@ResponseBody
	public Result findUser(@RequestBody UserInfo userInfo
			, HttpServletRequest req
			, HttpServletResponse resp)
	{
		
		
		List<UserInfo> findUserInSearchController= searchService.findUser(userInfo);
		
		System.out.println("這裡是findUserInSearchController-------------------");
		
		System.out.println("userInfo" + userInfo);
		
		
		
		return new Result().success(findUserInSearchController);
		
	}
	
	

}

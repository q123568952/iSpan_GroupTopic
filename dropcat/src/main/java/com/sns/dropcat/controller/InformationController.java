package com.sns.dropcat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.aop.Give_You_New_Jwt;
import com.sns.dropcat.model.FollowInformation;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.model.tellYouHaveLike;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.InformationService;
import com.sns.dropcat.ws.WSServer;
import com.sns.dropcat.ws.WSServer2;
import com.utils.JwtParseAllClaim;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class InformationController 
{
	
	@Autowired
	private WSServer2 wsSoCool;
	
	
	
	
	@Autowired
	InformationService informationService;
	
	// 偶像發文時，會給粉絲通知
//	@Give_You_New_Jwt
	@RequestMapping("/updateInformationData")
	@ResponseBody
	public Result updateInformation(InformationController information, HttpServletRequest req)
	{
		
		List<Information>  getInformation=informationService.findAll();
		System.out.println("這裡是InformationController=======================");
		System.out.println("SELECT 回來的所有通知Table 的資料-----------");
		System.out.println(getInformation.toString());
		
		
		
		
		// 現在的使用者的Id
		Integer userId=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req).getId();
		System.out.println("現在的使用者 id==========" + userId);
		
		
		
		List<Information> x= informationService.findUserInformationByJPA(userId);
		
		System.out.println("用現在的使用者的Id當作粉絲的id去 SELECT 回來的所有我追蹤對象的發文通知Table 的資料-----------");
		System.out.println(x.toString());
		System.out.println(x);

		
		
		// 這招超神
//		String json = JSONObject.toJSONString(x);
//		System.out.println("JSON用現在的使用者的Id當作粉絲的id去 SELECT 回來的所有我追蹤對象的發文通知Table 的資料-----------");
//		System.out.println(json);
//		
//		
//		wsSoCool.sendToOne(json);
		
		
		return new Result().success(x);
	}
	
	
	
	
	
	

	
	
	
	// 找誰按我的貼文讚
	@Give_You_New_Jwt
	@RequestMapping("/findWhoGiveMyPostLike")
	@ResponseBody
	public Result findWhoGiveMyPostLike(tellYouHaveLike tellyouhavelike, HttpServletRequest req)
	{
		// 現在的使用者的Id
		Integer userId=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req).getId();
		System.out.println("現在的使用者 id==========" + userId);
		

		List<tellYouHaveLike> x=informationService.find_WhoLikeYourPost(userId);
		
		
		
		// 這是單1貼文的資料
		String json = JSONObject.toJSONString(x.get((x.size()-1)));
		System.out.println("JSON用現在的使用者的Id當作粉絲的id去 SELECT 回來的所有按讚我的發文的人通知Table 的資料-----------");
		System.out.println(json);
		
		
//		Integer userId=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req).getId();
		System.out.println("在findWhoGiveMyPostLike的Controller印出userId" + userId);
		
//		wsSoCool.sendToOne(json, userId);
		
		
		
		return new Result().success(x);
	}
	
	
	
	
	
	
	
	// 找誰追蹤我
//	@Give_You_New_Jwt
	@RequestMapping("/followInformation")
	@ResponseBody
	public Result findWhofollowMe(FollowInformation tellyouhavelike, HttpServletRequest req)
	{
		// 現在的使用者的Id
		Integer userId=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req).getId();
		System.out.println("現在的使用者 id==========" + userId);
		

		List<FollowInformation> x=informationService.find_WhoFollowYouByJPA(userId);
		
		
		return new Result().success(x);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

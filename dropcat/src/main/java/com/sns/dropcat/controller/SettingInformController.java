package com.sns.dropcat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.aop.Give_You_New_Jwt;
import com.sns.dropcat.aop.MyLog;
import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.SettingInformService;
import com.utils.JwtParseAllClaim;
import com.utils.JwtUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class SettingInformController 
{
	@Autowired
	SettingInformService settingInformService;
	
	
//	抓取所有的 設定中的通知列資料 呈現在html
//	@GetMapping("/newestInform")
//	@ResponseBody
	public String getAllSettingInformData(Model model)
	{
//		List<SettingInform> settingInformlist=settingInformService.findAll();
//		
//		System.out.println(settingInformlist.toString());
//		model.addAttribute("", settingInformlist);
		
		
		return "newestInform";
		
	}
	


	
	
	
	
	
	
	// 使用@annotation 切入點表達式，需要在 連接點 上增加 自己設定的註解
	// 連接點 為由AOP控制的方法

	// 當使用者變更通知設定的時候要傳資料到料庫
	@RequestMapping("/addSettingInformData")
	@ResponseBody
	public Result addSettingInformData(@RequestBody SettingInform settingInform
										, HttpServletRequest req
										, HttpServletResponse resp)
	{
		
		
		
		// 取得JWT 是為了要知道目前是哪一位用戶在使用
		// 1.確認目前的請求url
		String url=req.getRequestURL().toString();
		System.out.println("這是在SettingInformController中------------------請求的url:" + url);
		

		
		
		// 2.獲取請求中的令牌
//		String jwt=req.getHeader("token");
		
		Cookie settingCookie=null;
		String jwt=null;
		 
		Cookie[] mapForCookie= req.getCookies();
		
		for(int i=0; i< mapForCookie.length; i++)
		{
			
//			System.out.println("mapForCookie[i].getName():=====" + mapForCookie[i].getName());
//			System.out.println("mapForCookie[i].getValue():=====" + mapForCookie[i].getValue());
			
			
			
			if(mapForCookie[i].getName().equals("tokenFromJava"))
			{
				// 從Cookie中獲取已生成的jwt
				// 把 存在Cookie中的token賦值給jwt
				jwt=mapForCookie[i].getValue();
				System.out.println("mapForCookie[i].getValue():=====" + mapForCookie[i].getValue());
				
				
				// 獲取自己設定的JWT 所在的Cookie
				settingCookie=mapForCookie[i];
				
				System.out.println("這是我們自己設定的JWT所屬的Cookie====" + settingCookie);
				
			}
			
			
			
		}
		
		
		 System.out.println("在SettingInformController印出放在Cookie中的jwt============" + jwt);
	
		
		
		 
		 
		 
		 
		 //3.判斷令牌是否存在，如果不存在，返回錯誤结果（未登入）
	        if(!StringUtils.hasLength(jwt))
	        {
//	            log.info("Token不存在");

	            Result responseResult = Result.error("NOT_LOGIN，JWT令牌不存在");
	            //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
	            String json = JSONObject.toJSONString(responseResult);
	            resp.setContentType("application/json;charset=utf-8");
	            //響應
//	            resp.getWriter().write(json);
//	            resp.getWriter().write("這裡是SettingInform裡面，還沒有拿到JWT---------------------");
	            System.out.println("這裡是SettingInform裡面，令牌不存在!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

	         
	            return Result.error("SettingInform裡面，沒有JWT");
	            
	        }
		 
		 
		 
		 

		 
		 //4.解析token
         //如果解析失敗，返回錯誤结果（未登入）
//	        try 
//	        {
	            JwtUtils.parseJWT(jwt);
	            
	            System.out.println("SettingInformController解析後的token-------------============================---------");
	            System.out.println(JwtUtils.parseJWT(jwt));
	            
	            
	            // 也可以单独获取某个claim
	            Integer userId = JwtUtils.parseJWT(jwt).get("id", Integer.class);
	            String userAccount = JwtUtils.parseJWT(jwt).get("userAccount", String.class);
	            String username = JwtUtils.parseJWT(jwt).get("username", String.class);
	            String phonenumber = JwtUtils.parseJWT(jwt).get("phonenumber", String.class);
	            String email = JwtUtils.parseJWT(jwt).get("email", String.class);
	            String password = JwtUtils.parseJWT(jwt).get("password", String.class);
	            String usericon = JwtUtils.parseJWT(jwt).get("usericon", String.class);
	            String LineId = JwtUtils.parseJWT(jwt).get("LineId", String.class);
	            String LineProfile = JwtUtils.parseJWT(jwt).get("LineProfile", String.class);
	            
	            
	            
	            System.out.println("===從JWT解析出來得=========id===============: " + userId);
//	            System.out.println("----------" + settingInformService.findOne(userId));
	            
	            
	            
	            // 把資料放進 settingInform
	            settingInform.setUserId(userId);
	            settingInform.setUserAccount(userAccount);
	         
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            // 用JWT 中保存的使用者id 去資料庫找是否有這個使用者的通知設定的資料
	            if(settingInformService.findOne(userId) != null )
	            {
	            	// 如果用戶的設定資料已經存在資料庫裡面了，就去更新通知設定


	            	System.out.println("這裡是settingInformController 成功查詢到用戶資料會是List<SettingInform>型態========"
	        				+ settingInformService.findOne(userId));
	        		System.out.println("這裡是settingInformController 成功查詢到用戶資料會是SettingInform型態========"
	        				+ settingInformService.findOne(userId).get(0));
	        		
	        		
	        		// 執行更新
	        		settingInformService.update(settingInform , userId);
	        		
	        		
	        		System.out.println("這裡是settingInformController 成功查詢到_____已經更新通知設定_____的用戶資料會是SettingInform型態========"
	        				+ settingInformService.findOne(userId).get(0));
	        		
	        		
	        		
	        		return Result.success(settingInformService.findOne(userId).get(0));
	            	
	            	
	            	
	            	
	            	
	            	
	            }
	            else
	            {
	            	
	            	//如果用戶的設定資料尚未存在資料庫裡面，就馬上Insert into 進去
	            	
	            	Result res = settingInformService.addOne(settingInform, userId);
	 	    		
	            	// 回傳Insert into成功的訊息回來
	            	System.out.println("---------------------------註冊成功-----------------------------------");
	            	return res;
	            	
	            	
	            }
	            

	            
	            
//	        }
//	        catch (Exception e)
//	        {
////	            log.info("令牌解析失敗!");
//
//	            Result responseResult = Result.error("NOT_LOGIN，解析令牌錯誤");
//	            
//	            //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
//	            String json = JSONObject.toJSONString(responseResult);
//	            resp.setContentType("application/json;charset=utf-8");
//	            //響應
////	            resp.getWriter().write(json);
//	            
//	            System.out.println("這裡是SettingInformController裡面，令牌解析失敗-------------------------------");
//	            
//	            return responseResult;
//	        }
		 
		 
		 

		
		
	}
	
	
	
	
	
	
	
	
	
	//	設定的功能開啟與關閉
	@GetMapping("/setting")
	@ResponseBody
	public Result getSettingAndBlock(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		
		Integer id = userInformation.getId();
		Result res = settingInformService.getBlockController(id);
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 2024/1/21 功能已經完成
	// 使用@annotation 切入點表達式，需要在 連接點 上增加 自己設定的註解
	// 連接點 為由AOP控制的方法

	// 當使用者變更通知設定的時候要傳資料到料庫
	@RequestMapping("/addSettingInformData1")
	@ResponseBody
	public Result addSettingInformData111forTest(@RequestBody SettingInform settingInform
										, HttpServletRequest req
										, HttpServletResponse resp)
	{
		
		
		
		// 取得JWT 是為了要知道目前是哪一位用戶在使用
		// 1.確認目前的請求url
		String url=req.getRequestURL().toString();
		System.out.println("這是在SettingInformController中---------請求的url:" + url);
		

		
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		
		Integer userId=userInformation.getId();
		String userAccount = userInformation.getUserAccount();
        String username = userInformation.getUsername();
        String phonenumber =userInformation.getPhonenumber();
        String email = userInformation.getEmail();
        String password = userInformation.getPassword();
   

        // 把資料放進 settingInform
        // 現在從前端傳過來的通知設定可以知道是哪一位使用者了
        settingInform.setUserId(userId);
        settingInform.setUserAccount(userAccount);
       
        
        System.out.println( "settingInform.getUserId()----"+ settingInform.getUserId());
        
        
        // 用JWT 中保存的使用者id 去資料庫找是否有這個使用者的通知設定的資料
        if(settingInformService.findByJPA(settingInform) != null )
        {
        	// 如果用戶的設定資料已經存在資料庫裡面了，就去更新通知設定


        	System.out.println("這裡是settingInformController 成功查詢到用戶資料會是List<SettingInform>型態========"
    				+ settingInformService.findByJPA(settingInform));
    		System.out.println("這裡是settingInformController 成功查詢到用戶資料getFollowInformState========"
    				+ settingInformService.findByJPA(settingInform).get(0).getFollowInformState());
    		
    		System.out.println("這裡是settingInformController 成功查詢到用戶資料getLikeInformState========"
    				+ settingInformService.findByJPA(settingInform).get(0).getLikeInformState());
    		
    		System.out.println("這裡是settingInformController 成功查詢到用戶資料getPostInformState========"
    				+ settingInformService.findByJPA(settingInform).get(0).getPostInformState());
    		
    		System.out.println("這裡是settingInformController 成功查詢到用戶資料getOpenState========"
    				+ settingInformService.findByJPA(settingInform).get(0).getOpenState());
    		
    		
    		// 執行更新
//    		settingInformService.update(settingInform , userId);
    		
    		
    		settingInformService.updateByJPA(settingInform);
    		
    		System.out.println("執行更新通知設定的結果===========" + settingInformService.updateByJPA(settingInform));
    		

    		
    		
    		return settingInformService.updateByJPA(settingInform);
        	
        	
        	
        	
        	
        	
        }
        else
        {
        	
        	//如果用戶的設定資料尚未存在資料庫裡面，就馬上Insert into 進去
        	
//        	Result res = settingInformService.insertByJPA(settingInform);
        	Result res = settingInformService.addOne(settingInform, userId);
    		
        	
        	
        	// 回傳Insert into成功的訊息回來
        	System.out.println("---------------------------成功幫還未有通知設定資料的用戶插入通知設定-----------------------------------");
        	
        	System.out.println(res.getData());
        	
        	return res;
        	
        	
        }
	            
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	





	@RequestMapping("/messageSetting")
	@ResponseBody
	public List<SettingInform> findAll(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim .letYouKnowWhoIsYourDaddy(req);
		Integer userId = userInformation.getId();
		List<SettingInform> res = settingInformService.findAll(userId);
		return res;
	}








//	@RequestMapping("/messageSetting")
//	@ResponseBody
//	public List<SettingInform> findOne(Integer userid) {
//		List<SettingInform> res = settingInformService.findOne(userid);
//		return res;
//	}









	// 顯示頁面給使用者
	@GetMapping("/performNewestInform")
	public String performNewestInform()
	{
		
		return "newestInform";
		
	}
	
	

}

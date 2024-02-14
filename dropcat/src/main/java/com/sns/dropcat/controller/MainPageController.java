package com.sns.dropcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.dropcat.model.FanList;
import com.sns.dropcat.model.FollowingList;
import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.PostService;
import com.utils.JwtParseAllClaim;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainPageController {

	PostService postService;

	

	public MainPageController(PostService postService) {
		super();
		this.postService = postService;
	}

//	// 取得貼文資料
//	@GetMapping("/MainPagePost")
//	// 不加這個就是回傳.html檔
//	@ResponseBody
//	public Result getMainPagePost(Model model, HttpServletRequest req) {
//		Result res = postService.getMainPagePost();
//		String url=req.getRequestURL().toString();
//		System.out.println("這是在SettingInformController中------------------請求的url:" + url);
//		
//
//		
//		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
//		
//		Integer userId=userInformation.getId();
//		String userAccount = userInformation.getUserAccount();
//        String username = userInformation.getUsername();
//        String phonenumber =userInformation.getPhonenumber();
//        String email = userInformation.getEmail();
//        String password = userInformation.getPassword();
//   
//		return res;
//	}
	
	@GetMapping("/MainPagePost")
	// 不加這個就是回傳.html檔
	@ResponseBody
	public Result getAllMainPagePost(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim .letYouKnowWhoIsYourDaddy(req);
		Integer userId = userInformation.getId();
		
		Result res = postService.getAllMainPagePost(userId);
		
   
		return res;
	}
	
//	@GetMapping("/MainPagePost")
	@GetMapping("/MainPagePost/{setLimit}/{postPage}")
	// 不加這個就是回傳.html檔
	@ResponseBody
	public Result getMainPagePost(@PathVariable Integer postPage,@PathVariable Integer setLimit, HttpServletRequest req) {
		
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim .letYouKnowWhoIsYourDaddy(req);
		Integer userId = userInformation.getId();
		Result res = postService.getMainPagePost(userId, postPage, setLimit);
		
		return res;
	}

	@GetMapping("/LikeUsers/{postContextId}")
	@ResponseBody
	public Result getLikeUsers(@PathVariable Integer postContextId) {
		Result res = postService.getLikeUsers(postContextId);
		return res;
	}
	
	
	
	@GetMapping("/followingList")
	@ResponseBody
	public Result getfollowingListandFanList( HttpServletRequest req) {
		
		
			

		
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		
		Integer userId=userInformation.getId();
		
        Result res = postService.getfollowingListandFanList(userId);
        System.out.println(res);
		return res;
	}

	@GetMapping("/dropcat")
	public String postdata(Model model) {
		return "mainPage";
	}
	
	
	@PostMapping("/insertFollowingList")
	@ResponseBody
	public Result insertFollowingList(@RequestBody FollowingList followingList, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		followingList.setUserID(userInformation.getId());
		System.out.println(followingList.getFollowingUserID());
		Result res = postService.insertFollowingList(followingList);
		return res;
	}
	

	
	@DeleteMapping("/deleteFollowingList")
	@ResponseBody
	public Result deleteFollowingList(@RequestBody FollowingList followingList, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		followingList.setUserID(userInformation.getId());
		Result res = postService.deleteFollowingList(followingList);
		return res;
	}


	
}

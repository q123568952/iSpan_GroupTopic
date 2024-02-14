package com.sns.dropcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.dropcat.model.Blacklist;
import com.sns.dropcat.model.FollowingList;
import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.PersonalPageService;
import com.sns.dropcat.service.PostService;
import com.utils.JwtParseAllClaim;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PersonalPageController {
	
	PersonalPageService personalPageService;
	PostService postService;
	
	@Autowired
	public PersonalPageController(PersonalPageService personalPageService,PostService postService) {
		super();
		this.personalPageService = personalPageService;
		this.postService = postService;
	}

	// 用來顯示網頁在畫面上
	@GetMapping( "/personalPage")
	public String getPersonalPage(Model model) {
		
		return "PersonalPage";
	}
	
	@GetMapping( value ="/personalPageForOthers-{userAccount}")
	public String getPersonalPageForOthers(Model model, @PathVariable String userAccount, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String whoAmI =userInformation.getUserAccount();
		if(whoAmI.equals(userAccount)){
			return getPersonalPage(model);
		}
		
		model.addAttribute("userAccount", userAccount);
		return "PersonalPageForOthers";
	}

	@GetMapping( "/personalPageUserData/{userAccount}")
	@ResponseBody
	public Result getPersonalPageUserData(@PathVariable String userAccount,HttpServletRequest req) {
		if(userAccount.equals("null")) {
			LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
			userAccount=userInformation.getUserAccount();
		}
		
		Result PPUResult = personalPageService.getPersonalPageUser(userAccount);
		return PPUResult;
	}
	
	@GetMapping( "/personalPagePostData/{userAccount}")
	@ResponseBody
	public Result getPersonalPagePostData(@PathVariable String userAccount, HttpServletRequest req) {
		if(userAccount.equals("null")) {
			LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
			userAccount=userInformation.getUserAccount();
		}
		
		Result PPPResult = personalPageService.getPersonalPagePost(userAccount);
		return PPPResult;
	}
	
	@GetMapping( "/personalPageCollectionData")
	@ResponseBody
	public Result getPersonalPageCollectionData(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId=userInformation.getId().toString();
		Result PPCResult = personalPageService.getPersonalPageCollection(userId);
		return PPCResult;
	}
	
	@GetMapping( "/personalPageSurfingHistoryData")
	@ResponseBody
	public Result getPersonalPageSurfingHistoryData(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId=userInformation.getId().toString();
		Result PPSHResult = personalPageService.getPersonalPageSurfingHistory(userId);
		return PPSHResult;
	}
	
	@GetMapping("/getTotalFollowing/{UserID}")
	@ResponseBody
	public Result getTotalFollowing(@PathVariable String UserID,HttpServletRequest req) {
		Result res = personalPageService.getTotalFollowing(UserID);
		return res;
	}
	@GetMapping("/getfollowingstatuas/{userAccount}")
	@ResponseBody
	public Result getfollowingstatuas(@PathVariable String userAccount,HttpServletRequest req) {
		if(userAccount.equals("null")) {
			return null;
		}
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId=userInformation.getId().toString();
		Result res = personalPageService.getfollowingstatuas(userId,userAccount);
		return res;
	}
	
	@GetMapping("/getblackstatuas/{userAccount}")
	@ResponseBody
	public Result getblackstatuas(@PathVariable String userAccount,HttpServletRequest req) {
		if(userAccount.equals("null")) {
			return null;
		}
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId=userInformation.getId().toString();
		Result res = personalPageService.getblackstatuas(userId,userAccount);
		return res;
	}
	
	@PostMapping("/insertblackList")
	@ResponseBody
	public Result insertFollowingList(@RequestBody Blacklist blackList, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		blackList.setBlockerID(userInformation.getId());
		Result res = personalPageService.insertblackList(blackList);
		return res;
	}
	

	
	@DeleteMapping("/deleteblackList")
	@ResponseBody
	public Result deleteFollowingList(@RequestBody Blacklist blackList, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		blackList.setBlockerID(userInformation.getId());
		Result res = personalPageService.deleteblackList(blackList);
		return res;
	}
	
}

package com.sns.dropcat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.EditProfileService;
import com.utils.JwtParseAllClaim;

import jakarta.servlet.http.HttpServletRequest;

// import com.sns.dropcat.service.SettingInformService;

@RestController
public class TestController {

	
	EditProfileService editProfileService;

	@Autowired
	public TestController(EditProfileService editProfileService) {
		this.editProfileService = editProfileService;
	}

	@GetMapping("/LayoutTemplate")
	public String testLayout(Model model) {
		return "LayoutTemplate";
	}

	
	@PostMapping("/postnewprofile")
	public Result editProfile(@RequestBody UserInfo userinfo,HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		Integer userId=userInformation.getId();
		userinfo.setId(userId);
		UserInfo newInfo = editProfileService.packProfile(userinfo);
		Result res = editProfileService.setProfile(newInfo);
		return res;
	}



}

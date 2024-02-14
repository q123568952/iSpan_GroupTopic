package com.sns.dropcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.dropcat.model.Information;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.InformationService;

@Controller
public class SettingInformPageController 
{
	@Autowired
	InformationService informationService;
	
	
	
//	要呈現html，通知功能的那一頁
	@GetMapping("/settingInform")
	public String list(Model model)
	{
		
//		List<Information> informationList=informationService.findAll();
//		
//		System.out.println(informationList.toString());
		
		return  "/settingInform";
		
	}
	
	
	
//	要呈現html
	@GetMapping("/newestInform")
	public String list132(Model model)
	{
		

		
		return  "/newestInform";
		
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
}

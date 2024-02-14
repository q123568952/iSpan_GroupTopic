package com.sns.dropcat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.respository.SearchRepository;
import com.sns.dropcat.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService
{
	// JDK 動態代理
	@Autowired
	SearchRepository repository;
	
	
	
	
	@Override
	public List<UserInfo> findUser(UserInfo userInfo) 
	{
		
		// 用userAccount 來搜尋
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withIgnorePaths("username")
				.withIgnorePaths("phonenumber")
				.withIgnorePaths("email")
				.withIgnorePaths("password")
				.withIgnorePaths("createtime")
				.withIgnorePaths("edittime")
				.withIgnorePaths("usericon")
				.withIgnorePaths("introduction")
				.withIgnorePaths("gender")
				.withIgnorePaths("LineId")
				.withIgnorePaths("LineProfile")
				.withIgnoreCase("userAccount")// 設置忽略大小寫
				.withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
				
		System.out.println("這是在SearchServiceImpl" + userInfo);		
		
		
		// 透過Example 構建查詢條件
		Example<UserInfo> example=Example.of(userInfo, matcher);

		List<UserInfo> list=(List<UserInfo>) repository.findAll(example);
		
		return list;
		
		
		
		
		
		
		
	}

}

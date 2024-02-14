package com.sns.dropcat.service;

import java.util.List;

import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;

public interface UserService {
	List<UserInfo> findAll();
	Result addUser(UserInfo userInfo);
	
	
	UserInfo selectUser(UserInfo userInfo);
	
	
	
	Result getRegisterUser(UserInfo userInfo);
	
	
	
	// test ，要創interface
	List<UserInfo> findUserJPA(UserInfo userInfo);
	Result getUserIcon(String userId);
	
	
	
}

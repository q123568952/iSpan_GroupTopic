package com.sns.dropcat.dao;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;

public interface UserDao 
{

	// extends JpaRepository<UserInfo, Integer>

	List<UserInfo> findAll();
	Result addUser(UserInfo userInfo);
	
	
	UserInfo selectUser(UserInfo userInfo);
	
	
	
	
	Result getRegisterUser(UserInfo userInfo);
	Result getUserIcon(String userId);

	Result updateUserProfile(UserInfo userInfo);
	
	
	
	
	
	
	
	
	
}

package com.sns.dropcat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.UserDao;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.respository.UserInfoRespository;
import com.sns.dropcat.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	UserDao dao;
	
	@Autowired
	public UserServiceImpl(UserDao dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public List<UserInfo> findAll() {
		List<UserInfo> userlist =dao.findAll();
		return userlist;
		
	}

	@Override
	@Transactional
	public Result addUser(UserInfo userInfo){
		userInfo.setCreatetime(new Date());
		userInfo.setEdittime(new Date());
		Result res = dao.addUser(userInfo);
		return res;
	}
	

	
	@Override
	public UserInfo selectUser(UserInfo userInfo) {
		
		return dao.selectUser(userInfo);
		
		 
	}

	
	
	@Override
	public Result getRegisterUser(UserInfo userInfo) {

		
		
		
		return dao.getRegisterUser(userInfo);
	}
	
	
	
	
	
	
	
	///////////////////////////////////////
	@Autowired
	UserInfoRespository repository;
	
	// test
	@Override
	public List<UserInfo> findUserJPA(UserInfo userInfo) {

		
		return	repository.getUser(userInfo.getuserAccount());
		
		
	
	}
	
	
	@Override
	public Result getUserIcon(String userId) {
		Result res = dao.getUserIcon(userId);
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

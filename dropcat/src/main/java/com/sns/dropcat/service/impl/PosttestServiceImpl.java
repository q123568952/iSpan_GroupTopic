package com.sns.dropcat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.PosttestDao;
import com.sns.dropcat.dao.UserDao;
import com.sns.dropcat.model.Posttest;
import com.sns.dropcat.service.PosttestService;

@Service
public class PosttestServiceImpl implements PosttestService {

	PosttestDao dao;
	
	@Autowired
	public PosttestServiceImpl(PosttestDao dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public List<Posttest> findAll() {
		List<Posttest> postlist =dao.findAll();
		return postlist;
		
	}

}

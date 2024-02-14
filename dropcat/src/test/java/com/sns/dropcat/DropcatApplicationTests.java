package com.sns.dropcat;

import java.io.Console;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.sns.dropcat.dao.UserPreferenceDao;
import com.sns.dropcat.service.AddPersonalPostService;

import jakarta.transaction.Transactional;

@SpringBootTest
class DropcatApplicationTests {
	
	 UserPreferenceDao userPreferenceDao;
	
	
	@Autowired
	public DropcatApplicationTests(UserPreferenceDao userPreferenceDao) {
		super();
		this.userPreferenceDao = userPreferenceDao;
	}

	
//	@Test 
//	void testgetType() {
//		
//		Post post = addPersonalPostService.getType(new Post(null,null,"我我我我我我我我我我我我我我我我我我",null,null,null,null,null));
//		System.out.println(post.getPostType());
//	}
	
//	@Test
//	@Transactional
//	void testinsertPrefer() {
//		userPreferenceDao.addUserPreference("57", "1");
//		System.out.println("ok");
//	}
}

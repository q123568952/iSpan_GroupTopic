package com.sns.dropcat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;

public interface SearchRepository extends JpaRepository<UserInfo, Integer>
							,QueryByExampleExecutor<UserInfo>
{

	
//	給SearchServiceImpl 用的
	
	
	
	
}

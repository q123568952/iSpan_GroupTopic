package com.sns.dropcat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;


public interface SettingInformService 
{
	
	
	List<SettingInform> findAll(Integer userId);
	
	
	
	List<SettingInform> findOne(Integer userid);
	Result getBlockController(Integer id);
	
	
	Result addOne(SettingInform settingInform, Integer userId);
	
	
	
	Result update(SettingInform settingInform ,Integer userId);
	
	
	
	
	
	List<SettingInform> findByJPA(SettingInform settingInform);
	
	
	
	Result updateByJPA(SettingInform settingInform);
	
	
	Result insertByJPA(SettingInform settingInform);
	
	
	

}

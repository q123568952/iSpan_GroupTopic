package com.sns.dropcat.dao;

import java.util.List;

import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.replyfomat.Result;

//@Mapper
public interface SettingInformDao 
{
	
	List<SettingInform> findAll(Integer userId);
	
	// 使用UserId 來找是否已經有用戶的設定資料已經存在資料庫裡面了
	List<SettingInform> findOne(Integer userid);
	
	// 檢視黑名單
	Result getBlockController(Integer id);
	
	// 應加單1筆用戶通知設定的資料進資料庫
	Result addOne(SettingInform settingInform, Integer userid);
	

	// 更新用戶通知設定的資料
	Result update(SettingInform settingInform ,Integer userId);
	
}

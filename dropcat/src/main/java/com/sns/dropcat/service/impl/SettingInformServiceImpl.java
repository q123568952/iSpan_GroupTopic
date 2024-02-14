package com.sns.dropcat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.SettingInformDao;
import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.respository.SettingInformRespository;
import com.sns.dropcat.service.SettingInformService;


@Service
public class SettingInformServiceImpl implements SettingInformService
{
	@Autowired
	SettingInformDao settingInformDao;
	
	// 找全部的用戶通知設定資料
	@Override
	@Transactional
	public List<SettingInform> findAll(Integer userId) {

		List<SettingInform> settingInformlist=settingInformDao.findAll(userId);

		
		return settingInformlist;
	}

	
	
	@Override
	@Transactional
	public Result getBlockController(Integer id) {
		Result res = settingInformDao.getBlockController(id);
		return res;
	}



	// 藉由UserId來找單1用戶通知設定資料
	@Override
	@Transactional
	public List<SettingInform> findOne(Integer userId) {
		
		
		List<SettingInform> settingInformfindByUserId=
				settingInformDao.findOne(userId);
		
		
		return settingInformfindByUserId;
	}


	// 增加單1筆用戶通知設定資料
	@Override
	@Transactional
	public Result addOne(SettingInform settingInform, Integer userId)
	{
		settingInform.setSettingInformationTime(new Date());
		Result res = settingInformDao.addOne(settingInform, userId);
		return res;
	}


	@Override
	@Transactional
	@Modifying
	public Result update(SettingInform settingInform, Integer userId) 
	{

		settingInform.setSettingInformationTime(new Date());
		
		
		Result res = settingInformDao.update(settingInform, userId);
		return res;
	}


	
	///////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private SettingInformRespository settingInformRespository;

	
	@Override
	public List<SettingInform> findByJPA(SettingInform settingInform) {


		List<SettingInform> settingInformUser= settingInformRespository.findSettingInform(settingInform.getUserId());
		
		
		if(settingInformUser.size()==0)
		{
			return null;
		}
		else
		{
			return settingInformUser;
		}
		
		
	}



	@Override
	@Transactional
	@Modifying
	public Result updateByJPA(SettingInform settingInform) 
	{

		int updateSuccessRow= settingInformRespository.updateSettingInform(
				settingInform.getUserId()
				, settingInform.getPostInformState()
				, settingInform.getFollowInformState()
				, settingInform.getLikeInformState()
				, settingInform.getOpenState());

		settingInform.setSettingInformationTime(new Date());
		
		return new Result(true, "UPDATE_SettingInform_SUCCESS", updateSuccessRow);
	}



	@Override
	@Transactional
	@Modifying
	public Result insertByJPA(SettingInform settingInform) {

		System.out.println("這是在 SettingInformServiceImpl中");
		System.out.println("settingInform.getUserId()======" + settingInform.getUserId());
		
		settingInform.setSettingInformationTime(new Date());
		
		int insertsettingInformBySelect =settingInformRespository.insertSettingInform(settingInform.getUserId());
		
		
		
		return new Result(true, "ADD_settingInformData_SUCCESS",insertsettingInformBySelect);
	}



	
	
	
	
	
	
	


		
	
	
	
	
	
	
	
	
	
	
	
	
	

}

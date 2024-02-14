package com.sns.dropcat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;


@Component
public interface SettingInformRespository extends JpaRepository<SettingInform, Integer>
{
	
	@Query(value="SELECT * FROM SettingInform SIF WHERE SIF.userId=:userId", nativeQuery = true)
	List<SettingInform> findSettingInform(@Param("userId") Integer userId);
	
	
	
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE SettingInform SIF "
			+ "SET SIF.postInformState=:postInformState,"
			+ "SIF.followInformState=:followInformState,"
			+ "SIF.likeInformState=:likeInformState, SIF.openState=:openState WHERE SIF.userId=:userId"
			,nativeQuery = true)
	int updateSettingInform(
			  @Param("userId") Integer userId 
			, @Param("postInformState") String postInformState
			, @Param("followInformState") String followInformState
			, @Param("likeInformState") String likeInformState
			, @Param("openState") String openState
			);
	
	
	
	
	
	// 2024/1/26 功能還沒完全
	// 2024/1/27 新增一筆 openState
	@Transactional
	@Modifying
	@Query(value="INSERT INTO SettingInform(postInformState, followInformState ,likeInformState, openState) SELECT postInformState, followInformState ,likeInformState, openState FROM SettingInform SIF WHERE SIF.userId=:userId"
			,nativeQuery = true)
	int insertSettingInform(@Param("userId") Integer userId );
	
	
	
	
	
	
	
	

}

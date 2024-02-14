package com.sns.dropcat.respository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.model.UserInfo;

/**
 * 在這個Class中使用JPA的技術完成曾刪改查的操作 
 * JpaRepository<T, ID>
 * T: 表示操做的實體類是誰 (在實體類上配置了和表的映射關係)
 * ID: 表示操作的實體類中對應哪格˙主見的數據類型，和實體類的@ID屬性修飾的數據類型一致
 */ 


public interface UserInfoRespository extends  JpaRepository<UserInfo, Integer>
{
	// @Query() 表示增、刪、改、查 操作的註解
	// nativeQuery 參數表示開啟原生SQL語句語法規則
	// Pageable表是一個分業的物件。指定起始的數據和最終的數據
	@Query(value="SELECT * FROM UserInfo WHERE id =?1 " 
			, nativeQuery=true)
	List<UserInfo> getCommentPage(Integer id, Pageable pageable);// 第一頁的數據，每頁數據顯示3個
	
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE UserInfo  UI "
			+ "SET UI.userAccount=?1 "
			+ "WHERE UI.id=?1 "
	, nativeQuery=true)
	int updateUserInfo(String author, Integer id);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE UserInfo  UI  WHERE UI.id=?1 "
	, nativeQuery=true)
	int deleteUserInfo(String author, Integer id);
	
	
	
	
	
	
	
	/////////////////////////////////////////////////
	// 查詢
	@Query(value="SELECT * FROM UserInfo "
			+ "WHERE userAccount =:xxxxx " 
			,nativeQuery = true)
	List<UserInfo> getUser(@Param("xxxxx") String xxxxx);
	
	
	
	// 修改
	@Transactional
	@Modifying    // 通知springdata是增刪改的操作
	@Query(value="UPDATE UserInfo UI SET UI.email =: xxxxx "
			+ "WHERE UI.userAccount=: zzz"
	,nativeQuery = true)
	int updateUser(@Param("xxxxx") String newEmail, @Param("zzz") String userAccount);// 第一頁的數據，每頁數據顯示3個
	
	
	
	
	
	
	
	
	

}

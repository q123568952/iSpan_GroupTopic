package com.sns.dropcat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.model.Information;
import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;

@Component
public interface InformationRespository extends JpaRepository<Information, Integer>
{
	
	
	
	/////////////////////////////////////////////////
	// 查詢偶像發文
	@Query(value="SELECT * FROM Information "
			+ "WHERE othersUserID =:xxxxx " 
			,nativeQuery = true)
	List<Information> getInformationByUserId(@Param("xxxxx") Integer xxxxx);
	
	
	
	
	
	
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO Information(userAccount, othersAccount ,postId, postType) SELECT userAccount, othersAccount, postId ,postType FROM Information IF WHERE IF.userId=:userId"
			,nativeQuery = true)
	int insertSettingInform(@Param("userId") Integer userId );
	
	
	
	
	

}

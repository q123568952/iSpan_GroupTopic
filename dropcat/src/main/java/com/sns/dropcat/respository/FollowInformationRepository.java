package com.sns.dropcat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.sns.dropcat.model.FollowInformation;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.replyfomat.Result;



@Component
public interface FollowInformationRepository extends JpaRepository<FollowInformation, Integer>
{
	
	
		/////////////////////////////////////////////////
		// 查詢
		@Query(value="SELECT * FROM FollowInformation "
		+ "WHERE followedUserId =:xxxxx " 
		,nativeQuery = true)
		List<FollowInformation> getFollowInformationByUserId(@Param("xxxxx") Integer xxxxx);
		
	
	
	
	
	
	

}

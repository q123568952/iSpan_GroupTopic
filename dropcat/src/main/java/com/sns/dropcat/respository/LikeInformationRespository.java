package com.sns.dropcat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.sns.dropcat.model.FollowInformation;
import com.sns.dropcat.model.Information;
import com.sns.dropcat.model.tellYouHaveLike;




@Component
public interface LikeInformationRespository extends JpaRepository<tellYouHaveLike, Integer>
{
	
	// 用發文者的id去查詢 全部的按讚表
		@Query(value="SELECT * FROM tellYouHaveLike "
				+ "WHERE post_userId =:xxxxx " 
				,nativeQuery = true)
		List<tellYouHaveLike> find_WhoLikeYourPost_By_Post_UserId(@Param("xxxxx") Integer xxxxx);
		
		@Query(value="SELECT * FROM tellYouHaveLike "
				+ "WHERE give_You_Like_PostId =:postContextId " 
				,nativeQuery = true)
		List<tellYouHaveLike> find_WhoOwnThisPost(@Param("postContextId") Integer postContextId);
	

}

package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.AddPersonalPostDao;
import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AddPersonalPostDaoImpl implements AddPersonalPostDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Result addPost(Post post, List<PostImg> postImgs) {
		try {
		entityManager.persist(post);
		postImgs.forEach((e)->{
			e.setPostId(post.getPostId());
			entityManager.persist(e);
		});
		}catch(Exception e) {
			return new Result(false, MessageConstant.ADD_Post_FAILED,null);
		}
		
		return new Result(true, MessageConstant.ADD_Post_SUCCESS,null);
	}

}

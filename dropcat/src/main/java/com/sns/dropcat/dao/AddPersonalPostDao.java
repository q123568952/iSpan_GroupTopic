package com.sns.dropcat.dao;

import java.util.List;

import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.replyfomat.Result;

public interface AddPersonalPostDao {
	Result addPost(Post post, List<PostImg> postImgs);
	
}

package com.sns.dropcat.service;

import java.util.List;
import java.util.Map;

import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.model.typeKeys;
import com.sns.dropcat.replyfomat.Result;

public interface AddPersonalPostService {
	Post packPost(Map<String, Object> postinfo);
	List<PostImg> packPostImg(Map<String, Object> postinfo);
	Result addPost(Post post, List<PostImg> postImgs);
	List<typeKeys> getAlltypeKeys();
	Post getType(Post post);
}

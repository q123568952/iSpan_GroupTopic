package com.sns.dropcat.dao;

import java.util.List;

import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.model.UserInfo;

public interface AwsS3Dao {
	List<PostImg> uploadImg(List<PostImg> postImgs);
	UserInfo uploadUserIcon(UserInfo userinfo);

	Boolean deleteawspost(List<PostImg> imgdelete);
}

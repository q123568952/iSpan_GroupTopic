package com.sns.dropcat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.AddPersonalPostDao;
import com.sns.dropcat.dao.AwsS3Dao;
import com.sns.dropcat.dao.TypeKeysDao;
import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.model.typeKeys;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.AddPersonalPostService;

@Service
public class AddPersonalPostServiceImpl implements AddPersonalPostService {
	
	AddPersonalPostDao addPersonalPostDao;
	AwsS3Dao awsS3Dao;
	TypeKeysDao typeKeysDao;
	
	
		@Autowired
	public AddPersonalPostServiceImpl(AddPersonalPostDao addPersonalPostDao,AwsS3Dao awsS3Dao,TypeKeysDao typeKeysDao) {
		super();
		this.addPersonalPostDao = addPersonalPostDao;
		this.awsS3Dao = awsS3Dao;
		this.typeKeysDao = typeKeysDao;
	}

	@Override
	public Post packPost(Map<String, Object> postinfo) {
		
		Post post = new Post(null, Integer.valueOf((String)postinfo.get("userId")), (String)postinfo.get("text"),(Double)postinfo.get("lng") , (Double)postinfo.get("lat"), null, null,null);
		
		return post;
	}
	
	@Override
	public List<PostImg> packPostImg(Map<String, Object> postinfo) {
		List<Map<String, String>> imgs = (List)postinfo.get("picture");
		List<PostImg> postImgs = new ArrayList<PostImg>();
		imgs.forEach((e)->{
			String imgURL = null;
			Integer imgSerial = null;
			for (Entry<String,String> entry: e.entrySet()) {
				imgSerial = Integer.valueOf(entry.getKey());
				imgURL = entry.getValue();
			}
			PostImg postIMG = new PostImg(null, null,imgURL, imgSerial);
			postImgs.add(postIMG);
		});
		
		return postImgs;
	}

	@Override
	@Transactional
	public Result addPost(Post post, List<PostImg> postImgs) {
		postImgs = awsS3Dao.uploadImg(postImgs);
		post.setCreatetime(new Date());
		post.setEdittime(new Date());
		Result res =addPersonalPostDao.addPost(post, postImgs);
		return res;
	}
	
	//取出所有typekey
		@Override
		public List<typeKeys> getAlltypeKeys() {
			List<typeKeys> keys=typeKeysDao.findAll();
			return keys;
		}


	//判定貼文種類
		@Override
		public Post getType(Post post) {
			String text =post.getPosttext();
			List<typeKeys> keys=getAlltypeKeys();
			Map<Integer, Integer> typeScore = new HashMap<Integer, Integer>();
			for (typeKeys e : keys) {
				String key = e.getTypekey();
//				key被點到幾次
				Integer count=0;
				Integer index=0;
				while ((index = text.indexOf(key, index)) != -1) {
				    count++;
				    index += key.length();
				}
				Integer type=e.getTypeId();
				typeScore.merge(type, count,(oldValue, newValue) -> oldValue + newValue);
			}
//			System.out.println(typeScore);
//			TypeScore蒐集完所有type分數
			 Entry<Integer, Integer> maxtype = null;
			 Boolean flag = false;
		        for (Entry<Integer, Integer> entry : typeScore.entrySet()) {
		            if (maxtype == null || entry.getValue().compareTo(maxtype.getValue()) > 0) {
		            	maxtype = entry;
		            	flag = maxtype.getValue()>0;
		            }
		        }
		    
//			確認type後填入post中
			post.setPostType(flag? maxtype.getKey():0);
			return post;
		}

}

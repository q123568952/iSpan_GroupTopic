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
import com.sns.dropcat.dao.UserDao;
import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.model.typeKeys;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.EditProfileService;

@Service
public class EditProfileImpl implements EditProfileService {

    AddPersonalPostDao addPersonalPostDao;
    AwsS3Dao awsS3Dao;
    TypeKeysDao typeKeysDao;
    UserDao userDao;

    @Autowired
    public EditProfileImpl(AddPersonalPostDao addPersonalPostDao, AwsS3Dao awsS3Dao, TypeKeysDao typeKeysDao,UserDao userDao) {
        super();
        this.addPersonalPostDao = addPersonalPostDao;
        this.awsS3Dao = awsS3Dao;
        this.typeKeysDao = typeKeysDao;
        this.userDao = userDao;
    }

    @Override
    public UserInfo packProfile(UserInfo info) {
        UserInfo newInfo = awsS3Dao.uploadUserIcon(info);
        return newInfo;
    }

    @Override
    @Transactional
    public Result setProfile(UserInfo info) {
        Result res = userDao.updateUserProfile(info);
        return res;
    }
}

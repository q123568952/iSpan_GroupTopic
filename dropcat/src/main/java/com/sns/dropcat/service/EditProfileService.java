package com.sns.dropcat.service;

import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;


public interface EditProfileService {
    UserInfo packProfile(UserInfo info);
    Result setProfile(UserInfo info);
}

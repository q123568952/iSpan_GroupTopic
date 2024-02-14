package com.sns.dropcat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sns.dropcat.model.UserInfo;
import java.util.Optional;

public interface LoginDao extends JpaRepository<UserInfo, Integer> {

	UserInfo findByUserAccount(String userAccount);

	UserInfo findByPhonenumber(String phonenumber);

	UserInfo findByEmail(String email);

	UserInfo findByPassword(String password);

	UserInfo findByResetToken(String resetToken);


}

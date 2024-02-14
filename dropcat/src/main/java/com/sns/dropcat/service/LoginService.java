package com.sns.dropcat.service;

import java.util.Date;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.dropcat.dao.LoginDao;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	public LoginService(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public UserInfo findByEmail(String email) {
		return loginDao.findByEmail(email);
	}

	public UserInfo findByResetToken(String resetToken) {
		return loginDao.findByResetToken(resetToken);
	}

	public void save(UserInfo user) {
		loginDao.save(user);
	}

	// 判斷登入方式
	public UserInfo Login(String identifier, String passwd) {

		UserInfo userInfo = new UserInfo();

		if (identifier.contains("@")) {
			userInfo = loginDao.findByEmail(identifier);
		} else if (identifier.matches("^09\\d{8}$")) {
			userInfo = loginDao.findByPhonenumber(identifier);
		} else {
			userInfo = loginDao.findByUserAccount(identifier);
		}

		if (userInfo != null && validatePassword(passwd, userInfo.getPassword(), userInfo)) {
			return userInfo;
		}

		return null;

	}

	// 用BCryptPasswordEncoder加密登錄密碼並驗證
	private boolean validatePassword(String inputPasswd, String storedPasswd, UserInfo user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (storedPasswd.startsWith("$2a$")) {

			return encoder.matches(inputPasswd, storedPasswd);

		} else {

			if (inputPasswd.equals(storedPasswd)) {

				String encryptedPassword = encoder.encode(inputPasswd);
				user.setPassword(encryptedPassword);
				loginDao.save(user);
				return true;

			}

			return false;
		}

		// return encoder.matches(inputPasswd, storedPasswd);
	}

	// 存入token
	public Result saveResetToken(String email, String resetToken) {

		UserInfo userInfo = loginDao.findByEmail(email);

		if (userInfo == null) {
			return Result.error("Not Found");
		}

		userInfo.setResetToken(resetToken);
		userInfo.setTokenTime(new Date());
		loginDao.save(userInfo);

		return Result.success("resetToken儲存成功");
	}

	// 驗證token時效，超過30分鐘就過期
	public boolean verifyToken(String token) {

		UserInfo userInfo = loginDao.findByResetToken(token);
		if (userInfo == null || userInfo.getTokenTime() == null) {
			return false;
		}

		long tokenTime = new Date().getTime() - userInfo.getTokenTime().getTime(); // 設定token時效
		long tokenTimeMin = TimeUnit.MILLISECONDS.toMinutes(tokenTime); // 毫秒轉換成分鐘

		return tokenTimeMin < 30;
	}

	// 更新密碼加密
	@Transactional
	public boolean changePassword(String token, String newPassword) {

		UserInfo user = loginDao.findByResetToken(token);
		if (user != null) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encryptedPassword = encoder.encode(newPassword);
			user.setPassword(encryptedPassword);

			// user.setPassword(newPassword);
			loginDao.save(user);
			return true;
		}
		return false;
	}

}

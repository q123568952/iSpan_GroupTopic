package com.sns.dropcat.controller;

import java.util.HashMap;

import java.util.Map;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import com.sns.dropcat.model.UserInfo;

import com.sns.dropcat.service.LoginService;
import com.sns.dropcat.service.MailService;
import com.sns.dropcat.service.UserService;
import com.utils.JwtUtils;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginCheckController {

	LoginService loginService;
	UserService userService;
	MailService mailService;

	@Autowired
	public LoginCheckController(LoginService loginService, MailService mailService) {
		this.loginService = loginService;
		this.mailService = mailService;
	}

	// 驗證firebase提供的JWT token
	@PostMapping("/verifyToken")
	public ResponseEntity<?> verifyToken(HttpSession httpSession, @RequestBody String jsonString,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		try {

			ObjectMapper obM = new ObjectMapper();
			JsonNode jNode = obM.readTree(jsonString);
			String idToken = jNode.path("idToken").asText();

			// 檢查idtoken是否跟前端的一樣
			System.out.println("ID Token: " + idToken);

			FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
			String email = decodedToken.getEmail();

			UserInfo user = loginService.findByEmail(email);

			if (user == null) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到用戶，請註冊");

			} else {

				// 設定JWT裡面要帶的訊息
				Map<String, Object> claims = new HashMap<>();
				claims.put("id", user.getId());
				claims.put("userAccount", user.getuserAccount());
				claims.put("username", user.getUsername());

				// 生成JWT
				String jwt = JwtUtils.generateJwt(claims);
				System.out.println("JWT:" + jwt);
				httpSession.setAttribute("userAccount", user.getuserAccount());
				
				Cookie myCookie = new Cookie("tokenFromJava", jwt);
	    		myCookie.setHttpOnly(true);
	    		response.addCookie(myCookie);
				return ResponseEntity.ok(jwt);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed: " + e.getMessage());

		}
	}

	// 本地登入驗證發放jwt
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(HttpSession httpSession, @RequestBody Map<String, String> credentials,
			HttpServletResponse response) {

		String identifier = credentials.get("username");
		String password = credentials.get("password");

		UserInfo user = loginService.Login(identifier, password);

		if (user != null) {
			Map<String, Object> claims = new HashMap<>();
			claims.put("id", user.getId());
			claims.put("userAccount", user.getuserAccount());
			claims.put("username", user.getUsername());

			String jwt = JwtUtils.generateJwt(claims);
			System.out.println("JWT:" + jwt);

			Map<String, String> responseBody = new HashMap<>();
			responseBody.put("token", jwt);
			httpSession.setAttribute("userId", user.getId());

			Cookie myCookie = new Cookie("tokenFromJava", jwt);
			myCookie.setHttpOnly(true);
			response.addCookie(myCookie);
			return ResponseEntity.ok(responseBody);

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("帳號或密碼錯誤，請重新輸入");
		}
	}

	// line登入
	@PostMapping("/linelogin")
	public ResponseEntity<?> linelogin(HttpSession httpSession, @RequestBody Map<String, Object> userData,HttpServletResponse response) {

		String userEmail = (String) userData.get("email");
		System.out.println(userEmail);
		UserInfo user = loginService.findByEmail(userEmail);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到用戶，請註冊");

		} else {

			// 設定JWT裡面要帶的訊息
			Map<String, Object> claims = new HashMap<>();
			claims.put("id", user.getId());
			claims.put("userAccount", user.getuserAccount());
			claims.put("username", user.getUsername());

			// 生成JWT
			String jwt = JwtUtils.generateJwt(claims);
			System.out.println("JWT:" + jwt);
			httpSession.setAttribute("userAccount", user.getuserAccount());
			
			Cookie myCookie = new Cookie("tokenFromJava", jwt);
    		myCookie.setHttpOnly(true);
    		response.addCookie(myCookie);
			return ResponseEntity.ok(jwt);

		}
	}

	// 寄送忘記密碼重設驗證信
	@PostMapping("/resetpassword")
	public ResponseEntity<?> sendresetEmail(@RequestParam String email) {

		UserInfo user = loginService.findByEmail(email);

		if (user == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("res", "error");
			response.put("notfoundmessage", "找不到與該電子郵件相對應的用戶");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		try {

			String resetToken = UUID.randomUUID().toString();
			loginService.saveResetToken(email, resetToken);
			loginService.verifyToken(resetToken);
			
			//下面是本地端用
			//String resetLink = "http://localhost:8080/reset-password?token=" + resetToken; 
			
			//下面是上線用
			String resetLink = "https://dropcat.fun/reset-password?token=" + resetToken;
			mailService.TemplateMail(email, resetLink);

			Map<String, Object> response = new HashMap<>();
			response.put("res", "success");
			response.put("message", "重置密碼連結已發送到您的Email");

			return ResponseEntity.ok(response);

		} catch (MessagingException e) {

			e.printStackTrace();

			Map<String, Object> response = new HashMap<>();
			response.put("res", "error");
			response.put("errormessage", "無法發送郵件");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	// 更新密碼
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody Map<String, String> payload) {
		String token = payload.get("token");
		String newPassword = payload.get("newPassword");

		// 驗證token是否有效
		if (!loginService.verifyToken(token)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("token無效或已過期");
		}

		// 嘗試更新密碼
		boolean updateSuccess = loginService.changePassword(token, newPassword);
		if (updateSuccess) {
			return ResponseEntity.ok("密碼更新成功");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("密碼更新失敗");
		}
	}
}

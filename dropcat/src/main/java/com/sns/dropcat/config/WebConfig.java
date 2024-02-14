package com.sns.dropcat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.dropcat.interceptor.LoginCheckInterceptor;

// 配置類 如果要注册的bean对象来自于第三方（不是自定义的），
@Configuration
public class WebConfig implements WebMvcConfigurer
{
	// 注入攔截器
	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		 // addInterceptor()用來設定需要攔截哪些資源
		// excludePathPatterns()用來設定不需要攔截哪些資源
		registry.addInterceptor(loginCheckInterceptor)
				.addPathPatterns("/*")
				.excludePathPatterns("/register")
				.excludePathPatterns("/realRegister1")
				.excludePathPatterns("/login")
				.excludePathPatterns("/logout")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/verifyToken")
				.excludePathPatterns("/mainPage")
				.excludePathPatterns("/findUser")
				.excludePathPatterns("/morganexplorepost/{page}/{uploadamount}")
				.excludePathPatterns("/morganexploremap/{lat}/{lng}/{dis}")
				.excludePathPatterns("/morganpopout/{postId}")
				.excludePathPatterns("/morganpopoutliked")
				.excludePathPatterns("/morganpopoutcollected")
				.excludePathPatterns("/morganpopoutcomment")
				.excludePathPatterns("/morganaddpostpersonalpost")
//				.excludePathPatterns("/updateInformationData")
				.excludePathPatterns("/logoutlogout")
				.excludePathPatterns("/resetMail")
				.excludePathPatterns("/resetpassword")
				.excludePathPatterns("/reset-password")
				.excludePathPatterns("/resetpassword?email=")
				.excludePathPatterns("/reset-password?token=")
				.excludePathPatterns("/reset-request")
				.excludePathPatterns("/changePassword")
				.excludePathPatterns("/linelogin")
				;
				
				
		
		
		
	}
	
	
	
	
	
	
	
	

}

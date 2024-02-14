package com.sns.dropcat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.service.LoginService;
import com.sns.dropcat.service.PosttestService;
import com.sns.dropcat.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	UserService userService;
	PosttestService posttestService;
	LoginService loginService;
	PasswordEncoder passwordEncoder;

	@Autowired
	public PageController(UserService userService, PosttestService posttestService, LoginService loginService) {
		super();
		this.userService = userService;
		this.posttestService = posttestService;
		this.loginService = loginService;
	}

	@GetMapping("/findall")
	public String findAll(Model model) {
		List<UserInfo> userlist = userService.findAll();
		System.out.println(userlist.toString());
		model.addAttribute("users", userlist);
		return "findall";
	}

	// 使用@annotation 切入點表達式，需要在 連接點 上增加 自己設定的註解
	// 連接點 為由AOP控制的方法
//	@MyLog
//	@GetMapping("/explorepage")
	@RequestMapping("/explorepage")
	public String postdata(Model model) {

		return "explorepage";
	}

//	@GetMapping("/explorepage")
//	@PostMapping("/explorepage")
//	@RequestMapping("/explorepage")
	public String postdata(@RequestHeader("Cookie") String tokenFromJS, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("________________________________explorepage裡面________________________________");

//		String  headerForToken=tokenFromJS.getAttribute("tokenFromJava");

//		System.out.println("headerForToken是:" + headerForToken);

		String url = request.getRequestURL().toString();
		System.out.println("url==============" + url);

//		String token = request.getHeader("token");
//		
//		System.out.println("在explorepage裡面印出token============" + token);
		System.out.println("________________________________________________________________");

		String contextPath = request.getContextPath();
		System.out.println("在explorepage裡面印出contextPath====================" + contextPath);
		System.out.println(contextPath);

		return "explorepage";
	}

//	@RequestMapping("/explorepage")
//	public String postdata(@RequestHeader("Cookie") String tokenFromJS , Model model, HttpServletRequest request, HttpServletResponse response) {
//		
////		try 
////		{
////			request.getRequestDispatcher("/findall").forward(request,response);
////		} 
////		catch (ServletException | IOException e) 
////		{
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
//		System.out.println("________________________________explorepage裡面________________________________");
//		
////		String  headerForToken=tokenFromJS.getAttribute("tokenFromJava");
//		
////		System.out.println("headerForToken是:" + headerForToken);
//		
//		
//		String url = request.getRequestURL().toString();
//		System.out.println("url==============" + url);
//		
////		String token = request.getHeader("token");
////		
////		System.out.println("在explorepage裡面印出token============" + token);
//        System.out.println("________________________________________________________________");
//	        
//		
//		
//		
//        
//        
//        String contextPath=request.getContextPath();
//        System.out.println("在explorepage裡面印出contextPath====================" + contextPath);
//        System.out.println(contextPath);
////		try {
////			response.sendRedirect("/explorepage");
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//        
//        
//        
//        
//        
//		
//		
//		
//		
//		return "explorepage";
//	}
//	
//	

	// 使用@annotation 切入點表達式，需要在 連接點 上增加 自己設定的註解
	// 連接點 為由AOP控制的方法

	@GetMapping("/register")
	public String regist() {

		return "register";
	}

	// 測試新招
//	@PostMapping("/explorepage")
	public ModelAndView explorePage() {
		ModelAndView modelAndView = new ModelAndView("explorepage"); // 视图的名称，对应HTML文件
		// 添加任何需要传递到HTML的数据

		return modelAndView;
	}

//	@RequestMapping("/myPage1")
	@ResponseBody
	public ModelAndView myPage(HttpSession session) {
//        createSharedList();
		return new ModelAndView("redirect:/explorepage");
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
//歷史紀錄按鈕回到個人頁，透過record attribute判斷直接切到歷史紀錄頁
	@GetMapping("/record")
	public String record(Model model) {
		model.addAttribute("record", true);
		return "PersonalPage";
	}

	@GetMapping("/resetMail")
	public String resetMail() {
		return "sendresetMail";
	}

	@GetMapping("/resetPassword")
	public String reset() {
		return "resetPassword";
	}

	@GetMapping("/reset-password")
	public String resetPassword(Model model, @RequestParam String token) {

		UserInfo user = loginService.findByResetToken(token);

		if (user == null) {
			return "redirect:/reset-request?error=invalid";
		}

		model.addAttribute("token", token);
		return "resetPassword";
	}

}

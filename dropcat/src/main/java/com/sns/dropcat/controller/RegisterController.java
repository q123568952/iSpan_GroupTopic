package com.sns.dropcat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.SettingInformService;
import com.sns.dropcat.service.UserService;
import com.utils.JwtUtils;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



//加了 @ResponseBody 的Class 下的方法的回傳值都會作為響應數據
// @RestController=@Controller +@ResponseBody
// 目前用來測試
//@SessionAttributes("token")
//@Controller
@RestController
public class RegisterController 
{
	@Autowired
	UserService userService;
	
	
/////////////////////////////////////////////////////////////
	
	@PostMapping("/findUser")
	@ResponseBody
	public UserInfo findUser(@RequestBody UserInfo userInfo)
	{
		System.out.println("這是從前端傳過來的資料 userInfo.getuserAccount():" + userInfo.getuserAccount());
//		System.out.println(userInfo.getuserAccount());
		System.out.println("這是從前端傳過來的物件 userInfo:" + userInfo);
		System.out.println("=================================================================");
		System.out.println("這是從後端傳過來搜尋過後的物件 userService.selectUser(userInfo):" + userService.selectUser(userInfo));
		
		
		
		
		return userService.selectUser(userInfo);
		
		 
	}
	
	
	


	
	///////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	freemarker.template.Configuration freemarkerConfig;
	
	
	
	
	@Autowired
	SettingInformService settingInformService;
	
	// 已完成的註冊邏輯	
	@PostMapping("/realRegister1")
	@ResponseBody
	public Result realRegister11111(@RequestBody UserInfo userInfo,Model model, HttpSession httpSession, HttpServletResponse response) throws Exception 
	{
		// 1.根據頁面提交的用戶資料去資料庫取出這筆資料
		Result userList=userService.getRegisterUser(userInfo);
		// 取出從資料回來的用戶資料
		UserInfo user=(UserInfo) userList.getData();
		
		System.out.println("user是從資料庫取得回來的資料======" + user);
		
		
		
		// 如果沒有從資料庫取回用戶的資料，就馬上增加這筆用戶資料進去資料庫
		if( user == null)
		{
			System.out.println("此用戶尚未註冊=================" + user);
			
			// 密碼用BCryptPasswordEncoder方法加密
			String encryptedPassword = new BCryptPasswordEncoder().encode(userInfo.getPassword());
			userInfo.setPassword(encryptedPassword);

			// 新增用戶資料到資料庫
			Result res = userService.addUser(userInfo);
			System.out.println("新增1筆用戶資料到資料庫=======" + res);
			
			
			
			
			
			// 根據頁面提交的用戶資料去資料庫取出這筆資料
			Result registerUserListResult=userService.getRegisterUser(userInfo);
			
			UserInfo registerUser=(UserInfo) registerUserListResult.getData();
			System.out.println("這裡是RegisterController=====剛剛成功新增到資料庫的用戶資料========" + registerUser); 
			 
			 
			// 判斷剛剛註冊的用戶是否有成功註冊，判斷註冊用戶是否存在
	        if(registerUser != null)
	        {
	        	// 如果註冊用戶存在﹑就開始生成1個JWT給這個用戶
	            //自定義的訊息
	            Map<String , Object> claims = new HashMap<>();
	            claims.put("id",registerUser.getId());
	            claims.put("userAccount", registerUser.getuserAccount());
	            claims.put("username",registerUser.getUsername());
	            claims.put("phonenumber",registerUser.getPhonenumber());
	            claims.put("email",registerUser.getEmail());
	            claims.put("password",registerUser.getPassword());
	            claims.put("usericon",registerUser.getUsericon());
	            claims.put("introduction",registerUser.getUsername());

	            
	            

     
	            
	            
	            
	            // 2024/1/30
	            // 設置Session
	            // key
	            httpSession.setAttribute("userId", registerUser.getId());

	            System.out.println("在register設置的httpSession的Key:userId");
	            System.out.println( "在register設置的httpSession的Value:"+ httpSession.getAttribute("userId"));
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            

	            //使用JWT，生成令牌
	            // generateJwt() 參數要放入自訂義的訊息
	            String jwt = JwtUtils.generateJwt(claims);
	            
//	            System.out.println("JWT為===========" + jwt);

	            
	            
	          
	            
	            System.out.println("這是解析後的token--這是在register中---" + JwtUtils.parseJWT(jwt));
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            // 註冊成功，發送Email通知
	      
//	            MimeMessage mimeMessage = mailSender.createMimeMessage();
//
//                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//                helper.setFrom("thefeelgood999@gmail.com");
//                helper.setTo(registerUser.getEmail());
//                helper.setSubject("歡迎加入 DropCat");
//                helper.setText("<!DOCTYPE html>\r\n"
//                		+ "<html lang=\"en\">\r\n"
//                		+ "<head>\r\n"
//                		+ "    <meta charset=\"UTF-8\">\r\n"
//                		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
//                		+ "    <title>Document</title>\r\n"
//                		+ "\r\n"
//                		+ "    <!-- 在dropCat的eclipse引用的CSS -->\r\n"
//                		+ "    <link rel=\"stylesheet\" href=\"./css/cssForRegister.css\">\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "</head>\r\n"
//                		+ "<body style=\" width: 100vw;\r\n"
//                		+ "height: 100vh; background-repeat: no-repeat;background-image: linear-gradient(25deg, #f6008a, #f45c7e, #ef8671, #e7a961);\">\r\n"
//                		+ "\r\n"
//                		+ "<div >\r\n"
//                		+ "\r\n"
//                		+ "    \r\n"
//                		+ "    <div style=\"display: flex; justify-content: center; align-items: center;\">\r\n"
//                		+ "        <h1>\r\n"
//                		+ "            歡迎加入DropCat\r\n"
//                		+ "\r\n"
//                		+ "        </h1>\r\n"
//                		+ "    </div>\r\n"
//                		+ "    <div  style=\"display: flex; justify-content: center; align-items: center;\">\r\n"
//                		+ "\r\n"
//                		+ "        <img src=\"cid:magic1\" alt=\"\">\r\n"
//                		+ "    </div>\r\n"
//                		+ "\r\n"
//                		+ "</div>\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "    \r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "    \r\n"
//                		+ "</body>\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ " \r\n"
//                		+ " \r\n"
//                		+ " \r\n"
//                		+ " \r\n"
//                		+ " \r\n"
//                		+ " \r\n"
//                		+ "\r\n"
//                		+ "\r\n"
//                		+ "</html>", true);
//
//                
//	              FileSystemResource file1 = new FileSystemResource(new File("D:\\學習程式的檔案\\1228gitCloneSNS\\SNS\\dropcat\\src\\main\\resources\\static\\img\\magic2.png"));
//	              helper.addInline("magic1", file1);
//                
////                FileSystemResource file2 = new FileSystemResource(new File("dropcat\\src\\main\\resources\\static\\img\\magic2.png"));
////                helper.addInline("magic1", file2);
//
//                mailSender.send(mimeMessage);
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	          
	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.setFrom("cuezoe@gmail.com");
	            helper.setTo(registerUser.getEmail());
	            helper.setSubject("歡迎加入 DropCat");
	            
//	            Map<String, Object> model2 = new HashMap<String, Object>();
//	            model2.put("userName", "Lebron99999999999");
//	            String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("email.html"), model);
//	            helper.setText(templateString,true);
//	            
//	            
//	            
//	           
//	            FileSystemResource file = new FileSystemResource(new File("D:\\學習程式的檔案\\1228gitCloneSNS\\SNS\\dropcat\\src\\main\\resources\\static\\img\\magic2.png"));
//                helper.addInline("thisAFile", file);
//	            
//	            
//	            mailSender.send(mimeMessage);
//	            System.out.println("成功發送Email------------");
	            
	            
	            
	            
	            
	            Context context = new Context();
	    		context.setVariable("userName", registerUser.getUsername());
//	    		context.setVariable("resetLink", resetLink);

	    		String template = templateEngine.process("email.html", context);
	    		helper.setText(template, true);
	    		mailSender.send(mimeMessage);
	            
	            
	            
	            
	            
	            
	            
	            
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		// 2024/1/29
	    		// 註冊成功馬上inset into 1筆資料到SettingInform 表
	    		
	    		SettingInform newRegisterForsettingInform=new SettingInform();
	    		
	    		
	    		newRegisterForsettingInform.setUserAccount(registerUser.getuserAccount());
	    		newRegisterForsettingInform.setUserId(registerUser.getId());
	    		newRegisterForsettingInform.setOpenState("1");
	    		newRegisterForsettingInform.setFollowInformState("1");
	    		newRegisterForsettingInform.setLikeInformState("1");
	    		newRegisterForsettingInform.setPostInformState("1");
	    		
	    		
	    		settingInformService.addOne(newRegisterForsettingInform, newRegisterForsettingInform.getUserId());
	         
	            
	    		
	    		
	    		
	    		
	    		
	    		
	    		
//	    		response.setHeader('S', 'sessionID=123456; HttpOnly');
	    		
	    		// 2024/2/1
	    		// 為Cookie 設置 HttpOnly
	    		Cookie myCookie = new Cookie("tokenFromJava", jwt);
	    		myCookie.setHttpOnly(true);
	    		response.addCookie(myCookie);
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
//	    		response.addHeader("Set-Cookie", "tokenFromJava=112; Path=/; HttpOnly");
	            
	            
	            
	            return Result.success(jwt);
	            
	            
	        }
	        
	        return Result.error("錯誤");
		 
			
			
			
			
			
			
			
			
		}
		else
		{
			System.out.println("----------------------此用戶已註冊================");
			
			
			
			// 根據頁面提交的用戶資料去資料庫取出這筆資料
			Result registerUserListResult=userService.getRegisterUser(userInfo);
			
			UserInfo registerUser=(UserInfo) registerUserListResult.getData();
			System.out.println("這裡是RegisterController====印出已註冊的用戶的用戶資料========" + registerUser); 
			 
			
			//判斷註冊用戶是否存在
	        if(registerUser != null)
	        {
	            //自定義的訊息
	            Map<String , Object> claims = new HashMap<>();
	            claims.put("id",registerUser.getId());
	            claims.put("userAccount", registerUser.getuserAccount());
	            claims.put("username",registerUser.getUsername());
	            claims.put("phonenumber",registerUser.getPhonenumber());
	            claims.put("email",registerUser.getEmail());
	            claims.put("password",registerUser.getPassword());
	            claims.put("usericon",registerUser.getUsericon());
	            claims.put("introduction",registerUser.getUsername());

	            


	            
	            
	            
	            
	            
	            // 2024/1/30
	            // 設置Session
//	            httpSession.setAttribute("id", claims.get(registerUser.getId()));
	            // key
//	            httpSession.setAttribute("userAccount", registerUser.getuserAccount());
//	            
//	            
//	            System.out.println("在register設置的httpSession的Key:userAccount");
//	            System.out.println( "在register設置的httpSession的Value:"+ httpSession.getAttribute("userAccount"));
//	            
//	            
	            
	            
	            
	            
	            
	            
	            
	            
	            // 2024/1/30
	            // 設置Session
	            // key
	            httpSession.setAttribute("userId", registerUser.getId());
	          
	            
	            
	            System.out.println("在register設置的httpSession的Key:userId");
	            System.out.println( "在register設置的httpSession的Value:"+ httpSession.getAttribute("userId"));
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            

	            //使用JWT，生成令牌
	            // generateJwt() 參數要放入自訂義的訊息
	            String jwt = JwtUtils.generateJwt(claims);
//	            System.out.println("JWT為======這是在register裡面==" + jwt);
//	            model.addAttribute("token", jwt);
	            
	            
	            
	            System.out.println("這是解析後的token--這是在register中---" + JwtUtils.parseJWT(jwt));
	            
	            
	            
	            
	            
	            // 2024/2/1
	    		// 為Cookie 設置 HttpOnly
	    		Cookie myCookie = new Cookie("tokenFromJava", jwt);
	    		myCookie.setHttpOnly(true);
	    		response.addCookie(myCookie);
	            
	            
	            
	            
	            
	            
	            
	            System.out.println("----這是在register裡面---成功為已註冊的用戶生成1個JWT----------------");
	            return Result.success(jwt);
	            
	        }
	        
	        return Result.error("錯誤");
		 
			
			
			
			
		}
		

	        
	        
	        
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////
	

	
	// 實現登出用
	//  用來刪除我們自己設定的JWT 所屬的Cookie，來實現登出的作用
	@RequestMapping("/logoutlogout")
	@ResponseBody
    public Result logout(HttpServletRequest req, HttpServletResponse resp){


		String jwt=null;
		Cookie settingCookie=null;
		 
		Cookie[] mapForCookie= req.getCookies();
		
		for(int i=0; i< mapForCookie.length; i++)
		{
			System.out.println(mapForCookie[i].getName());
			System.out.println(mapForCookie[i].getValue());
			
			if(mapForCookie[i].getName().equals("tokenFromJava"))
			{
				// 從Cookie中獲取已生成的jwt
				// 把 存在Cookie中的token賦值給jwt
				jwt=mapForCookie[i].getValue();
				
				System.out.println("mapForCookie[i].getValue()=======");
				System.out.println(mapForCookie[i].getValue());
				
				// 獲取自己設定的JWT 所在的Cookie
				settingCookie=mapForCookie[i];
				
				System.out.println("這是我們自己設定的JWT所屬的Cookie====" + settingCookie);
				
				
				
				
				
//				Cookie newCookie=new Cookie("username",null); //假如要删除名称为username的Cookie
//			    newCookie.setMaxAge(0); //立即删除型
//			    newCookie.setDomain(domain);//保存cookie的IP地址,则是删除这个IP的cookie
//			    newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
//			    newCookie.setHttpOnly(httpOnly);
//			    resp.addCookie(newCookie); //重新写入，将覆盖之前的
//		
				
			}
			
			
			
		}
		
		
		 System.out.println("印出放在Cookie中的jwt============" + jwt);
	
		 // 刪除Cookie
//		settingCookie.setMaxAge(0);
	
		
//		settingCookie.setPath("/");//该路径为创建Cookie时设定的访问路径
//		resp.addCookie(settingCookie);
//		System.out.println("刪除過後的Cookie====" + settingCookie);
		
		
		
		 //新的Cookie 覆盖 旧的Cookie，實現刪除Cookie
        Cookie settingCookie2 = new Cookie("tokenFromJava", "");
		
        settingCookie2.setPath("/");//该路径为创建Cookie时设定的访问路径
		resp.addCookie(settingCookie2);
		System.out.println("刪除過後的Cookie====" + settingCookie2);
		
		
		
		
		
		
		Result responseResult = Result.success("成功刪除我們自己設定的JWT所屬的Cookie，登出成功！");
        //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
        String json = JSONObject.toJSONString(responseResult);
        resp.setContentType("application/json;charset=utf-8");
        //響應
        try 
        {
//			resp.getWriter().write(json);
        	System.out.println("這是在logoutlogout的Controller----成功刪除我們自己設定的JWT所屬的Cookie，登出成功==========");
		} 
        catch (Exception e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        // 刪除JWT了，沒有JWT 就自動跳轉到登入頁
        try 
        {
			resp.sendRedirect("/login");
		} 
        catch (IOException e) 
        {
			
			e.printStackTrace();
		}
        
        
        
 
        return Result.success("這是在logoutlogout的Controller---成功刪除我們自己設定的JWT所屬的Cookie，登出成功！");
 
    }

	
	
	
	
	


	
	
	

}

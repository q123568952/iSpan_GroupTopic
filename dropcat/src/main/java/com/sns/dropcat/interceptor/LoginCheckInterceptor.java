package com.sns.dropcat.interceptor;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.replyfomat.Result;

import com.utils.JwtUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 定義攔截器
// 交給IOC容器
@Component
public class LoginCheckInterceptor implements HandlerInterceptor
{
	 //目標資源方法執行前執行。 返回true：放行    返回false：不放行
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {

		
		
		
		
		
		System.out.println("preHandle ..................................................................... ");
		  
		// 1.獲取請求url
		String url=req.getRequestURL().toString();
		System.out.println("這是在Interceptor中-------------請求的url:" + url);
		
		
		
		// 現在的使用者的Id
//		Integer userId=JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req).getId();
//		System.out.println("現在的使用者 id==========" + userId);

		
		
		
		
		// 2.判斷請求url 中是否包含login，如果包含說明是登入操作，直接放行
		
		if(url.contains("login"))
		{
			System.out.println("登入操作，放行");
			
			// Interceptor放行，直接return true 就放行了
			return true;
			
			
		}
		
		// 3.獲取請求頭中的令牌
//		String jwt=req.getHeader("token");
		
		
		String jwt=null;
		Cookie settingCookie=null;
		// 去Cookie找上一個request就已經放好的JWT 
		Cookie[] mapForCookie= req.getCookies();
		
		
		if(mapForCookie != null)
		{
			for(int i=0; i< mapForCookie.length; i++)
			{
//				System.out.println(mapForCookie[i].getName());
//				System.out.println(mapForCookie[i].getValue());
				
				if(mapForCookie[i].getName().equals("tokenFromJava"))
				{
					// 從Cookie中獲取已生成的jwt
					// 把 存在Cookie中的token賦值給jwt
					jwt=mapForCookie[i].getValue();
					
//					System.out.println("mapForCookie[i].getValue()=======");
//					System.out.println(mapForCookie[i].getValue());
					
					// 獲取自己設定的JWT 所在的Cookie
					settingCookie=mapForCookie[i];
					
//					System.out.println("這是我們自己設定的JWT所屬的Cookie====" + settingCookie);
					
				}
				
				
				
			}
			
			
//			 System.out.println("在Interceptor印出放在Cookie中的jwt====" + jwt);
			
			 
		
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		for(int i=0; i< mapForCookie.length; i++)
//		{
////			System.out.println(mapForCookie[i].getName());
////			System.out.println(mapForCookie[i].getValue());
//			
//			if(mapForCookie[i].getName().equals("tokenFromJava"))
//			{
//				// 從Cookie中獲取已生成的jwt
//				// 把 存在Cookie中的token賦值給jwt
//				jwt=mapForCookie[i].getValue();
//				
////				System.out.println("mapForCookie[i].getValue()=======");
////				System.out.println(mapForCookie[i].getValue());
//				
//				// 獲取自己設定的JWT 所在的Cookie
//				settingCookie=mapForCookie[i];
//				
////				System.out.println("這是我們自己設定的JWT所屬的Cookie====" + settingCookie);
//				
//			}
//			
//			
//			
//		}
//		
//		
//		 System.out.println("在Interceptor印出放在Cookie中的jwt====" + jwt);
//	
		
		
		
		
//		String  headerForToken=tokenFromJS.getAttribute("tokenFromJava");
//		System.out.println("headerForToken是:" + headerForToken);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 //4.判斷令牌是否存在，如果不存在，返回錯誤结果（未登入）
        if(!StringUtils.hasLength(jwt))
        {
//            log.info("Token不存在");

            Result responseResult = Result.error("NOT_LOGIN，JWT令牌不存在");
            //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
            String json = JSONObject.toJSONString(responseResult);
            resp.setContentType("application/json;charset=utf-8");
            
            
            //響應
            resp.getWriter().write(json);
            resp.getWriter().write("這裡是Interceptor裡面，還沒有拿到JWT---------------------");
            
            
            
            System.out.println("這裡是Interceptor裡面，令牌不存在!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            
            // 還沒有JWT 就自動跳轉到登入頁
            resp.sendRedirect("/login");
            
            
            
            
            
         // Interceptor不放行
            return false;
            
        }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      //5.解析token，如果解析失敗，返回錯誤结果（未登入）
        try 
        {
            JwtUtils.parseJWT(jwt);
            
            System.out.println("這是解析後的token-----這是在Interceptor中---------" + JwtUtils.parseJWT(jwt));
            
            
            
            String username = JwtUtils.parseJWT(jwt).get("username", String.class);
            System.out.println("這是在Interceptor中-----username: " + username);
           
            
          
            
            
        }
        catch (Exception e)
        {
//            log.info("令牌解析失敗!");

            Result responseResult = Result.error("NOT_LOGIN，解析令牌錯誤");
            
            //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
            String json = JSONObject.toJSONString(responseResult);
            


            
            resp.setContentType("application/json;charset=utf-8");
//            //響應
            resp.getWriter().write(json);
            
            
            
            
            System.out.println("這裡是Interceptor裡面，令牌解析失敗-------------------------------");
            
            // 還沒有JWT 就自動跳轉到登入頁
            resp.sendRedirect("/login");
            
            return false;
        }

        
       
        //6.放行，return true;直接放行
        
        
       System.out.println("interceptor...............return true就是放行........................... 放行");
		
		return true;
	
	}

	
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//目标资源方法执行后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 System.out.println("postHandle ......Interceptor放行後邏輯");
		 
		 
		 
		 
//		 request.getRequestDispatcher("/explorepage").forward(request,response);
		 
		 
	}

	
	//视图渲染完毕后執行，最後執行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion ...................................... ");
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

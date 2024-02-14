package com.sns.dropcat.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.replyfomat.Result;
import com.utils.JwtUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Filter類別上加@WebFilter註解，配置攔截資源的路徑
//@WebFilter(urlPatterns =  "/explorepage")
//@WebFilter(urlPatterns =  "/*")
public class LoginCheckFilter implements Filter 
{

	
	// doFilter方法：這個方法是在每一次攔截到請求之後都會被調用，
	//所以這個方法是會被調用多次的，每攔截到一次請求就會調用一次doFilter()方法。
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		//前置：強轉為http的請求對象、響應對象 （轉換原因：要使用子類中特有的方法）
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        
        System.out.println("Filter攔截到請求---------============------這裡是Filter放行前的邏輯---------------------------");
        
        
      //1.取得url
        String url = req.getRequestURL().toString();
//        log.info("request的路徑：{}", url); //request路徑：http://localhost:8080/login

        System.out.println("本次請求的網站url===============================" + url);

        // 定義不需要攔截的路徑
        String[] urls=new String[]{"/login"};
        
        //2.判断請求url中是否包含login，如果包含，說明是登入操作，放行
        if(url.contains("/login"))
        {
        	System.out.println("遇到login直接放行請求==============");
            chain.doFilter(request, response);//放行請求
            return;//結束當前方法的執行
        
    	}
        
     
      //3.如果不是要訪問登入面就要去獲取請求頭中的令牌（token）
        String token = req.getHeader("token");
//        log.info("從請求頭中獲取的令牌：{}",token);
        
        System.out.println("在Filter印出token============" + token);
        System.out.println("________________________________________________________________");
        
        
        
        
        //4.判斷令牌是否存在，如果不存在，返回錯誤结果（未登入）
        if(!StringUtils.hasLength(token))
        {
//            log.info("Token不存在");

            Result responseResult = Result.error("NOT_LOGIN");
            
            //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
            String json = JSONObject.toJSONString(responseResult);
            resp.setContentType("application/json;charset=utf-8");
            
            
            //響應 沒通過放行的訊息到前端頁面上
            resp.getWriter().write(json);
            resp.getWriter().write("響應沒通過放行的訊息到前端頁面上，令牌不存在!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("響應沒通過放行的訊息到前端頁面上，令牌不存在!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            
//            resp.sendRedirect( "/explorepage");
            
            return;
        }

        
        
      //5.解析token，如果解析失敗，返回錯誤结果（未登入）
        try 
        {
            JwtUtils.parseJWT(token);
            System.out.println("解析後的token------------============================---------");
            System.out.println(JwtUtils.parseJWT(token));
            
            
            req.setAttribute("jwtToken111111111111", token);
            
            
            
            
            //////////////////////////////////////////////////////////////////////
//            if(url.contains("/explorepage"))
//            {
//            	System.out.println("遇到login直接放行請求==============");
//                chain.doFilter(request, response);//放行請求
//                return;//結束當前方法的執行
//            
//        	}
            
            
            
            
            
        }
        catch (Exception e)
        {
//            log.info("令牌解析失敗!");

            Result responseResult = Result.error("NOT_LOGIN");
            
            //把Result對象轉換為JSON格式字符串 (fastjson是阿里巴巴提供的用於實現物件和json的轉換工具類)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //響應
            response.getWriter().write(json);
            response.getWriter().write("響應NOT_LOGIN，令牌不存在!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            
            
            System.out.println("令牌解析失敗-------------------------------");
            return;
        }

//        URI uri;
//		try {
//			uri = new URI("http", "localhost:8080", "explorepage", null);
//			String urlString = uri.toURL().toString();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        req.getRequestDispatcher(urlString).forward(request,response);
        
        
        System.out.println(req.getRequestURL().toString());
        System.out.println(req.getRequestURL());
        System.out.println("檢查是否响应是否已经提交" + response.isCommitted());
//        req.getRequestDispatcher("/explorepage").forward(request,response);
        
//        req.getRequestDispatcher("/explorepage").forward(req, resp);
        
        
        // 对用户输入中的URL进行编码
        String encodedURL = URLEncoder.encode(req.getRequestURL().toString(), "UTF-8");

        // 打印编码后的URL
        System.out.println("Encoded URL================" + encodedURL);
//        req.getRequestDispatcher(encodedURL).forward(request,response);
        
//        req.getRequestDispatcher("/explorepage").forward(req,resp);
        
        
        
         
//        req.getRequestDispatcher(req.getRequestURL().toString()).forward(request,response);
        
        
        
        String contextPath=req.getContextPath();
        System.out.println("contextPath為===================================" + contextPath);
//		try {
//			resp.sendRedirect(contextPath +  "/explorepage");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        
        
        
        
        // doFilter()這個方法之前所寫的程式碼屬於放行之前的邏輯。
        //6.放行
        // 放行操作就是去執行Controller
        chain.doFilter(request, response);
        System.out.println("chain.doFilter(request, response);的下面-------------------------------");
        System.out.println("檢查是否响应是否已经提交:" + response.isCommitted());
        
        
        System.out.println("放行後的邏輯------------------------------==========================-");
        
        
//        req.getRequestDispatcher("/explorepage").forward(req,resp);
        
       
        
        
        
        
        
        
        return;

	
	}
	
	
}

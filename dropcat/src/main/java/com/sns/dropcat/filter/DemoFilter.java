package com.sns.dropcat.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;


// Filter 的攔截路徑:目前是 所有路徑
//@WebFilter(urlPatterns =  "/*")
public class DemoFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("init 初始化方法執行了");

//		Filter.super.init(filterConfig);
	}

	
	// doFilter方法：這個方法是在每一次攔截到請求之後都會被調用，
	//所以這個方法是會被調用多次的，每攔截到一次請求就會調用一次doFilter()方法。
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Filter攔截到了請求，放行前邏輯");

		// 執行完放行後，就可以執行 Controller 中的方法，資源訪問完WEB的資源之後還會回到Filter中，只執行放行後的邏輯
        chain.doFilter(request,response);
		
        // 執行完 Controller 中的方法，只執行放行後的邏輯
    	System.out.println("放行後邏輯");
		
	}

	@Override
	public void destroy() {

		System.out.println("destroy 方法執行了");

		
//		Filter.super.destroy();
	}
	

}

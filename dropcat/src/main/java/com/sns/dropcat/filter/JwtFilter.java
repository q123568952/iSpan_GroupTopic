//package com.sns.dropcat.filter;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.sns.dropcat.model.UserInfo;
//import com.sns.dropcat.service.LoginService;
//import com.utils.JwtUtils;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class JwtFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private LoginService loginService;
//	
//	private static final String HEAD_STRING = "Authorization";
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
//			throws ServletException, IOException {
//		String authHeader = req.getHeader(HEAD_STRING);
//		
//		if (authHeader != null && !authHeader.isEmpty()) {
//			String accessToken = authHeader.replace("Bearer ", "");
//			Claims claims = JwtUtils.parseJWT(accessToken);
//			String user = claims.getSubject();
//			
//			if (user != null) {
//				Optional<UserInfo> opt = getUserInfo(user);
//				
//				if (opt.isPresent()) {
//					UserInfo userInfo = opt.get();
//					
//					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), null,userInfo.getAuthorities());
//				}
//			}
//		}
//		
//	}
//
//	private Optional<UserInfo> getUserInfo(String user) {
//		return null;
//	}
//	
//	
//	
//
//}

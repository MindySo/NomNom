//package com.ssafy.mvc.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@Component
//public class AdminInterceptor implements HandlerInterceptor{
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		//로그인한 유저가 관리자인지를 체크
//		HttpSession session = request.getSession();
//		if(!"admin".equals(session.getAttribute("loginUser"))) {
//			response.sendRedirect("list");
//			return false;
//		}
//		return true;
//		
//	}
//}



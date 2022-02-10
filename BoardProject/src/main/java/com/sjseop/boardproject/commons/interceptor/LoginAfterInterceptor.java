package com.sjseop.boardproject.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAfterInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//로그인 후 로그인 or 회원가입 페이지 요청할 시
		HttpSession session = request.getSession();
		if(session.getAttribute("login") != null) {
			response.sendRedirect("/");
			return false;
		}
		
		return true;
	}

}

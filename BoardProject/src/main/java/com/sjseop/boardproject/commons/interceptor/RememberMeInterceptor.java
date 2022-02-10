package com.sjseop.boardproject.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.sjseop.boardproject.domain.UserVO;
import com.sjseop.boardproject.service.UserService;

public class RememberMeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RememberMeInterceptor.class);

	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		//쿠키의 세션ID 확인
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			UserVO userVO = userService.checkLoginBefore(loginCookie.getValue());
			if(userVO != null) {
				session.setAttribute("login", userVO);
			}
		}
		return true;
	}
	
	
	
}

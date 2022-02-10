package com.sjseop.boardproject.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		//기존의 로그인 정보 제거
		if(session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("user");
		
		if(userVO != null) {
			logger.info("new login success");
			session.setAttribute(LOGIN, userVO);
			
			if(request.getParameter("useCookie") != null) {
				logger.info("remember me...");
				//쿠키 생성
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*7);
				response.addCookie(loginCookie);
			}
			
			Object destination = request.getSession().getAttribute("destination");
			response.sendRedirect(destination != null ? (String)destination : "/");
		}
		
	}
	
}

package com.sjseop.boardproject.controller;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;
import com.sjseop.boardproject.service.UserService;

@Controller
@RequestMapping("/user")
public class UserLoginController {

	public static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO) throws Exception {
		return "/user/login";
	}
	
	@PostMapping("/loginPost")
	public void login(LoginDTO loginDTO, HttpSession httpSession, Model model) throws Exception {
		
		logger.info("login POST...");
		UserVO userVO = userService.login(loginDTO);
		
		if(userVO == null || !BCrypt.checkpw(loginDTO.getUserPw(), userVO.getUserPw())) {
			return;
		}
		
		model.addAttribute("user", userVO);
		
		//로그인 유지
		if(loginDTO.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;    //7일
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
			userService.keepLogin(userVO.getUserId(), httpSession.getId(), sessionLimit);
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("logout...");
		Object object = session.getAttribute("login");
		if(object != null) {
			UserVO userVO = (UserVO)object;
			session.removeAttribute("login");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				userService.keepLogin(userVO.getUserId(), "none", new Date(System.currentTimeMillis()));
			}
		}
		return "/user/logout";
	}
	
}

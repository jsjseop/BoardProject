package com.sjseop.boardproject.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjseop.boardproject.domain.UserVO;
import com.sjseop.boardproject.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	// 회원가입 페이지
	@GetMapping("/register")
	public String register() throws Exception {
		logger.info("register GET");
		return "/user/register";
	}
	
	// 회원가입 처리
	@PostMapping("/register")
	public String register(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("register POST");
		String hashedPw = BCrypt.hashpw(userVO.getUserPw(), BCrypt.gensalt());
		userVO.setUserPw(hashedPw);
		userService.register(userVO);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/user/login";
	}
	
	/*
	 * // 로그인 임시
	 * 
	 * @GetMapping("/login") public String login() throws Exception { return
	 * "/user/login"; }
	 */
	
}

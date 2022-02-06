package com.sjseop.boardproject.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(LoginDTO loginDTO, HttpSession httpSession, Model model) throws Exception {
		
		logger.info("login POST...");
		UserVO userVO = userService.login(loginDTO);
		
		if(userVO == null || !BCrypt.checkpw(loginDTO.getUserPw(), userVO.getUserPw())) {
			return;
		}
		
		model.addAttribute("userVO", userVO);
	}
	
}

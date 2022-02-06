package com.sjseop.boardproject.service;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;

public interface UserService {

	// 회원가입 처리
	void register(UserVO userVO) throws Exception;
	// 로그인 처리
	UserVO login(LoginDTO loginDTO) throws Exception;
}
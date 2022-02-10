package com.sjseop.boardproject.persistence;

import java.sql.Date;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;

public interface UserDAO {

	// 회원가입 처리
	void register(UserVO userVO) throws Exception;
	// 로그인 처리
	UserVO login(LoginDTO loginDTO) throws Exception;
	//로그인 유지 처리
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	//세션키 검증
	UserVO checkUserWithSessionKey(String sessionId) throws Exception;
}

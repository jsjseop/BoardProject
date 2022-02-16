package com.sjseop.boardproject.service;

import java.sql.Date;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;

public interface UserService {

	// 회원가입 처리
	void register(UserVO userVO) throws Exception;
	// 로그인 처리
	UserVO login(LoginDTO loginDTO) throws Exception;
	// 로그인 유지 처리
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	//세션키 검증
	UserVO checkLoginBefore(String sessionId) throws Exception;
	
	//회원정보 수정 처리
	public void modifyUser(UserVO userVO) throws Exception;
	//회원정보 조회
	public UserVO getUser(String userId) throws Exception;
	//회원 비밀번호 수정 처리
	public void modifyPw(UserVO userVO) throws Exception;
	//회원 이미지 수정 처리
	public void modifyImg(String userId, String userImg) throws Exception;
}

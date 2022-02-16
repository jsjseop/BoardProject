package com.sjseop.boardproject.persistence;

import java.sql.Date;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;

public interface UserDAO {

	// 회원가입 처리
	public void register(UserVO userVO) throws Exception;
	// 로그인 처리
	public UserVO login(LoginDTO loginDTO) throws Exception;
	//로그인 유지 처리
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	//세션키 검증
	public UserVO checkUserWithSessionKey(String sessionId) throws Exception;
	//로그인 일자 갱신
	public void updateLoginDate(String userId) throws Exception;
	
	//회원정보 수정 처리
	public void updateUser(UserVO userVO) throws Exception;
	//회원정보 조회
	public UserVO getUser(String userId) throws Exception;
	//회원 비밀번호 수정 처리
	public void updatePw(UserVO userVO) throws Exception;
	//회원 이미지 수정 처리
	public void updateImg(String userId, String userImg) throws Exception;
}

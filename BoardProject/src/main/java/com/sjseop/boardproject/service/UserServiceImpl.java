package com.sjseop.boardproject.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;
import com.sjseop.boardproject.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	// 회원가입 처리
	@Override
	public void register(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.register(userVO);
	}

	// 로그인 처리
	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.updateLoginDate(loginDTO.getUserId());
		return userDAO.login(loginDTO);
	}

	@Override
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		// TODO Auto-generated method stub
		userDAO.keepLogin(userId, sessionId, sessionLimit);
	}

	@Override
	public UserVO checkLoginBefore(String sessionId) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.checkUserWithSessionKey(sessionId);
	}

	@Override
	public void modifyUser(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.updateUser(userVO);
	}

	@Override
	public UserVO getUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId);
	}

	@Override
	public void modifyPw(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.updatePw(userVO);
	}

	@Override
	public void modifyImg(String userId, String userImg) throws Exception {
		// TODO Auto-generated method stub
		userDAO.updateImg(userId, userImg);
	}

}

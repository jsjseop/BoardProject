package com.sjseop.boardproject.persistence;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjseop.boardproject.domain.LoginDTO;
import com.sjseop.boardproject.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final String NAMESPACE = "com.sjseop.boardproject.mappers.user.userMapper";
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void register(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".register", userVO);
	}

	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
	}

	@Override
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("sessionId", sessionId);
		paramMap.put("sessionLimit", sessionLimit);
		
		sqlSession.update(NAMESPACE + ".keepLogin", paramMap);
	}

	@Override
	public UserVO checkUserWithSessionKey(String sessionId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".checkUserWithSessionKey", sessionId);
	}

}

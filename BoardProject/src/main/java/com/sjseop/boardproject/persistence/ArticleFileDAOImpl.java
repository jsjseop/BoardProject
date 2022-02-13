package com.sjseop.boardproject.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleFileDAOImpl implements ArticleFileDAO {

	private static final String NAMESPACE = "com.sjseop.boardproject.mappers.upload.articleFileMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addFile(String fullName) throws Exception {
		sqlSession.insert(NAMESPACE + ".addFile", fullName);
	}

}

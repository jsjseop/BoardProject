package com.sjseop.boardproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<String> getArticleFiles(Integer articleNo) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getArticleFiles", articleNo);
	}

	@Override
	public void deleteFiles(Integer articleNo) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteFiles", articleNo);
	}

	@Override
	public void deleteFile(String fileName) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteFile", fileName);
	}

	@Override
	public void replaceFile(String fileName, Integer article_no) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fileName", fileName);
		paramMap.put("article_no", article_no);
		
		sqlSession.insert(NAMESPACE + ".replaceFile", paramMap);
	}

	@Override
	public void updateFileCnt(Integer article_no) throws Exception {
		sqlSession.update(NAMESPACE + ".updateFileCnt", article_no);
	}

}

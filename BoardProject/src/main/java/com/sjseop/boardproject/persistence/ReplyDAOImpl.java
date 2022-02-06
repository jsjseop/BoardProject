package com.sjseop.boardproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	private static String NAMESPACE = "com.sjseop.boardproject.mappers.reply.replyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> list(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".list", article_no);
	}

	@Override
	public void create(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".create", replyVO);
	}

	@Override
	public void update(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".update", replyVO);
	}

	@Override
	public void delete(Integer reply_no) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".delete", reply_no);
	}

	@Override
	public List<ReplyVO> listPaging(Integer article_no, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("article_no", article_no);
		paramMap.put("criteria", criteria);
		
		return sqlSession.selectList(NAMESPACE + ".listPaging", paramMap);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".countReplies", article_no);
	}

	@Override
	public int getArticleNo(Integer reply_no) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".getArticleNo", reply_no);
	}

}
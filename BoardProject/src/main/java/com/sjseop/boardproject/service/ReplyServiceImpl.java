package com.sjseop.boardproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.domain.ReplyVO;
import com.sjseop.boardproject.persistence.ArticleDAO;
import com.sjseop.boardproject.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private ArticleDAO articleDAO;

	@Override
	public List<ReplyVO> list(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.list(article_no);
	}

	@Transactional
	@Override
	public void create(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.create(replyVO); //댓글 등록
		articleDAO.updateReplyCnt(replyVO.getArticle_no(), 1);  //댓글 수 증가
	}

	@Override
	public void update(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.update(replyVO);
	}

	@Transactional
	@Override
	public void delete(Integer reply_no) throws Exception {
		// TODO Auto-generated method stub
		int article_no = replyDAO.getArticleNo(reply_no); //댓글의 게시글 번호 조회
		replyDAO.delete(reply_no); //댓글 삭제
		articleDAO.updateReplyCnt(article_no, -1); //댓글 수 감소
	}

	@Override
	public List<ReplyVO> getRepliesPaging(Integer article_no, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.listPaging(article_no, criteria);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.countReplies(article_no);
	}

	@Override
	public List<ReplyVO> userReplies(String userId) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.userReplies(userId);
	}

}

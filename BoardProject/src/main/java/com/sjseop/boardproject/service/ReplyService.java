package com.sjseop.boardproject.service;

import java.util.List;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.domain.ReplyVO;

public interface ReplyService {

	List<ReplyVO> list(Integer article_no) throws Exception;
	
	void create(ReplyVO replyVO) throws Exception;
	
	void update(ReplyVO replyVO) throws Exception;
	
	void delete(Integer reply_no) throws Exception;
	
	List<ReplyVO> getRepliesPaging(Integer article_no, Criteria criteria) throws Exception;
	
	int countReplies(Integer article_no) throws Exception;
	
	List<ReplyVO> userReplies(String userId) throws Exception;
}

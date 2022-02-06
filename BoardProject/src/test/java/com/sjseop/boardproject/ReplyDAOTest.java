package com.sjseop.boardproject;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.domain.ReplyVO;
import com.sjseop.boardproject.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReplyDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);
	
	@Autowired
	private ReplyDAO replyDAO;
	
	//@Test
	public void testReplyCreate() throws Exception {
		
		for(int i=1; i<100; i++) {
			ReplyVO replyVO = new ReplyVO();
			replyVO.setArticle_no(5);
			replyVO.setReply_text(i + "번째 댓글입니다.");
			replyVO.setReply_writer("user0" + i%10);
			replyDAO.create(replyVO);
		}
	}
	
	//@Test
	public void testReplyList() throws Exception {
		
		logger.info(replyDAO.list(5).toString());
	}
	
	//@Test
	public void testReplyModify() throws Exception {
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReply_no(1);
		replyVO.setReply_text("찐1번째 댓글");
		replyDAO.update(replyVO);
	}
	
	//@Test
	public void testReplyDelete() throws Exception {
		replyDAO.delete(2);
	}
	
	@Test
	public void testReplyListPaging() throws Exception {
		
		Criteria criteria = new Criteria();
		criteria.setPage(1);
		criteria.setPerPageNum(10);
		
		List<ReplyVO> list = replyDAO.listPaging(5, criteria);
		
		for(ReplyVO reply : list) {
			logger.info(reply.getArticle_no() + " : " + reply.getReply_text());
		}
	}
}

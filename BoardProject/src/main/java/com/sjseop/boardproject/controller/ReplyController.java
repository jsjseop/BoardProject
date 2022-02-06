package com.sjseop.boardproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.commons.paging.PageMaker;
import com.sjseop.boardproject.domain.ReplyVO;
import com.sjseop.boardproject.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	//댓글 등록 처리
	@PostMapping("")
	public ResponseEntity<String> register(@RequestBody ReplyVO replyVO) {
		
		ResponseEntity<String> entity = null;
		try {
			replyService.create(replyVO);
			entity = new ResponseEntity<String>("regSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 목록 전체 조회
	@GetMapping("/all/{article_no}")
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("article_no") Integer article_no) {
		
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<List<ReplyVO>>(replyService.list(article_no), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 내용 수정
	@RequestMapping(value="/{reply_no}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("reply_no") Integer reply_no
																		, @RequestBody ReplyVO replyVO) {
		ResponseEntity<String> entity = null;
		try {
			replyVO.setReply_no(reply_no);
			replyService.update(replyVO);
			entity = new ResponseEntity<String>("modSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 삭제
	@DeleteMapping("/{reply_no}")
	public ResponseEntity<String> delete(@PathVariable("reply_no") Integer reply_no) {
		
		ResponseEntity<String> entity = null;
		try {
			replyService.delete(reply_no);
			entity = new ResponseEntity<String>("delSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 목록 페이징
	@GetMapping("/{article_no}/{page}")
	public ResponseEntity<Map<String, Object>> listPaging(@PathVariable("article_no") Integer article_no
																								, @PathVariable("page") Integer page) throws Exception {
		
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			List<ReplyVO> replies = replyService.getRepliesPaging(article_no, criteria);
			int repliesCount = replyService.countReplies(article_no);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(repliesCount);
			
			Map<String, Object> map = new HashMap<>();
			map.put("replies", replies);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
		}
		return entity;
	}
	
}

package com.sjseop.boardproject.service;

import java.util.List;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.commons.paging.SearchCriteria;
import com.sjseop.boardproject.domain.ArticleVO;

public interface ArticleService {
	
	void create(ArticleVO articleVO) throws Exception;
	
	ArticleVO read(Integer article_no) throws Exception;
	
	void update(ArticleVO articleVO) throws Exception;
	
	void delete(Integer article_no) throws Exception;
	
	List<ArticleVO> listAll() throws Exception;
	
	List<ArticleVO> listCriteria(Criteria criteria) throws Exception;
	
	List<ArticleVO> listSearch(SearchCriteria searchCriteria) throws Exception;
	
	int countArticles(Criteria criteria) throws Exception;
	
	int countSearchedArticles(SearchCriteria searchCriteria) throws Exception;
	
	List<ArticleVO> userBoardList(String usreId) throws Exception;
}

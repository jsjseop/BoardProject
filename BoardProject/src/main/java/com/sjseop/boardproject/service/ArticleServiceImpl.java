package com.sjseop.boardproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.commons.paging.SearchCriteria;
import com.sjseop.boardproject.domain.ArticleVO;
import com.sjseop.boardproject.persistence.ArticleDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	private final ArticleDAO articleDAO;
	
	@Inject
	public ArticleServiceImpl(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public void create(ArticleVO articleVO) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.create(articleVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ArticleVO read(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.updateViewCnt(article_no); //조회수 1증가
		return articleDAO.read(article_no);
	}

	@Override
	public void update(ArticleVO articleVO) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.update(articleVO);
	}

	@Override
	public void delete(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.delete(article_no);
	}

	@Override
	public List<ArticleVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.listAll();
	}

	@Override
	public List<ArticleVO> listCriteria(Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.listCriteria(criteria);
	}

	@Override
	public List<ArticleVO> listSearch(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.listSearch(searchCriteria);
	}

	@Override
	public int countArticles(Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.countArticles(criteria);
	}

	@Override
	public int countSearchedArticles(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.countSearchedArticles(searchCriteria);
	}

}

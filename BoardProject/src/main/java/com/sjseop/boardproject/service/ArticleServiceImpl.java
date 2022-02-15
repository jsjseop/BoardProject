package com.sjseop.boardproject.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.commons.paging.SearchCriteria;
import com.sjseop.boardproject.domain.ArticleVO;
import com.sjseop.boardproject.persistence.ArticleDAO;
import com.sjseop.boardproject.persistence.ArticleFileDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	private final ArticleDAO articleDAO;
	
	@Autowired
	private ArticleFileDAO articleFileDAO;
	
	@Inject
	public ArticleServiceImpl(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Transactional
	@Override
	public void create(ArticleVO articleVO) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.create(articleVO);
		
		String[] files = articleVO.getFiles();
		
		if(files == null) {
			return;
		}
		//첨부파일 저장
		for(String fileName : files) {
			articleFileDAO.addFile(fileName);
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ArticleVO read(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.updateViewCnt(article_no); //조회수 1증가
		return articleDAO.read(article_no);
	}

	@Transactional
	@Override
	public void update(ArticleVO articleVO) throws Exception {
		// TODO Auto-generated method stub
		Integer article_no = articleVO.getArticle_no();
		String[] files = articleVO.getFiles();
		
		logger.info(articleVO.toString());
		
		articleDAO.update(articleVO);
		articleFileDAO.deleteFiles(article_no);
		
		if(files == null) {
			return;
		}
		for(String fileName : files) {
			articleFileDAO.replaceFile(fileName, article_no);
		}
		articleFileDAO.updateFileCnt(article_no);
	}

	@Transactional
	@Override
	public void delete(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		articleFileDAO.deleteFiles(article_no);
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

package com.sjseop.boardproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjseop.boardproject.persistence.ArticleFileDAO;

@Service
public class ArticleFileServiceImpl implements ArticleFileService {

	@Autowired
	private ArticleFileDAO articleFileDAOImpl;
	
	@Override
	public List<String> getArticleFiles(Integer article_no) throws Exception {
		// TODO Auto-generated method stub
		return articleFileDAOImpl.getArticleFiles(article_no);
	}

	@Override
	public void deleteFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		articleFileDAOImpl.deleteFile(fileName);
	}

}

package com.sjseop.boardproject.service;

import java.util.List;

public interface ArticleFileService {

	//첨부파일 목록
	List<String> getArticleFiles(Integer article_no) throws Exception;
	//첨부파일 삭제
	void deleteFile(String fileName) throws Exception;
}

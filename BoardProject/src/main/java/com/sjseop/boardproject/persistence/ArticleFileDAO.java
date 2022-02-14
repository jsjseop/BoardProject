package com.sjseop.boardproject.persistence;

import java.util.List;

public interface ArticleFileDAO {

	//파일 추가
	void addFile(String fullName) throws Exception;
	//첨부파일 목록
	List<String> getArticleFiles(Integer article_no) throws Exception;
	//게시글의 첨부파일 전체 삭제
	void deleteFiles(Integer article_no) throws Exception;
	//첨부파일 삭제
	void deleteFile(String fileName) throws Exception;
	//첨부파일 수정
	void replaceFile(String fileName, Integer article_no) throws Exception;
	//파일개수 갱신
	void updateFileCnt(Integer article_no) throws Exception;
}

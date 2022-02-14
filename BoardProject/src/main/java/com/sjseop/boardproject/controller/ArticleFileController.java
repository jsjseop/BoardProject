package com.sjseop.boardproject.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sjseop.boardproject.commons.util.UploadFileUtils;
import com.sjseop.boardproject.service.ArticleFileService;

@RestController
@RequestMapping("/article/file")
public class ArticleFileController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleFileController.class);
	
	@Autowired
	private ArticleFileService articleFileService;
	
	//첨부파일 업로드
	@RequestMapping(value="/upload", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
		
		logger.info("============FILE UPLOAD============");
		logger.info("ORIGINAL FILENAME : " + file.getOriginalFilename());
		logger.info("FILE SIZE : " + file.getSize());
		logger.info("CONTENT TYPE : " + file.getContentType());
		logger.info("=================================");
		
		ResponseEntity<String> entity = null;
		try {
			String savedFilePath = UploadFileUtils.uploadFile(file, request);
			entity = new ResponseEntity<>(savedFilePath, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//첨부파일 출력
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ResponseEntity<byte[]> diplayFile(String fileName, HttpServletRequest request) throws Exception {
		
		logger.info("file name : " + fileName);
		InputStream inputStream = null;
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
		String rootPath = UploadFileUtils.getRootPath(fileName, request);
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			inputStream = new FileInputStream(rootPath + fileName);
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {
			inputStream.close();
		}
		return entity;
	}
	
	//첨부파일 목록
	@RequestMapping(value="/list/{article_no}", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getFiles(@PathVariable("article_no") Integer article_no) {
		logger.info("getFiles..." + article_no);
		ResponseEntity<List<String>> entity = null;
		try {
			List<String> fileList = articleFileService.getArticleFiles(article_no);
			logger.info("List : " + fileList);
			entity = new ResponseEntity<List<String>>(fileList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//첨부파일 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request) throws Exception {
		
		ResponseEntity<String> entity = null;
		try {
			UploadFileUtils.deleteFile(fileName, request);
			entity = new ResponseEntity<String>("DELETED", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//게시글 수정 : 첨부파일 삭제
	@RequestMapping(value="/delete/{article_no}", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@PathVariable("article_no") Integer article_no,
																			 String fileName, HttpServletRequest request) throws Exception {
		ResponseEntity<String> entity = null;
		try {
			UploadFileUtils.deleteFile(fileName, request);
			articleFileService.deleteFile(fileName);
			entity = new ResponseEntity<String>("DELETED", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//게시글의 첨부파일 전체 삭제
	@RequestMapping(value="/deleteAll", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files, HttpServletRequest request) throws Exception {
		
		if(files == null || files.length == 0) {
			return new ResponseEntity<String>("DELETED", HttpStatus.OK);
		}
		
		ResponseEntity<String> entity = null;
		try {
			for(String fileName : files) {
				UploadFileUtils.deleteFile(fileName, request);
			}
			entity = new ResponseEntity<String>("DELETED", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}

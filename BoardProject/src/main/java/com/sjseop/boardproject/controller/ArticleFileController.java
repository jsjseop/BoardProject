package com.sjseop.boardproject.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sjseop.boardproject.commons.util.UploadFileUtils;

@RestController
@RequestMapping("/article/file")
public class ArticleFileController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleFileController.class);
	
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
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
		String rootPath = UploadFileUtils.getRootPath(fileName, request);
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			InputStream inputStream = new FileInputStream(rootPath + fileName);
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
	
}

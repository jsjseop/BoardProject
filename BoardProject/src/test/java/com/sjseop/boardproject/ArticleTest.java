package com.sjseop.boardproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sjseop.boardproject.domain.ArticleVO;
import com.sjseop.boardproject.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/mybatis-config.xml",
															"file:src/main/webapp/WEB-INF/spring/root-context.xml",
															"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class ArticleTest {
	
	@Inject
	private ArticleService articleService;
	
	@Test
	public void testCreate() throws Exception {
		
		for(int i=1; i<200; i++) {
			ArticleVO articleVO = new ArticleVO();
			articleVO.setTitle(i + "번째 글 제목");
			articleVO.setContent(i + "번째 글 내용");
			articleVO.setWriter("user0" + (i%10));
			
			articleService.create(articleVO);
		}
	}
}

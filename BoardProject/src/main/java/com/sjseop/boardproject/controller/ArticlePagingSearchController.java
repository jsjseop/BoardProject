package com.sjseop.boardproject.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjseop.boardproject.commons.paging.PageMaker;
import com.sjseop.boardproject.commons.paging.SearchCriteria;
import com.sjseop.boardproject.domain.ArticleVO;
import com.sjseop.boardproject.service.ArticleService;

@Controller
@RequestMapping("/article/paging/search")
public class ArticlePagingSearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticlePagingSearchController.class);
	
	@Inject
	private ArticleService articleService;
	
	//글 조회 처리
	@GetMapping("/read")
	public String read(@RequestParam("article_no") int article_no,
										@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
										Model model) throws Exception {
		
		logger.info("read ...");
		model.addAttribute("article", articleService.read(article_no));
		
		return "article/search/read";
	}
	
	//글 수정 페이지 이동
	@GetMapping("/modify")
	public String modify(@RequestParam("article_no") int article_no,
											@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
											Model model) throws Exception {
		
		logger.info("modify GET...");
		model.addAttribute("article", articleService.read(article_no));
		
		return "article/search/modify";
	}
	
	//글 수정 처리
	@PostMapping("/modify")
	public String modify(ArticleVO articleVO, SearchCriteria searchCriteria,
										RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("modify POST...");
		articleService.update(articleVO);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("perPageNum", searchCriteria.getPerPageNum());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/paging/search/list";
	}
	
	//글 삭제 처리
	@PostMapping("/remove")
	public String remove(@RequestParam("article_no") int article_no,
											SearchCriteria searchCriteria,
											RedirectAttributes redirectAttributes,
											Model model) throws Exception {
		
		logger.info("remove ...");
		articleService.delete(article_no);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("perPageNum", searchCriteria.getPerPageNum());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/paging/search/list";
	}
	
	@GetMapping("/list")
	public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
									Model model) throws Exception {
		
		logger.info("article/paging/search/list...");
		logger.info(searchCriteria.toString());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(articleService.countSearchedArticles(searchCriteria));
		logger.info(pageMaker.toString());
		
		model.addAttribute("articles", articleService.listSearch(searchCriteria));
		model.addAttribute("pageMaker", pageMaker);
		
		return "article/search/list";
		
	}
}

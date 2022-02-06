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

import com.sjseop.boardproject.commons.paging.Criteria;
import com.sjseop.boardproject.commons.paging.PageMaker;
import com.sjseop.boardproject.domain.ArticleVO;
import com.sjseop.boardproject.service.ArticleService;

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {

	private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);
	
	private final ArticleService articleService;
	
	@Inject
	public ArticlePagingController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	//글 작성 페이지 이동
	@GetMapping("/write")
	public String write() {
		
		logger.info("write GET...");
		
		return "article/paging/write";
	}
	
	//글 작성 처리
	@PostMapping("/write")
	public String write(ArticleVO articleVO,
									RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("write POST...");
		logger.info(articleVO.toString());
		
		articleService.create(articleVO);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:/article/paging/list";
	}
	
	//글 조회 처리
	@GetMapping("/read")
	public String read(@RequestParam("article_no") int article_no,
										@ModelAttribute("criteria") Criteria criteria,
										Model model) throws Exception {
		
		logger.info("read ...");
		model.addAttribute("article", articleService.read(article_no));
		
		return "article/paging/read";
	}
	
	//글 수정 페이지 이동
	@GetMapping("/modify")
	public String modify(@RequestParam("article_no") int article_no,
											@ModelAttribute("criteria") Criteria criteria,
											Model model) throws Exception {
		
		logger.info("modify GET...");
		model.addAttribute("article", articleService.read(article_no));
		
		return "article/paging/modify";
	}
	
	//글 수정 처리
	@PostMapping("/modify")
	public String modify(ArticleVO articleVO, Criteria criteria,
										RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("modify POST...");
		articleService.update(articleVO);
		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/paging/list";
	}
	
	//글 삭제 처리
	@PostMapping("/remove")
	public String remove(@RequestParam("article_no") int article_no,
											Criteria criteria,
											RedirectAttributes redirectAttributes,
											Model model) throws Exception {
		
		logger.info("remove ...");
		articleService.delete(article_no);
		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/paging/list";
	}
	
	//글 목록 페이징 처리
	@GetMapping("/list")
	public String list(Model model, Criteria criteria) throws Exception {
		
		logger.info("listPaging...");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(articleService.countArticles(criteria));
		logger.info(pageMaker.toString());
		
		model.addAttribute("articles", articleService.listCriteria(criteria));
		model.addAttribute("pageMaker", pageMaker);
		
		return "article/paging/list";
	}
	
}

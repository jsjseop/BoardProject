package com.sjseop.boardproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjseop.boardproject.commons.util.UploadFileUtils;
import com.sjseop.boardproject.domain.ArticleVO;
import com.sjseop.boardproject.domain.ReplyVO;
import com.sjseop.boardproject.domain.UserVO;
import com.sjseop.boardproject.service.ArticleService;
import com.sjseop.boardproject.service.ReplyService;
import com.sjseop.boardproject.service.UserService;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ReplyService replyService;
	
	//회원 프로필 이미지 수정
	@PostMapping("/modify/image")
	public String userImgModify(String userId, MultipartFile file, HttpSession session,
													HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		
		if(file == null) {
			redirectAttributes.addFlashAttribute("msg", "FAIL");
			return "redirect:/user/profile";
		}
		String savedFilePath = UploadFileUtils.uploadFile(file, request);
		String userImg = savedFilePath.substring(0, 12) + savedFilePath.substring(14);
		userService.modifyImg(userId, userImg);
		UserVO userVO = (UserVO)session.getAttribute("login");
		userVO.setUserImg(userImg);
		session.setAttribute("login", userVO);
		redirectAttributes.addFlashAttribute("msg", "SUCCCESS");
		return "redirect:user/profile";		
	}
	
	// 회원정보 수정처리
    @PostMapping("/modify/info")
    public String userInfoModify(UserVO userVO, HttpSession session,
                                 RedirectAttributes redirectAttributes) throws Exception {
    	logger.info(userVO.toString());
        UserVO oldUserInfo = userService.getUser(userVO.getUserId());
        logger.info(oldUserInfo.toString());
        logger.info("1");
        if (!BCrypt.checkpw(userVO.getUserPw(), oldUserInfo.getUserPw())) {
            redirectAttributes.addFlashAttribute("msg", "FAILURE");
            logger.info("2");
            return "redirect:/user/profile";
        }
        logger.info("3");
        userService.modifyUser(userVO);
        userVO.setUserJoinDate(oldUserInfo.getUserJoinDate());
        userVO.setUserLoginDate(oldUserInfo.getUserLoginDate());
        userVO.setUserImg(oldUserInfo.getUserImg());
        session.setAttribute("login", userVO);
        redirectAttributes.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/user/profile";
    }
    
    //회원 비밀번호 수정
    public String userPwModify(@RequestParam("userId") String userId, @RequestParam("oldPw") String oldPw,
    												@RequestParam("newPw") String newPw, HttpSession session,
    												RedirectAttributes redirectAttributes) throws Exception {
    	
    	UserVO userInfo = userService.getUser(userId);
    	if(!BCrypt.checkpw(oldPw, userInfo.getUserPw())) {
    		redirectAttributes.addFlashAttribute("msg", "FAILURE");
    		return "redirect:/user/profile";
    	}
    	String newHashPw = BCrypt.hashpw(newPw, BCrypt.gensalt());
    	userInfo.setUserPw(newHashPw);
    	userService.modifyPw(userInfo);
    	session.setAttribute("login", userInfo);
    	redirectAttributes.addFlashAttribute("msg", "SUCCESS");
    	return "redirect:/user/profile";
    }
    
    // 회원 정보 페이지
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) throws Exception {

        Object userObj = session.getAttribute("login");
        UserVO userVO = (UserVO) userObj;
        String userId = userVO.getUserId();
        List<ArticleVO> userBoardList = articleService.userBoardList(userId);
        List<ReplyVO> userReplies = replyService.userReplies(userId);

        model.addAttribute("userBoardList", userBoardList);
        model.addAttribute("userReplies", userReplies);

        return "user/profile";
    }
	
}

package com.sds.icto.instakgram.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.instakgram.domain.DBoardVO;
import com.sds.icto.instakgram.domain.GBoardVO;
import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.domain.ReplyVO;
import com.sds.icto.instakgram.repository.DBoardDAO;
import com.sds.icto.instakgram.repository.GBoardDAO;



@Controller
@RequestMapping("/dboard")
public class DBoardController {

	@Autowired
	DBoardDAO dboardDao;

	@RequestMapping({"/index",""})
	public String index(Model model, @RequestParam(value = "pageNum", 
            required = false, 
            defaultValue = "1" )  Long pageNum ) {
		
		if(pageNum.equals(null)){
			pageNum = (long) 1;
		}
		Long start = (pageNum - 1) * 5 + 1;
		Long end = pageNum * 5;

		List<DBoardVO> list = dboardDao.fetchList(start, end);
		model.addAttribute("list", list);
		
		
		List<DBoardVO> listcnt = dboardDao.cntBasicList();
		
		model.addAttribute("count", listcnt.size());
		
		System.out.println(listcnt.size());
		
		List<ReplyVO> reply = dboardDao.fetchReply();
		
		model.addAttribute("reply", reply);
		
		return "dboard/list";
	}

	
	@RequestMapping("/like")
	public String like(@RequestParam Long no,
			@RequestParam Long like_cnt) {

	
		dboardDao.like(no, like_cnt);


		return "redirect:/dboard/index";

	}

	
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String insert(HttpSession session, @RequestParam String reply, @RequestParam Long no) {
		
		ReplyVO vo = new ReplyVO();
		vo.setContent(reply);
		vo.setDaily_no(no);
		
		MemberVO vo2 = (MemberVO) session.getAttribute("authMember");
		vo.setMember_no(vo2.getNo());
		vo.setMember_name(vo2.getName());
		
		System.out.println(reply);
		System.out.println(no);
		System.out.println(vo2.getNo());
		System.out.println(vo2.getName());
		
		/*	vo.setPassword(password);
		vo.setMessage(message);
		*/
		dboardDao.reply(vo);

		return "redirect:/dboard/index";
	}
	
	
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(HttpSession session, @RequestParam String content) {
		
		DBoardVO vo = new DBoardVO();
		vo.setContent(content);
		
		MemberVO vo2 = (MemberVO) session.getAttribute("authMember");
		vo.setMember_no(vo2.getNo());
		vo.setMember_name(vo2.getName());
		
		/*	vo.setPassword(password);
		vo.setMessage(message);
		*/
		dboardDao.insert(vo);

		return "redirect:/dboard/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	// 링크??겟방??
	public String delete(@RequestParam  Long no) {
	
		return "dboard/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam Long no, @RequestParam String password) {
		
		dboardDao.delete(no, password);
				
		return "redirect:/dboard/index";

	}
	
	@RequestMapping("/searchDBoard")
	public String searchDBoard(@RequestParam Long member_no,
			Model model, 
			@RequestParam(value = "pageNum", required = false, defaultValue = "1" )
			Long pageNum 
			) {
		
		if(pageNum.equals(null)){
			pageNum = (long) 1;
		}
		Long start = (pageNum - 1) * 5 + 1;
		Long end = pageNum * 5;

		List<DBoardVO> list = dboardDao.fetchList(start, end, member_no);
		model.addAttribute("list", list);
		
		List<DBoardVO> listcnt = dboardDao.cntList();
		model.addAttribute("count", listcnt.size());
		
		List<ReplyVO> reply = dboardDao.fetchReply();
		
		model.addAttribute("reply", reply);
		
		return "dboard/list";
		
	}

}

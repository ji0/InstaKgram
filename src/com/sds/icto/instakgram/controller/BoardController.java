package com.sds.icto.instakgram.controller;

import java.sql.SQLException;
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

import com.sds.icto.mysite.domain.BoardVO;
import com.sds.icto.mysite.domain.MemberVO;
import com.sds.icto.mysite.repository.BoardDAO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardDAO boardDao;

	@RequestMapping("/index")
	public String index(Model model) {

		List<BoardVO> list = null;
		try {
			list = boardDao.fetchList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "board/list";
	}

	@RequestMapping("/search")
	public String search(Model model, @RequestParam String content) {

		List<BoardVO> list = null;
		try {
			list = boardDao.search(content);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "board/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	// 링크??겟방??
	public String write() {

		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@RequestParam String title,
			@RequestParam String content, HttpSession session) {

		// boardDao.delete(no, password);
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);

		MemberVO vo2 = (MemberVO) session.getAttribute("authMember");

		vo.setMember_no(vo2.getNo());
		vo.setMember_name(vo2.getName());

		boardDao.insert(vo);

		return "redirect:/board/index";

	}

	@RequestMapping("/view")
	public String view(Model model, @RequestParam Long no,
			@RequestParam Long view_cnt) {

		BoardVO list = null;
		list = boardDao.view(no, view_cnt);

		model.addAttribute("list", list);

		return "board/view";

	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	// 링크??겟방??
	public String modify(Model model, @RequestParam Long no) {
		BoardVO list = null;
		list = boardDao.modify(no);

		model.addAttribute("list", list);

		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@RequestParam Long no, @RequestParam String title,
			@RequestParam String content) {

		BoardVO vo = new BoardVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		boardDao.update(vo);

		return "redirect:/board/index";

	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam Long no) {
		
		boardDao.delete(no);
				
		return "redirect:/board/index";

	}
	
	
	

	/*
	 * 
	 * @RequestMapping(value = "/insert", method = RequestMethod.POST) public
	 * String insert(
	 * 
	 * @RequestParam String name,
	 * 
	 * @RequestParam String password,
	 * 
	 * @RequestParam String message) {
	 * 
	 * BoardVO vo = new BoardVO(); vo.setName(name); vo.setPassword(password);
	 * vo.setMessage(message);
	 * 
	 * boardDao.insert(vo);
	 * 
	 * return "redirect:/board/index"; }
	 * 
	 * @RequestMapping(value = "/delete", method = RequestMethod.GET) // 링크??겟방??
	 * public String delete(@RequestParam Long no) {
	 * 
	 * return "guestbook/deleteform"; }
	 * 
	 * @RequestMapping(value = "/delete", method = RequestMethod.POST) public
	 * String delete(@RequestParam Long no, @RequestParam String password) {
	 * 
	 * //boardDao.delete(no, password);
	 * 
	 * return "redirect:/guestbook/index";
	 * 
	 * }
	 */

}

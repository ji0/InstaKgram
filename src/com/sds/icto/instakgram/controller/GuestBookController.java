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

import com.sds.icto.instakgram.domain.GuestBookVO;
import com.sds.icto.instakgram.repository.GuestBookDAO;



@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	GuestBookDAO guestBookDao;

	@RequestMapping("/index")
	public String index(Model model) {

		List<GuestBookVO> list = guestBookDao.fetchList();
		model.addAttribute("list", list);

		return "guestbook/list";
	}


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(
			@RequestParam String name,
			@RequestParam String password, 
			@RequestParam String message) {
		
		GuestBookVO vo = new GuestBookVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		guestBookDao.insert(vo);

		return "redirect:/guestbook/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	// 링크??겟방??
	public String delete(@RequestParam  Long no) {
	
		return "guestbook/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam Long no, @RequestParam String password) {
		
		guestBookDao.delete(no, password);
				
		return "redirect:/guestbook/index";

	}


}

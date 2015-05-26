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
import com.sds.icto.instakgram.repository.DBoardDAO;
import com.sds.icto.instakgram.repository.GBoardDAO;



@Controller
@RequestMapping("/dboard")
public class DBoardController {

	@Autowired
	DBoardDAO dboardDao;

	@RequestMapping("/index")
	public String index(Model model) {

		List<DBoardVO> list = dboardDao.fetchList();
		model.addAttribute("list", list);

		return "dboard/list";
	}


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(
			@RequestParam String name,
			@RequestParam String password, 
			@RequestParam String message) {
		
		DBoardVO vo = new DBoardVO();
	/*	vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		*/
		dboardDao.insert(vo);

		return "redirect:/gboard/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	// 링크??겟방??
	public String delete(@RequestParam  Long no) {
	
		return "gboard/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam Long no, @RequestParam String password) {
		
		dboardDao.delete(no, password);
				
		return "redirect:/gboard/index";

	}


}

package com.sds.icto.instakgram.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sds.icto.mysite.domain.MemberVO;
import com.sds.icto.mysite.repository.BoardDAO;
import com.sds.icto.mysite.repository.MemberDAO;
import com.sds.icto.mysite.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberDAO memberDao;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	// 링크??겟방??
	public String joinForm() {
		return "member/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberVO vo) {
		memberService.joinUser(vo);
		return "redirect:/member/login";
	}

	@RequestMapping(value = "/uinfo", method = RequestMethod.GET)
	// 링크??겟방??
	public String uinfoForm() {
		return "member/uinfoform";
	}

	@RequestMapping(value = "/uinfo", method = RequestMethod.POST)
	public String uinfo(@RequestParam String name, @RequestParam String email,
			@RequestParam String password, @RequestParam String gender, @RequestParam Long no) {

		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);

	
		memberDao.update(vo);

		return "redirect:/index";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "member/loginform";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute MemberVO vo, HttpSession session) {

		MemberVO memberVO = memberService.authUser(vo);

		if (memberVO == null) {
			return "redirect:/member/login?result=fail";
		}

		session.setAttribute("authMember", memberVO);
		return "redirect:/index";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authMember");
		session.invalidate();
		return "redirect:/index";
	}
}

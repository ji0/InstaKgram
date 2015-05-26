package com.sds.icto.instakgram.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.repository.MemberDAO;
import com.sds.icto.instakgram.service.MemberService;

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
	
	@RequestMapping("/email_check") 
	@ResponseBody
	public Object checkEmail(String email) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("email:"+email);
		
		List<MemberVO> list = memberDao.checkEmail();
		System.out.println(list.size());
		
		for(MemberVO vo:list){
			//중복
			if(vo.getEmail().equals(email)){
				map.put("result", true);
				map.put("data", "사용할 수 없습니다.");
				break;
			}
			//사용가능
			else{
				map.put("result", false);
				map.put("data", "사용할 수 있습니다.");
				continue;
			}
		}
		return map;
	}
}

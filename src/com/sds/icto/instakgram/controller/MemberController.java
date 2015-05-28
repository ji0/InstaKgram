package com.sds.icto.instakgram.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.repository.MemberDAO;
import com.sds.icto.instakgram.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;


	@RequestMapping(value = "/join", method = RequestMethod.GET)
	// 링크??겟방??
	public String joinForm() {
		return "member/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberVO vo, @RequestParam Long deptNo,
			@RequestParam("file") MultipartFile file) {

		String fileOriginalName = file.getOriginalFilename();
		String extName = fileOriginalName.substring(
				fileOriginalName.lastIndexOf(".") + 1,
				fileOriginalName.length());
		String fileName = file.getName();
		Long size = file.getSize();

		String saveFileName = "";
		Calendar calendar = Calendar.getInstance();

		saveFileName += calendar.get(Calendar.YEAR);
		saveFileName += calendar.get(Calendar.MONTH);
		saveFileName += calendar.get(Calendar.DATE);
		saveFileName += calendar.get(Calendar.HOUR);
		saveFileName += calendar.get(Calendar.MINUTE);
		saveFileName += calendar.get(Calendar.SECOND);
		saveFileName += calendar.get(Calendar.MILLISECOND);
		saveFileName += ("." + extName);

		writeFile(file, "c:\\uploads", saveFileName);

		// vo에 사진name등록
		vo.setPic_ref(saveFileName);
		// 회원등록
		memberService.joinUser(vo);

		return "redirect:/member/login";
	}

	private void writeFile(MultipartFile file, String path, String fileName) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path + "\\" + fileName);
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@RequestMapping(value = "/uinfo", method = RequestMethod.GET)
	// 링크??겟방??
	public String uinfoForm() {
		return "member/uinfoform";
	}

	@RequestMapping(value = "/uinfo", method = RequestMethod.POST)
	public String uinfo(@RequestParam String name, @RequestParam String email,
			@RequestParam String password, @RequestParam String gender,
			@RequestParam Long no, @RequestParam Long deptNo,
			@RequestParam("file") MultipartFile file, HttpSession session) {

		String fileOriginalName = file.getOriginalFilename();
		String extName = fileOriginalName.substring(
				fileOriginalName.lastIndexOf(".") + 1,
				fileOriginalName.length());
		String fileName = file.getName();
		Long size = file.getSize();

		String saveFileName = "";
		Calendar calendar = Calendar.getInstance();

		saveFileName += calendar.get(Calendar.YEAR);
		saveFileName += calendar.get(Calendar.MONTH);
		saveFileName += calendar.get(Calendar.DATE);
		saveFileName += calendar.get(Calendar.HOUR);
		saveFileName += calendar.get(Calendar.MINUTE);
		saveFileName += calendar.get(Calendar.SECOND);
		saveFileName += calendar.get(Calendar.MILLISECOND);
		saveFileName += ("." + extName);

		writeFile(file, "c:\\uploads", saveFileName);

		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);
		vo.setPic_ref(saveFileName);

		memberService.uinfoUser(vo);
		session.setAttribute("authMember", memberService.authUser(vo));
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

		List<MemberVO> list = memberService.mailList();
		map.put("result", false);
		map.put("data", "사용할 수 있습니다.");
		
		for (MemberVO vo : list) {
			// 중복
			System.out.println("asdf:" + vo.getEmail());
			if (email.equals(vo.getEmail())) {
				map.put("result", true);
				map.put("data", "사용할 수 없습니다.");
				break;
			}
			// 사용가능
			else {
				map.put("result", false);
				map.put("data", "사용할 수 있습니다.");
				continue;
			}
		}
		return map;
	}
}

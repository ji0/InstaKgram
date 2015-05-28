package com.sds.icto.instakgram.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sds.icto.instakgram.domain.DBoardVO;
import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.domain.ReplyVO;
import com.sds.icto.instakgram.repository.DBoardDAO;
import com.sds.icto.instakgram.service.DBoardService;
import com.sds.icto.instakgram.service.GBoardService;

@Controller
@RequestMapping("/dboard")
public class DBoardController {

	@Autowired
	DBoardService DboardService;

	@RequestMapping({ "/index", "" })
	public String index(
			Model model,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Long pageNum) {

		if (pageNum.equals(null)) {
			pageNum = (long) 1;
		}
		Long start = (pageNum - 1) * 5 + 1;
		Long end = pageNum * 5;

		List<DBoardVO> list = DboardService.DBList(start, end);

		model.addAttribute("list", list);

		List<DBoardVO> listcnt = DboardService.DBlistcnt();

		model.addAttribute("count", listcnt.size());

		List<ReplyVO> reply = DboardService.ReplyList();

		model.addAttribute("reply", reply);

		return "dboard/list";
	}

	@RequestMapping("/like")
	public String like(@RequestParam Long no, @RequestParam Long like_cnt) {

		DboardService.DBlike(no, like_cnt);

		return "redirect:/dboard/index";

	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String insert(HttpSession session, @RequestParam String reply,
			@RequestParam Long no) {

		ReplyVO vo = new ReplyVO();
		vo.setContent(reply);
		vo.setDaily_no(no);

		MemberVO vo2 = (MemberVO) session.getAttribute("authMember");
		vo.setMember_no(vo2.getNo());
		vo.setMember_name(vo2.getName());

		DboardService.DBreply(vo);

		return "redirect:/dboard/index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(HttpSession session, @RequestParam String content,
			@RequestParam Long deptNo, @RequestParam("file") MultipartFile file) {

		String fileOriginalName = file.getOriginalFilename();
		String extName = fileOriginalName.substring(
				fileOriginalName.lastIndexOf(".") + 1,
				fileOriginalName.length());

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

		DBoardVO vo = new DBoardVO();
		vo.setContent(content);

		MemberVO vo2 = (MemberVO) session.getAttribute("authMember");
		vo.setMember_no(vo2.getNo());
		vo.setMember_name((String) vo2.getName());
		vo.setPic_ref(saveFileName);

		DboardService.DBinsert(vo);

		return "redirect:/dboard/index";
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

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	// 링크??겟방??
	public String delete(@RequestParam Long no) {

		return "dboard/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam Long no, @RequestParam String password) {

		DboardService.DBdelete(no, password);
		return "redirect:/dboard/index";

	}


	@RequestMapping(value = "/replydelete")
	public String replydelete(@RequestParam Long no) {

		DboardService.DBreplyDelete(no);
		return "redirect:/dboard/index";

	}

	@RequestMapping("/like_cnt")
	@ResponseBody
	public Object checkEmail(String likeCnt, String no) {

		Map<String, Object> map = new HashMap<String, Object>();

		int like = Integer.parseInt(likeCnt) + 1;
		likeCnt = String.valueOf(like);

		map.put("v", likeCnt);
		map.put("n", no);

		// 다오 생성하기
		DboardService.DBincLike(map);
	
		// List<MemberVO> list = dboardDao.incLike();
		// System.out.println(list.size());

		// 중복
		// System.out.println("asdf:"+vo.getEmail());

		map.put("result", true);
		map.put("data", likeCnt);

		// System.out.println(map);

		return map;
	}

}

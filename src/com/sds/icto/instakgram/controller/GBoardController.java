package com.sds.icto.instakgram.controller;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sds.icto.instakgram.domain.GBoardVO;
import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.repository.GBoardDAO;

@Controller
@RequestMapping("/gboard")
public class GBoardController {

	@Autowired
	GBoardDAO gboardDao;

	@RequestMapping("/index")
	public String index(Model model) {

		List<GBoardVO> list = null;
		try {
			list = gboardDao.fetchList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "gboard/list";
	}

	@RequestMapping("/search")
	public String search(Model model, @RequestParam String search_what, @RequestParam String content) {

		List<GBoardVO> list = null;
		try {
			list = gboardDao.search(search_what, content);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "gboard/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	// 링크??겟방??
	public String write() {

		return "gboard/write";
	}


	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@RequestParam String title,
			@RequestParam String content, HttpSession session,
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

		GBoardVO vo = new GBoardVO();
		vo.setTitle(title);
		vo.setContent(content);

		MemberVO vo2 = (MemberVO) session.getAttribute("authMember");

		vo.setMember_no(vo2.getNo());
		vo.setMember_name(vo2.getName());
		vo.setPic_ref(saveFileName);

		gboardDao.insert(vo);

		return "redirect:/gboard/index";

	}
	
	private void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream( path + "\\" + fileName );
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
	
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam Long no,
			@RequestParam Long view_cnt) {

		GBoardVO list = null;
		list = gboardDao.view(no, view_cnt);

		model.addAttribute("list", list);

		return "gboard/view";

	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	// 링크??겟방??
	public String modify(Model model, @RequestParam Long no) {
		GBoardVO list = null;
		list = gboardDao.modify(no);

		model.addAttribute("list", list);

		return "gboard/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@RequestParam Long no, @RequestParam String title,
			@RequestParam String content) {

		GBoardVO vo = new GBoardVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		gboardDao.update(vo);

		return "redirect:/gboard/index";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam Long no) {

		gboardDao.delete(no);

		return "redirect:/gboard/index";

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
	 * @RequestMapping(value = "/delete", method = RequestMethod.GET) //
	 * 링크??겟방?? public String delete(@RequestParam Long no) {
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

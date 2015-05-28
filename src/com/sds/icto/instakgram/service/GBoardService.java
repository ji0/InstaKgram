package com.sds.icto.instakgram.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.instakgram.domain.GBoardVO;
import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.repository.GBoardDAO;

@Service
public class GBoardService {

	@Autowired
	GBoardDAO gboardDao;

	public List<GBoardVO> GBList() {
		List<GBoardVO> list = null;
		try {
			list = gboardDao.fetchList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<GBoardVO> GBSearch(String search_what, String content) {
		List<GBoardVO> list = null;
		try {
			list = gboardDao.search(search_what, content);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void GBinsert(GBoardVO vo) {
		gboardDao.insert(vo);
		
	}
	public void GBupdate(GBoardVO vo) {
		gboardDao.update(vo);
		
	}
	
	public void GBdelete(Long no) {
		gboardDao.delete(no);		
	}
	
	public GBoardVO GBView(Long no, Long view_cnt){
		
	GBoardVO list = null;
	list = gboardDao.view(no, view_cnt);
	
	return list;
	}
	
	
	public GBoardVO GBmodify(Long no){
		
	GBoardVO list =  gboardDao.modify(no);
	
	return list;
	}


}

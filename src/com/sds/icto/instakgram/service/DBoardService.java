package com.sds.icto.instakgram.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.instakgram.domain.DBoardVO;
import com.sds.icto.instakgram.domain.ReplyVO;
import com.sds.icto.instakgram.repository.DBoardDAO;

@Service
public class DBoardService {

	@Autowired
	DBoardDAO dboardDao;

	public List<DBoardVO> DBList(Long start, Long end) {
		List<DBoardVO> list = dboardDao.fetchList(start, end);

		return list;
	}

	public List<DBoardVO> DBlistcnt() {
		List<DBoardVO> listcnt = dboardDao.cntBasicList();

		return listcnt;
	}

	public List<ReplyVO> ReplyList() {
		List<ReplyVO> list = dboardDao.fetchReply();

		return list;
	}

	public void DBlike(Long no, Long like_cnt) {

		dboardDao.like(no, like_cnt);

	}

	public void DBreply(ReplyVO vo) {

		dboardDao.reply(vo);

	}

	public void DBinsert(DBoardVO vo) {

		dboardDao.insert(vo);

	}

	public void DBdelete(Long no, String password) {

		dboardDao.delete(no, password);

	}

	public void DBincLike(Map map) {

		dboardDao.incLike(map);

	}

}

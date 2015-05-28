package com.sds.icto.instakgram.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.instakgram.domain.DBoardVO;
import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.domain.ReplyVO;


@Repository
public class DBoardDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public void insert(DBoardVO vo) {

		sqlMapClientTemplate.insert("dboard.insert", vo);

	}

	public void delete(Long no, String password) {
		
			Map map = new HashMap();
			map.put("no", no);
			map.put("password", password);
			sqlMapClientTemplate.delete("dboard.delete", map);
	}
	
	public void replydelete(Long no) {
		
		
		sqlMapClientTemplate.delete("dboard.replydelete", no);
}

	public void reply(ReplyVO vo) {

		sqlMapClientTemplate.insert("dboard.reply", vo);

	}
	
	

	public void like(Long no, Long like_cnt) {

		like_cnt++;

		Map<String, Long> map = new HashMap<String, Long>();
		map.put("n", no);
		map.put("v", like_cnt);

		sqlMapClientTemplate.update("dboard.pluslike", map);

		}

	
	
	public void delete() {
		sqlMapClientTemplate.delete("dboard.deleteAll");
	}

	public List<DBoardVO> fetchList(Long start, Long end) {
		@SuppressWarnings("unchecked")
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("s", start);
		map.put("e", end);
		
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.list", map);
		
		return list;
	}
	
	//오버라이딩
	public List<DBoardVO> fetchList(Long start, Long end, Long member_no) {
		@SuppressWarnings("unchecked")
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("s", start);
		map.put("e", end);
		map.put("m", member_no);
		
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.choiceList", map);
		
		return list;
	}
	
	
	
	public List<DBoardVO> cntList() {
		@SuppressWarnings("unchecked")
		
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.choiceCntlist");
		
		return list;
	}
	
	
	public List<DBoardVO> cntBasicList() {
		@SuppressWarnings("unchecked")
		
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.cntlist");
		
		return list;
	}
	
	//오버라이딩
	public List<DBoardVO> cntList(Long member_no) {
		@SuppressWarnings("unchecked")
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m", member_no);
		
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.cntlist",map);
		
		return list;
	}
	
	
	public List<ReplyVO> fetchReply() {
		@SuppressWarnings("unchecked")
		List<ReplyVO> list = sqlMapClientTemplate.queryForList("dboard.allreply");
		
		return list;
	}

	public void incLike(Map map){
		
		sqlMapClientTemplate.update("dboard.pluslike", map);
		
	}
}

package com.sds.icto.instakgram.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.instakgram.domain.DBoardVO;
import com.sds.icto.instakgram.domain.GBoardVO;
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
	
	
	public List<DBoardVO> cntList() {
		@SuppressWarnings("unchecked")
		
		
	
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.cntlist");
		
		return list;
	}
	
	
	public List<ReplyVO> fetchReply() {
		@SuppressWarnings("unchecked")
		List<ReplyVO> list = sqlMapClientTemplate.queryForList("dboard.allreply");
		
		return list;
	}


}

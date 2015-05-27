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

import com.sds.icto.instakgram.domain.GBoardVO;
import com.sds.icto.instakgram.domain.DBoardVO;

@Repository
public class GBoardDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public List<GBoardVO> fetchList() throws ClassNotFoundException,
			SQLException {
		@SuppressWarnings("unchecked")
		List<GBoardVO> list = sqlMapClientTemplate.queryForList("gboard.list");

		return list;
	}

	public List<GBoardVO> search(String search_what, String content) throws ClassNotFoundException,
			SQLException {
		@SuppressWarnings("unchecked")
		
		
		List<GBoardVO> list =null;
		
		if("title".equals(search_what)){
			list = sqlMapClientTemplate.queryForList("gboard.t_search",
					content);
		}else if("member_name".equals(search_what)){
			list = sqlMapClientTemplate.queryForList("gboard.m_search",
					content);
		}else if("content".equals(search_what)){
			list = sqlMapClientTemplate.queryForList("gboard.c_search",
					content);
		}else{
			
		}

		return list;
	}

	public void insert(GBoardVO vo) {

		sqlMapClientTemplate.insert("gboard.insert", vo);

	}

	public GBoardVO view(Long no, Long view_cnt) {

		GBoardVO list = null;
		view_cnt++;

		Map<String, Long> map = new HashMap<String, Long>();
		map.put("n", no);
		map.put("v", view_cnt);

		sqlMapClientTemplate.update("gboard.pluscnt", map);

		list = (GBoardVO) sqlMapClientTemplate.queryForObject("gboard.view", no);

		return list;
	}

	public GBoardVO modify(Long no) {

		GBoardVO list = null;

		list = (GBoardVO) sqlMapClientTemplate.queryForObject("gboard.view", no);

		return list;
	}

	public void update(GBoardVO vo) {

		sqlMapClientTemplate.update("gboard.update", vo);

	}
	
	public void delete(Long no) {

		sqlMapClientTemplate.delete("gboard.delete", no);

	}


}

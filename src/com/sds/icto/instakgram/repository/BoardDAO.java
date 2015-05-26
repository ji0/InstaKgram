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

import com.sds.icto.instakgram.domain.BoardVO;
import com.sds.icto.instakgram.domain.GuestBookVO;

@Repository
public class BoardDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public List<BoardVO> fetchList() throws ClassNotFoundException,
			SQLException {
		@SuppressWarnings("unchecked")
		List<BoardVO> list = sqlMapClientTemplate.queryForList("board.list");

		return list;
	}

	public List<BoardVO> search(String content) throws ClassNotFoundException,
			SQLException {
		@SuppressWarnings("unchecked")
		List<BoardVO> list = sqlMapClientTemplate.queryForList("board.search",
				content);
		System.out.println(list.size());

		return list;
	}

	public void insert(BoardVO vo) {

		sqlMapClientTemplate.insert("board.insert", vo);

	}

	public BoardVO view(Long no, Long view_cnt) {

		BoardVO list = null;
		view_cnt++;

		Map<String, Long> map = new HashMap<String, Long>();
		map.put("n", no);
		map.put("v", view_cnt);

		sqlMapClientTemplate.update("board.pluscnt", map);

		list = (BoardVO) sqlMapClientTemplate.queryForObject("board.view", no);

		return list;
	}

	public BoardVO modify(Long no) {

		BoardVO list = null;

		list = (BoardVO) sqlMapClientTemplate.queryForObject("board.view", no);

		return list;
	}

	public void update(BoardVO vo) {

		sqlMapClientTemplate.update("board.update", vo);

	}
	
	public void delete(Long no) {

		sqlMapClientTemplate.delete("board.delete", no);

	}


}

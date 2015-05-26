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

import com.sds.icto.instakgram.domain.GuestBookVO;


@Repository
public class GuestBookDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public void insert(GuestBookVO vo) {

		sqlMapClientTemplate.insert("guestbook.insert", vo);

	}

	public void delete(Long no, String password) {
		
			
			Map map = new HashMap();
			map.put("no", no);
			map.put("password", password);
			
			sqlMapClientTemplate.delete("guestbook.delete", map);
			
		
	
	}

	public void delete() {
		sqlMapClientTemplate.delete("guestbook.deleteAll");
	}

	public List<GuestBookVO> fetchList() {
		@SuppressWarnings("unchecked")
		List<GuestBookVO> list = sqlMapClientTemplate.queryForList("guestbook.list");
		
		return list;
	}

}

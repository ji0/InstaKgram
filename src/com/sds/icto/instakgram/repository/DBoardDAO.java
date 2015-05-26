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

	public void delete() {
		sqlMapClientTemplate.delete("dboard.deleteAll");
	}

	public List<DBoardVO> fetchList() {
		@SuppressWarnings("unchecked")
		List<DBoardVO> list = sqlMapClientTemplate.queryForList("dboard.list");
		
		return list;
	}

}

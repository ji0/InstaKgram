package com.sds.icto.instakgram.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.instakgram.domain.BoardVO;
import com.sds.icto.instakgram.domain.GuestBookVO;
import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.exception.MemberDAOException;

@Repository
public class MemberDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public void insert(MemberVO vo) {

		sqlMapClientTemplate.insert("member.insert", vo);

	}

	public MemberVO getMember(MemberVO vo) {

		@SuppressWarnings("unchecked")
		MemberVO list = (MemberVO) sqlMapClientTemplate.queryForObject("member.getMember", vo);
		return list;
	}
	
	public void update(MemberVO vo) {

			sqlMapClientTemplate.update("member.uinfo", vo);
		
	}

}

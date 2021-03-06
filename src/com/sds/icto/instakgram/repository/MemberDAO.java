package com.sds.icto.instakgram.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.instakgram.domain.MemberVO;


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

	public List<MemberVO> checkEmail(){
		
		@SuppressWarnings("unchecked")
		List<MemberVO> emailList= sqlMapClientTemplate.queryForList("member.checkEmail");
		return emailList;
	}
}

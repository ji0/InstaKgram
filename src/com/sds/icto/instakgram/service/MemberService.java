package com.sds.icto.instakgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.instakgram.domain.MemberVO;
import com.sds.icto.instakgram.repository.MemberDAO;


@Service
public class MemberService {

	@Autowired
	MemberDAO memberDao;
	
	public void joinUser(MemberVO vo) {
		memberDao.insert(vo);
	}
	
	public MemberVO authUser(MemberVO vo) {
		
		 MemberVO vo2 = memberDao.getMember(vo);
		 return vo2;
	}
	
}

package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service	// component-scan으로 자동 빈 생성
public class MemberService {
	
	@Autowired // MemberDAO 객체 자동 주입
	MemberDAO dao; 
	
	public void memberAdd(MemberDTO m) {
		dao.memberAdd(m);	//dto전달 
	}
	
	public MemberDTO login(Map<String,String> map) {
		MemberDTO dto = dao.login(map);
		return dto;
	}

	public MemberDTO myPage(String userid) {
		MemberDTO dto = dao.myPage(userid);
		return dto;
	}
	
	public void memberUpdate(MemberDTO dto) {
		dao.memberUpdate(dto);
	}
}

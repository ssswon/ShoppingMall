package com.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository	// component-scan으로 자동 빈 생성
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate template; // 자동 주입
	
	public void memberAdd(MemberDTO m) {
		int n = template.insert("MemberMapper.memberAdd",m);
		System.out.println("MemberDAO의  memberAdd() insert 커밋 완료"+n);
	}

	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = template.selectOne("MemberMapper.login", map);
		return dto;
	}

	public MemberDTO myPage(String userid) {
		MemberDTO dto = template.selectOne("MemberMapper.mypage",userid);
		return dto;
	}

}
/*
 * DAO자체에서 sqlsession을 자동 주입받아 DB에 접근함
 */
 
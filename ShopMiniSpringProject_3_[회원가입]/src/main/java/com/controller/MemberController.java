package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;	// MemberService 객체 자동 주입
	
	@RequestMapping(value = "/memberAdd")	// memberForm.jsp 에서 데이터 받음
	public String memberAdd(MemberDTO m,Model model) {// form에서 넘어온 데이터를 MemberDTO에 자동저장
		service.memberAdd(m);	// 회원가입 성공
		model.addAttribute("success","회원가입 성공");	// success에 문구 저장 -> main.jsp에서 받음
		return "main";	//main.jsp
	}

}

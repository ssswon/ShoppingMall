package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;

@Controller
public class MemberController {

	@RequestMapping(value="/memberAdd")
	public String memberAdd(MemberDTO m) {//form에서 넘어온 데이터를 MemberDTO에 자동저장
		System.out.println(m);
		return "main";
	}
	
}

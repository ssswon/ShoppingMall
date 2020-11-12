package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/loginCheck/myPage")// loginCheck으로 들어오면 먼저 로그인 검사를 하도록!
	// 인터셉터에서 로그인 인증 후 페이지 이동
	public String myPage(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();
		dto = service.myPage(userid);
		session.setAttribute("login", dto); 	//다시 login에 dto꽂아주기
		System.out.println(dto);
		return "redirect:../myPage";	//servlet-context에 등록
	}
	
	//응답에 대한 한글 처리 필수 !!!!
	@RequestMapping(value="/idDulicateCheck", produces="text/plain;charset=UTF-8")
	@ResponseBody	
	public String idDuplicateCheck(@RequestParam("id") String userid) {
		MemberDTO dto = service.myPage(userid);
		String mesg = "아이디 중복";
		if(dto == null) {
			mesg= "아이디 사용가능";
		}
		return mesg;
	}

}

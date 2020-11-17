package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;

	@RequestMapping("/login")
	public String login(@RequestParam HashMap<String, String> map, HttpSession session, Model model) {
		System.out.println("LoginController :login() :loginForm에서 넘어온 ID,PASSWORD : " + map);
		MemberDTO dto = service.login(map);
		if (dto != null) {
			session.setAttribute("login", dto);
			return "redirect:/goodsList?gCategory=top";//주소로 이동
			//메인페이지에서는 top만보여주기 
		} else {
			model.addAttribute("mesg", "회원 정보가 없습니다. 다시 시도해주세요. ");
			return "loginForm";//jsp로 이동
		}
	}

	@RequestMapping(value = "/loginCheck/logout")
	public String logout(HttpSession session) {
		System.out.println("LoginController : logout() : 로그아웃! session종료 후 main.jsp로 이동");
		session.invalidate();
		return "redirect:../";	//.xml에 설정된 main.jsp로 이동
//		/loginCheck/logout 두 개의 경로를 넘어야 하기 때문에 ../ 은 loginCheck의 상위 주소로 이동
	}
}

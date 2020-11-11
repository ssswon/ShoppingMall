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
		System.out.println("loginForm에서 넘어온 ID,PASSWORD : " + map);
		MemberDTO dto = service.login(map);
		if (dto != null) {
			session.setAttribute("login", dto);
			return "main";
		} else {
			model.addAttribute("mesg", "회원 정보가 없습니다. 다시 시도해주세요. ");
			return "loginForm";
		}
	}
}

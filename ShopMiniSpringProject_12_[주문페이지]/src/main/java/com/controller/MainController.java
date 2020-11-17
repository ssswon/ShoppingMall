package com.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller	//비회원이더라도 goodsList가 보이게끔 
public class MainController {
	@Autowired
	GoodsService service;
	
	@RequestMapping("/")
	public ModelAndView goodsList() {
		List<GoodsDTO> list = service.goodsList("top"); //top 카테고리 목록 가져오기 
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList",list);
		mav.setViewName("main");//main.jsp => include goodsList.jsp
		return mav;
	}
}

package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	
	@RequestMapping(value="/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory") String gCategory) {
//		if(gCategory == null) {
//			gCategory="top";
//		}
		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		
		//request.setAttribute("goodsList",list)와 동일 
		mav.addObject("goodsList",list);
		mav.setViewName("main");//main.jsp
		return mav;
	}
	
	@RequestMapping(value="/goodsRetrieve")//view페이지는 goodsRetrieve.jsp
	@ModelAttribute("goodsRetrieve")// request의 key값
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) {
		System.out.println("GoodsController : goodRetrieve() : goodsList에서 넘어온 gCode : "+gCode);
		GoodsDTO dto = service.goodsRetrieve(gCode);
		return dto;	//request.setAttribute("goodsRetrieve",dto);
	}
	
	@RequestMapping("/loginCheck/cartAdd")//interceptor를 거친다.
	public String cartAdd(CartDTO dto,HttpSession session) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		dto.setUserid(login.getUserid());
		System.out.println("GoodsController : cartAdd() : cartDTO :" +dto);
		session.setAttribute("mesg",dto.getgCode());
		service.cartAdd(dto);
		return "redirect:../goodsRetrieve?gCode="+dto.getgCode();
	}
	
	@RequestMapping("/loginCheck/cartList")
	public String cartList(HttpSession session,RedirectAttributes attr) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();
		List<CartDTO> list = service.cartList(userid);
		//redirectAttribute에 저장하고 cartList.jsp로 리다이레그
		attr.addFlashAttribute("cartList", list);
		System.out.println("GoodsController : cartList() : user에 대한 cartList:"+list);
		return "redirect:../cartList"; // servlet-context에 등록된 것을 참고하여 cartList.jsp를 찾아간다
		// RedirectAttributes
	}

	@RequestMapping("/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam HashMap<String,String> map) {
		
		System.out.println("GoodsController : cartUpdate() : map="+map);
		service.cartUpdate(map);
	}
	
	@RequestMapping("/loginCheck/cartDelete")
	@ResponseBody
	public void cartDelete(@RequestParam("num") int num) {
		
		System.out.println("GoodsController : cartDelete() : num ="+num);
		service.cartDelete(num);
	}
	
}

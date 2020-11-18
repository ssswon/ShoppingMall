package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	@Autowired
	MemberService mservice;
	
	@RequestMapping(value="/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory") String gCategory) {
		if(gCategory == null) {
			gCategory="top";
		}
		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		
		//request.setAttribute("goodsList",list)�� ���� 
		mav.addObject("goodsList",list);
		mav.setViewName("main");//main.jsp
		return mav;
	}
	
	@RequestMapping(value="/goodsRetrieve")//view�������� goodsRetrieve.jsp
	@ModelAttribute("goodsRetrieve")// request�� key��
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) {
		System.out.println("GoodsController : goodRetrieve() : goodsList���� �Ѿ�� gCode : "+gCode);
		GoodsDTO dto = service.goodsRetrieve(gCode);
		return dto;	//request.setAttribute("goodsRetrieve",dto);
	}
	
	@RequestMapping("/loginCheck/cartAdd")//interceptor�� ��ģ��.
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
		//redirectAttribute�� �����ϰ� cartList.jsp�� �����̷���
		attr.addFlashAttribute("cartList", list);
		System.out.println("GoodsController : cartList() : user�� ���� cartList:"+list);
		return "redirect:../cartList"; // servlet-context�� ��ϵ� ���� �����Ͽ� cartList.jsp�� ã�ư���
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
	
	@RequestMapping("/loginCheck/delAllCart")
	public String delAllCart(@RequestParam(value= "check") List<Integer> num) {//check�� �ֵ��� list�� ��� ��������
		System.out.println("GoodsController : delAllCart() �Ѿ�� List<> num = "+num);
		service.delAllCart(num);	
		return "redirect:../loginCheck/cartList";
	}
	
	@RequestMapping("/loginCheck/delAllCart2")
	public void delAllCart2(@RequestParam("list") List<Object> list) {
		System.out.println(list);
	}
	
	@RequestMapping("/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam("num") int num,HttpSession session, RedirectAttributes xxx) {
		System.out.println("GoodsController : orderConfirm() : num = "+num);
		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
		mdto = mservice.myPage(mdto.getUserid());
		CartDTO cdto = service.orderConfirmByNum(num);
		xxx.addFlashAttribute("mdto", mdto);
		xxx.addFlashAttribute("cdto", cdto);
		return "redirect:../orderConfirm";
	}
	
	//����
	@RequestMapping("/loginCheck/directOrder")
	public String directOrder(HashMap<String,String> map , HttpSession session, RedirectAttributes xxx, CartDTO cdto ) {
		//map���� CartDTO���·� ������ �ȴ�. 
		System.out.println("GoodsController : directOrder() : HashMap = "+map);
		MemberDTO mdto = (MemberDTO)session.getAttribute("login");
		mdto = mservice.myPage(mdto.getUserid());
		cdto.setUserid(mdto.getUserid());
		xxx.addFlashAttribute("mdto",mdto);
		xxx.addFlashAttribute("cdto", cdto);
		return "redirect:../orderConfirm";
	}
	
	//주문 완료
	@RequestMapping(value="/loginCheck/orderDone",method=RequestMethod.GET)
	public String orderDone(OrderDTO odto,int orderNum, HttpSession session, RedirectAttributes xxx) {
		System.out.println("GoodsController : orderDone() OrderDTO :"+odto+" , orderNum : "+orderNum);
		MemberDTO mdto =(MemberDTO)session.getAttribute("login");
		odto.setUserid(mdto.getUserid());
		service.orderDone(odto,orderNum);//orderNum 은 cart테이블에서 삭제하기 위해 필요 	//tx처리
		xxx.addFlashAttribute("odto", odto);
		return "redirect:../orderDone";	//servlet-context.xml에 등록
	}
}


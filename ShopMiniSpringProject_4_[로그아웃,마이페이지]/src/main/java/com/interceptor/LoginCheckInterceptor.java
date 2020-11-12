package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인 유무 검사 인터셉터 
// /loginCheck/**  장바구니, 마이페이지, 장바구니에서 삭제, 수량 업데이터, 주문 등 회원전용 메뉴에서 선 동작
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(" ====== preHandle 동작 ====== ");
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null) {
			response.sendRedirect("../loginForm"); // servlet-context.xml 에서 등록된 loginForm.jsp 로 이동
			return false; // 그 뒤 작업들을 하지 않음.
		} else {
			return true; // 로그인 정보가 있는 경우 이후 작업 계속 진행
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(" ====== postHandle 동작 ====== ");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(" ====== afterCompletion 동작 ====== ");
	}


}

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- if,each 등을 사용하기위함 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!-- el태그와 jstl태그를 사용하면, 다양한 제어문 사용가능 -->

<!-- 비회원메뉴 -->
<c:if test="${empty login }">
	<a href="LoginServlet">로그인</a>&nbsp;
<a href="CartListServlet">장바구니</a>&nbsp;
<a href="MemberUIServlet">회원가입</a>&nbsp;
</c:if>

<!-- 회원전용메뉴  -->
<c:if test="${!empty login }"><!-- 세션에서 로그인 정보 검사후 로그인 된 경우 -->
	안녕하세요 ? ${login.username }님! <br>
	<a href="LoginServlet">로그인</a>&nbsp;
	<a href="CartListServlet">장바구니</a>&nbsp;
	<a href="MemberUIServlet">회원가입</a>&nbsp;
</c:if>
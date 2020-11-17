<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<c:if test="${!empty success }">
	<script>alert("${success}")</script>	<!-- 회원가입 성공문구띄우기 --><!-- "${success}" 주의! -->
</c:if>
</head>
<body>
<h1>Main 화면입니다</h1>
<jsp:include page="common/top.jsp" flush="true" /><br>
<jsp:include page="common/menu.jsp" flush="true" />
<hr>
<jsp:include page="goods/goodsList.jsp" flush="true" /><!-- 가장 먼저 상품이 보이는 곳 -->
<hr>

</body>
</html>



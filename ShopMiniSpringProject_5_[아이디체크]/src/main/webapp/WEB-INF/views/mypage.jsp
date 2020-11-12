<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>mypage.jsp</title>
</head>
<body>
	<h1>mypage</h1>
	<jsp:include page="common/top.jsp" flush="true"></jsp:include><br>
	<jsp:include page="common/menu.jsp" flush="true"></jsp:include><br>
	<hr>
	<jsp:include page="member/mypage.jsp" flush="true"></jsp:include>
</body>
</html>
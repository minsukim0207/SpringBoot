<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" url="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원정보조회성공</title>
</head>
<body>
	<h2>회원정보조회</h2>
	<table>
		<tr>
			<td>회원정보</td>
			<td>회원아이디</td>
			<td>회원이름</td>
			<td>회원나이</td>
		</tr>
		<c:set var="dto" value="{user}"/>
		<tr>
			<td>${dto.getUserNo()}</td>
			<td>${dto.getUserId()}</td>
			<td>${dto.getUserName()}</td>
			<td>${dto.getUserAge()}</td>
		</tr>
	</table>
	<a href="index.jsp">메인페이지</a>
</body>
</html>
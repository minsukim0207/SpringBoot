<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�˻�</title>
</head>
<body>
	<h1>ȸ��������ȸ</h1>
	<form action="<%request.getContextPath() %> /selectUser">
		<input type="text" name="userNo" placeholder="ȸ����ȣ�Է�"/>
		<input type="submit" value="��ȸ"/>
	</form>
</body>
</html>
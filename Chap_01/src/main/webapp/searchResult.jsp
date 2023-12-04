<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.kh.model.vo.DTO" %>
<%@ page import="java.util.List" %>
<%
	List<DTO> userList = (List<DTO>) request.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�˻� ���</title>
</head>
<body>
	<h2>��ȸ ���</h2>
	<table border="1">
		<thead>
			<tr>
				<th>����� ��ȣ</th>
				<th>����� ID</th>
				<th>����� �̸�</th>
				<th>����� ����</th>
		</thead>
		<tbody>
			<%
				for(DTO user : userList) {
			%>
			<tr>
				<td><%=user.getUser_number()%></td>
				<td><%=user.getUser_id()%></td>
				<td><%=user.getUser_name()%></td>
				<td><%=user.getUser_age()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>
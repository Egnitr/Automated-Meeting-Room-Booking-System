<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.hsbc.system.model.Users"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome admin</h1>
	<%=session.getAttribute("time")%>
	<%
		if((session.getAttribute("userOb"))!=null){
			Users u=(Users)session.getAttribute("userOb");
	%>
	<%=u.getLastLoggedIn() %>
	<%} %>
	
	<br><a href="createRoom.html">Create Room</a>
</body>

</html>
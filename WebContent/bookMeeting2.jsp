<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList, java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("roomList") == null) {
	%>
	<h2>All rooms are available</h2>
	<%
		} else {
			HashMap<String, ArrayList<String>> rooms = (HashMap) session.getAttribute("roomList");
	%>
	<table>
		<tr>
			<td>Room name</td>
			<td>Meeting start time</td>
			<td>Meeting End Time</td>
		</tr>

		<%
			for (String room : rooms.keySet()) {
		%>
		<tr>
			<td><%=room %></td>
			<td><%=rooms.get(room).get(0) %></td>
			<td><%=rooms.get(room).get(1) %></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
</body>
</html>
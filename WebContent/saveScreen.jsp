<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>To-Do List Mania Save Screen</title>
	<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body class="saveCentering">
	<h1 class="thanks">Thanks for stopping by!</h1>
	<br>
	<h2 class="thanks2">Use this ID to return to your list: </h2>
	
	<%
	session = request.getSession();
	out.print("<p class=\"rainbow-text\">" + session.getAttribute("user_ID") + "</p>");
	session.invalidate();
	%>
	<br>
	
	<form action="index.html" method="get">
    <button class="whiteButton" type="submit">Return Home</button>
	</form>
</body>
</html>
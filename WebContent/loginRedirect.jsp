<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Loading To-Do List</title>
	<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body class="loginRedirect-body">
	<% session = request.getSession(); %>
	<form action="ToDoListServlet_View" method="post">
	<button class="glowing-Button" type="submit">Enter To-Do List Paradise</button>
	</form>
</body>

</html>
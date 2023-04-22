<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>To-Do List Mania</title>
	<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body class="welcome-bodyFont">
	<h1 class="rainbow-text"> To-Do List Mania </h1>
	<h3 class="sub">View, Add, and Delete to your list's content.</h3>
	<br>

	<%
		//Display List
		session = request.getSession();
		String theList = session.getAttribute("user_list").toString();
		out.println("<p class=\"listFont\">" + theList + "</p>");
	%>
	<br><br>
	
	<h3>Add to List</h3>
	<form action="ToDoListServlet_Add" method="post">
	<input type="text" id="toAdd" name="toAdd">
	<button class="glowing-ButtonSmall" type="submit">Add Item</button>
	</form>	
	
	
	<h3>Delete from List</h3>
	<form action="ToDoListServlet_Delete" method="post">
	<input type="number" id="toDelete" name="toDelete">
	<button class="glowing-ButtonSmall" type="submit">Delete Item No.</button>
	</form>
	<br>
	
	<form action="ToDoListServlet_Save" method="post">
	<button class="glowing-ButtonSmall" type="submit">Save and Exit</button>
	</form>	

</body>
</html>
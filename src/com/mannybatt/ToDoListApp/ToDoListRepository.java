package com.mannybatt.ToDoListApp;

public class ToDoListRepository {
	
	public ToDoListRepository() {
		System.out.println("ToDoListRepository Created.");
	}
	
	/**
	 * This will generate a String from the To-Do List
	 * Database and will return it. This is for Restful
	 * API Functionality. 
	 * 
	 * @param id User ID for Logging in
	 * @return String Representation of ToDo List
	 */
	public ToDoList retrieveList(int id) {
		System.out.println("retreive List launched");
		
		ToDoList tdList = ToDoListDBTools.loadList(id);
		
		//Create ToDoList String
		Object[] listArray = tdList.arrayOut();
		String theList = arrayStringerizer(listArray);
		
		return tdList;
	}
	
	/**
	 * Used for formatting the look of the list String.
	 * 
	 * @param greyArray Container of Strings.
	 * @return Formatted list String.
	 */
	protected static String arrayStringerizer(Object[] greyArray) {
		String output = "<br>~*~*~ Your Incredible To-Do List ~*~*~<br>";

		for (int i = 0; i < greyArray.length; i++) {
			output += ((i + 1) + ") " + greyArray[i] + "<br>");
		}
		return (output + "<br>");
	}
}




/*
// Database access constants
private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
private static final String DB_URL = "jdbc:mysql://localhost:3306/todolist?useSSL=false&amp;serverTimezone=UTC";
private static final String USER = "root";
private static final String PASS = "kululu966";

Connection con = null;

public ToDoListRepository() {
	try {
		Class.forName(JDBC_DRIVER);
		con = DriverManager.getConnection(DB_URL, USER, PASS);
	} catch (Exception e) {
		System.out.println(e); 
	}
}
*/

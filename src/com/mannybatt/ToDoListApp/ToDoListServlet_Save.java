package com.mannybatt.ToDoListApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1> Hibernate Save and Preparation for Close </h1>
 * This servlet is an interface for the ToDoList Object. It 
 * keeps the web application functionality separate from the
 * core ToDoList Object operations. This makes it possible to 
 * update the web application appearances later while keeping the same
 * functionality.
 * <p>
 * 
 * @author Manny Batt  
 * @version 1.0
 *
 */
@WebServlet("/ToDoListServlet_Save")
public class ToDoListServlet_Save extends HttpServlet {
	
	//Database access constants
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/todolist?useSSL=false&amp;serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "kululu966";
	
	/**
	 * The doPost method in this servlet is responsible for saving the
	 * ToDoList into the Hibernate session. If this is a users first 
	 * time writing a list, a query into the mySql DB will be executed
	 * in order to pull back the ID for the user. There may be more 
	 * efficient ways of getting that ID but this is functional with 
	 * my limited Hibernate abilities.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Get HttpSession and save the ToDoList
		HttpSession session = request.getSession();
		ToDoListDBTools.saveList( (ToDoList)session.getAttribute("tdList"));
		
		//If there's a new user, grab ID via SQL Query
		if((int)session.getAttribute("newUserFlag") == 1) {
			session.setAttribute("user_ID", getTheSillyID());
		}
		
		//Redirect to final screen web application
		response.sendRedirect(request.getContextPath() + "/saveScreen.jsp");
	}

	/**
	 * This private method does all of the SQL Query handling
	 * and returns the new ID associated with the new user.
	 * @return New User's ID
	 */
	private int getTheSillyID() {
		Connection conn = null;
		Statement stmt = null;
		int id = -1;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      String sql = "SELECT next_val FROM todolist.hibernate_sequence;";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name		    	 
		         id  = rs.getInt(1);
		         System.out.println("rs: " + id);
		      }
		      rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{ //close connections		      
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   System.out.println("Succesfully Retrieved.");
		   return id-1;
	}
}



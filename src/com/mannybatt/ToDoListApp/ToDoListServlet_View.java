package com.mannybatt.ToDoListApp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1> View the ToDoList </h1>
 * This servlet is called in order to view the 
 * user's ToDoList, new and returning users alike.
 * <p>
 * @author Manny Batt  
 * @version 1.0
 *
 */
@WebServlet("/ToDoListServlet_View")
public class ToDoListServlet_View extends HttpServlet {

	private static ToDoList tdList;

	/**
	 * All of the action occurs in the doPost method here.
	 * This is responsible for sorting between new and 
	 * returning users. The servlet is also designed to be
	 * revisited after each add or delete. Most of the 
	 * objects used in the web application's HttpSession
	 * are set in this method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Get HttpSession and create variables
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("login_ID");
		String theList = "";		

		/*
		 * Generate ToDoList String output. Loads appropriate
		 * settings for new users, returning users loading
		 * for the first time, and users returning from add & 
		 * delete actions.
		*/		
		//~Returning Users
		if (id != 0) {
			
			//~If the list has never been loaded
			if (session.getAttribute("tdList") == null) {
				//Load list and set to HttpSession
				tdList = (ToDoList) ToDoListDBTools.loadList(id);				
				session.setAttribute("tdList", tdList);
				
				//Create ToDoList String
				Object[] listArray = tdList.arrayOut();
				theList = arrayStringerizer(listArray);
				session.setAttribute("user_list", theList);
				
				System.out.println("list exists and has never been loaded");
			}
			
			//~If we're visiting after an add or delete
			else {
				//Load list
				tdList = (ToDoList) session.getAttribute("tdList");
				
				//Create ToDoList String
				Object[] listArray = tdList.arrayOut();
				theList = arrayStringerizer(listArray);
				session.setAttribute("user_list", theList);
				
				System.out.println("visiting after an add or delete");
			}
		} //~New Users
		  else {
			//Create & save new ToDoList and set to HttpSession
			tdList = new ToDoList();
			ToDoListDBTools.saveList(tdList);			
			session.setAttribute("tdList", tdList);			
			
			//Create new ToDoList String and set to HttpSession
			theList = "~New List~";
			session.setAttribute("user_list", theList);			
			session.setAttribute("user_ID", tdList.getDaId()); //The true ID is pulled in save servlet
			
			System.out.println("new user and list");
		}

		// Send to welcome screen with list ready
		response.sendRedirect(request.getContextPath() + "/welcome.jsp");
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

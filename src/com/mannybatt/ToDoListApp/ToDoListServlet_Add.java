package com.mannybatt.ToDoListApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1> Add item to ToDoList </h1>
 * This servlet is called when adding a new item to
 * the ToDoList.
 * <p>
 * @author Manny Batt  
 * @version 1.0
 *
 */
@WebServlet("/ToDoListServlet_Add")
public class ToDoListServlet_Add extends HttpServlet {

	/**
	 * The doPost method is responsible for taking the user's
	 * new item from the HttpSession and adds it onto the  
	 * the ToDoList using the Object's methods 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Get HttpSession, ToDoList, and the deletion choice
		HttpSession session = request.getSession();
		ToDoList tdList = (ToDoList) session.getAttribute("tdList");
		String toAdd = request.getParameter("toAdd").toString();
		System.out.println("Being Added: " + toAdd);
		
		//Slap it on there
		tdList.addItem(toAdd);
		
		//Recreate the String representation of the list being displayed, save to session
		Object[] listArray = tdList.arrayOut();
		String theList = ToDoListServlet_View.arrayStringerizer(listArray);
		session.setAttribute("user_list", theList);
		session.setAttribute("user_ID", tdList.getDaId());
		
		//Redirect back to main menu
		response.sendRedirect(request.getContextPath() + "/welcome.jsp");
	}
}
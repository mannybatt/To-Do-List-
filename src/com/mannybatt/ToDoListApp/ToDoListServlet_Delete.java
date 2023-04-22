package com.mannybatt.ToDoListApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1> Delete Item from ToDoList </h1>
 * This servlet is called when deleting an item from the
 * ToDoList.
 * <p>
 * @author Manny Batt  
 * @version 1.0
 *
 */
@WebServlet("/ToDoListServlet_Delete")
public class ToDoListServlet_Delete extends HttpServlet {
	
	/**
	 * The doPost method is responsible for taking the user
	 * defined choice from the HttpSession and deletes the 
	 * Corresponding list item using the ToDoList Object methods
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Get HttpSession, ToDoList, and the deletion choice
		HttpSession session = request.getSession();
		ToDoList tdList = (ToDoList) session.getAttribute("tdList");
		int toDelete = Integer.parseInt(request.getParameter("toDelete"));
		System.out.println("Being Deleted: " + (toDelete));
		
		//Get rid of it!!
		tdList.deleteItem(toDelete-1);
		
		//Recreate the String representation of the list being displayed, save to session
		Object[] listArray = tdList.arrayOut();
		String theList = ToDoListServlet_View.arrayStringerizer(listArray);
		session.setAttribute("user_list", theList);
		session.setAttribute("user_ID", tdList.getDaId());
		
		//Redirect back to main menu
		response.sendRedirect(request.getContextPath() + "/welcome.jsp");
	}
}
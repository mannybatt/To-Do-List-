package com.mannybatt.ToDoListApp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1> Program Setup and Preparation </h1>
 * This servlet is responsible for preparing the rest of the
 * program for operation. Unfortunately, it was a mess attempting
 * to separate new users from returning users so this servlet acts
 * as a precursor to the rest of the program. 
 * <p>
 * 
 * @author Manny Batt  
 * @version 1.0
 *
 */
@WebServlet("/ToDoListServlet_newUserCheck")
public class ToDoListServlet_newUserCheck extends HttpServlet {       

	/**
	 * The doPost method is designed to handle the user input
	 * from the launch page, determine if it is a valid choice, set a
	 * few parameters in the HttpSession, and send the browser off to 
	 * the 'home page redirect' page(which was sadly needed as I don't
	 * think its possible to redirect to yet another servlet)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get HttpSession, set "login_ID" in session
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("login_ID"));
		session.setAttribute("login_ID", id); //Now its an int instead of String
		
		//New user
		if(id == 0) {
			session.setAttribute("newUserFlag", 1);
		}
		//Returning User
		else if(id != 0) {
			session.setAttribute("newUserFlag", 0);
		}
		//set "newUserFlag" in session
		System.out.println("newUserFlag: " + session.getAttribute("newUserFlag"));
		response.sendRedirect(request.getContextPath() + "/loginRedirect.jsp");
	}
}

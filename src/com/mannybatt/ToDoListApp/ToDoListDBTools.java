package com.mannybatt.ToDoListApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * <h1> Save and Load ToDoList via mySql DB</h1>
 * This is designed to be an easy way to save and
 * load ToDoLists from a mySql DB using the setup 
 * found in HibernateUtil.
 * <p>
 * @author Manny Batt  
 * @version 1.0
 *
 */
public class ToDoListDBTools {

	/**
	 * Saves a ToDoList Object to via Hibernate.
	 * @param toSave ToDoList Object being saved.
	 */
	public static void saveList(ToDoList toSave) {

		//Get HttpSession and create Transaction
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Transaction transaction = session.beginTransaction();

		//Save List and commit Transaction
		try {
			session.merge(toSave);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		//Close resources
		session.close();
	}

	/**
	 * Loads and returns a ToDoList via Hibernate with
	 * a user ID.
	 * @param toLoad User ID as int.
	 * @return Object of ToDoList being loaded.
	 */
	public static ToDoList loadList(int toLoad) {

		//Get HttpSession
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//Acquire ToDoList from DB
		try {
			ToDoList toReturn = (ToDoList) session.get(ToDoList.class, toLoad);			
			return toReturn;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		//Close resources
		session.close();
		return null;
	}
}
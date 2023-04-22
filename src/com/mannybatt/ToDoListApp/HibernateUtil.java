package com.mannybatt.ToDoListApp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
import com.mannybatt.ToDoListApp.ToDoList;

/**
 * <h1>Hibernate SessionFactory Creator</h1>
 * A custom way of creating a SessionFactory with
 * settings outlined in "hibernate.cfg.xml".
 * <p>
 * @author Manny Batt  
 * @version 1.0
 *
 */
public class HibernateUtil {
	
	//All of this code runs prior to any methods. The
	//entire session factory is therefore prepared no
	//matter what.
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new Configuration()
                                .configure()
                                .addPackage("com.mannybatt.ToDoListApp") //the fully qualified package name
                                .addAnnotatedClass(ToDoList.class)
                                .buildSessionFactory();
 
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    /**
     * Returns a fully prepared SessionFactory
     * for use with ToDoList.
     * @return Custom SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

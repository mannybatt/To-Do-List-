package com.mannybatt.ToDoListApp;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;

@Path("todolists")
public class ToDoListResource {
	
	ToDoListRepository repo = new ToDoListRepository();
	
	@GET
	@Path("list/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ToDoList getToDoList(@PathParam("id") int id) {
		
		System.out.println("getToDoList Called, retrieving list...");
		return repo.retrieveList(id);
	}
}

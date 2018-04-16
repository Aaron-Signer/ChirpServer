package edu.wc.cs383.chirp;

import static spark.Spark.get;
import static spark.Spark.put;

import java.util.UUID;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class UserController {
	
	// HTTP verb -> REST meaning
	// GET -> read
	// PUT -> update
	// POST -> create
	// DELETE -> delete
	
	public UserController(final UserRepository service) {
		get("/users", (req, res) -> {
		 return service.getUsers();
		}, json());
		
		get("/users/:email", (req, res) -> {
			return service.getUserByEmail(req.params(":email"));
		}, json());		
		
		put("/users", (req, res) -> {
			User u = new User("Sam", "Sam-Hockenberry","hocksa22@wclive.westminster.edu");
			service.addUser(u);
			return service.getUsers();
		}, json());
	}


	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	private static ResponseTransformer json() {
		return UserController::toJson;
	}

}

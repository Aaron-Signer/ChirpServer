package edu.wc.cs383.chirp;

import static spark.Spark.*;

import java.io.IOException;
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
		
//		Returns the list of users
		get("/users", (req, res) -> {
		 return service.getUsers();
		}, json());
		
//		Returns an user given an email address
		get("/users/:email", (req, res) -> {
			return service.getUserByEmail(req.params(":email"));
		}, json());		
		
//		Creates an user in the database
		post("/users/:handle/:email/:password", (req, res) -> {
			User u = new User(req.params(":handle"),req.params(":email"), req.params(":password"));
			service.addUser(u);
			return service.getUsers();
		}, json());
		
//		Delete an user by email
		delete("/users/:email", (req, res) -> {
			service.removeUserByEmail(req.params(":email"));
			return service.getUsers();
		}, json());
		
//		Update an user by email
		put("/users/:email/:handle/:password", (req, res) -> {
			service.updateUserByEmail(req.params(":email"), req.params(":password"), req.params(":handle"));
			return service.getUsers();
		}, json());
				
//		exception(ArrayIndexOutOfBoundsException.class, (exception, request, response) -> {
//		    halt(404);
//		});
		
		exception(UserNotFoundException.class, (exception, request, response) -> {
		    response.status(400);
		    response.body("No User Exist");
		});
		
		exception(DuplicateEmailException.class, (exception, request, response) -> {
		    response.status(409);
		    response.body("Duplicate Email");
		});
		
	}


	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	private static ResponseTransformer json() {
		return UserController::toJson;
	}

}

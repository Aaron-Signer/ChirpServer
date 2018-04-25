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
		
//		Returns a user given an email address
		get("/users/:email", (req, res) -> {
			return service.getUserByEmail(req.params(":email"));
		}, json());		
		
		post("/users/:handle/:email/:password", (req, res) -> {
			User u = new User(req.params(":handle"),req.params(":email"), req.params(":password"));
			service.addUser(u);
			return service.getUsers();
		}, json());
		
		get("/throwexception", (req, res) -> {
			throw new DuplicateEmailException("BAD");
		});
		
//		exception(ArrayIndexOutOfBoundsException.class, (exception, request, response) -> {
//		    halt(404);
//		});
		
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

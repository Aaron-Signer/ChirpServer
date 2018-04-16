package edu.wc.cs383.chirp;

import static spark.Spark.*;

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
		
		post("/users/:name/:username/:email", (req, res) -> {
			User u = new User(req.params(":name"), req.params(":username"),req.params(":email"));
			service.addUser(u);
			return service.getUsers();
		}, json());
		
//		delete("/users/:email"), (req, res) -> {
//		}, json());
	}


	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	private static ResponseTransformer json() {
		return UserController::toJson;
	}

}

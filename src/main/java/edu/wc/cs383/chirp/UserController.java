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
//			res.status(299);
		 return service.getUsers();
		}, json());
		
//		Returns the sorted watchlist for an email
		get("/users/watchlist/:email", (req, res) -> {
			return service.getWatchlistByEmail(req.params(":email"));
		}, json());
		
//		Returns a list containing all the emails
		get("/users/getEmails", (req, res) -> {
			return service.getEmailList();
		}, json());
		
//		Returns a list containing all the handles
		get("/users/getHandles", (req, res) -> {
			return service.getHandleList();
		}, json());
		
//		Returns a list of the watched users
		get("/users/getWatchedList/:email", (req, res) -> {
			return service.getWatchedUsers(req.params(":email"));
		}, json());
		
		
//		Returns true if the user exist and the password is valid
		get("/users/verifyEmailAndPassword/:email/:password", (req, res) -> {
			service.verifyUser(req.params(":email"), req.params(":password"));
			res.status(200);
			return true;
		}, json());	
		
//		Returns an user given an email address
		get("/users/getUser:email/:password", (req, res) -> {
			service.verifyUser(req.params(":email"), req.params(":password"));
			return service.getUserByEmail(req.params(":email"));
		}, json());	
		
//		Creates an user in the database
		post("/users/createUser/:email/:handle/:password", (req, res) -> {
			User u = new User(req.params(":handle"),req.params(":email"), req.params(":password"));
			service.addUser(u);
			return true;
		}, json());
				
//		Adds the the user to the current users watchlist
		post("/users/addToWatchlist/:email/:handle", (req, res) -> {
			service.addUserToWatchlist(req.params(":email"), req.params(":handle"));
			return true;
		}, json());	
		
//		Update an user by email
		put("/users/updateUser/:email/:handle/:password", (req, res) -> {
			service.verifyUser(req.params(":email"), req.params(":password"));
			service.updateUserByEmail(req.params(":email"), req.params(":password"), req.params(":handle"));
			return service.getUsers();
		}, json());
				
//		Remove a user from a users watchlist
		delete("/users/removeFromWatchlist/:email1/:email2", (req, res) -> {
			service.removeUserFromWatchlist(req.params(":email1"), req.params(":email2"));
			return true;
		}, json());
		
//		Delete an user by email
		delete("/users/deleteUser/:email/:password", (req, res) -> {
			service.verifyUser(req.params(":email"), req.params(":password"));
			service.removeUserByEmail(req.params(":email"));
			return service.getUsers();
		}, json());
	
		
		
		exception(UserNotFoundException.class, (exception, request, response) -> {
		    response.status(402);
		    response.body("No User Exist");
		});
		
		exception(InvalidPermissionException.class, (exception, request, response) -> {
		    response.status(401);
		    response.body("Invalid Permission");
		});
		
		exception(DuplicateEmailException.class, (exception, request, response) -> {
		    response.status(409);
		    response.body("Duplicate Email");
		});
		
		exception(DuplicateHandleException.class, (exception, request, response) -> {
		    response.status(411);
		    response.body("Duplicate Handle");
		});
		
		exception(AlreadyOnWatchlistException.class, (exception, request, response) ->{
			response.status(412);
			response.body("Already on watchlist");
		});
	}


	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	private static ResponseTransformer json() {
		return UserController::toJson;
	}

}

package edu.wc.cs383.chirp;

import static spark.Spark.*;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class ChirpController {
	
	// HTTP verb -> REST meaning
	// GET -> read
	// PUT -> update
	// POST -> create
	// DELETE -> delete
	
	public ChirpController(final ChirpRepository service) {
		
//		Returns the list of all chirps
		get("/chirps", (req, res) -> {
			return service.getAllChirps();
		}, json());
		
//		Returns a list of chirps for a given email
		get("/chirps/:email", (req, res) -> {
			return service.getChirps(req.params(":email"));
		}, json());
		
//		Adds a new chirp to the chirp database
		post("/chirps/:email/:message", (req, res) -> {
			Chirp c = new Chirp(req.params(":email"), req.params(":message"), new Date());
			service.addChirp(c);
			return null;
		}, json());
		
		exception(UserNotFoundException.class, (exception, request, response) -> {
		    response.status(450);
		    response.body("No User exist");
		});
		
	}

	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	private static ResponseTransformer json() {
		return ChirpController::toJson;
	}

}
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
		post("/chirps/addChirp/:email/:message", (req, res) -> {
			String message = java.net.URLDecoder.decode(req.params(":message"), "UTF-8");
			Chirp c = new Chirp(req.params(":email"),
					message,
					UserRepository.getInstance().getUserByEmail(req.params(":email")).getHandle(),
					new Date());
			service.addChirp(c);
			return true;
		}, json());
		
	}

	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	private static ResponseTransformer json() {
		return ChirpController::toJson;
	}

}
package edu.wc.cs383.chirp;

import static spark.Spark.*;

import org.apache.log4j.BasicConfigurator;

import com.google.gson.Gson;

//import com.shaffer_consulting.simple.InMemoryUserStorage;
//import com.shaffer_consulting.simple.UserController;
//import com.shaffer_consulting.simple.UserServiceImpl;

public class ChirpServer {
    
    public static void main(String[] args) {
		port(80);
//		BasicConfigurator.configure();
		new UserController(UserRepository.getInstance());
		new ChirpController(ChirpRepository.getInstance());
    }
}
package edu.wc.cs383.chirp;

import java.util.ArrayList;

public interface ChirpStorage {

	public void resetChirpRepository();
	public ArrayList<Chirp> getChirps(String email) throws UserNotFoundException;	//In ChirpController
	public ArrayList<Chirp> getAllChirps();		//In ChirpController
	public void addChirp(Chirp c);		//In ChirpController	
}

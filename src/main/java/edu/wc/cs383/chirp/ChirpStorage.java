package edu.wc.cs383.chirp;

import java.util.ArrayList;

public interface ChirpStorage {

	public ArrayList<Chirp> getChirps(String email) throws UserNotFoundException;	//In ChirpController
	public ArrayList<Chirp> getAllChirps();		//In ChirpController
	public void addChirp(Chirp c);		//In ChirpController
//	public void removeChirp(String email);
//	public int getNumberOfChirps();
//	public int getChirpIndex(String email);
	
}

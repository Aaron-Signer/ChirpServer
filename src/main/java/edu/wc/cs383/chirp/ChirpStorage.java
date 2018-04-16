package edu.wc.cs383.chirp;

import java.util.ArrayList;

public interface ChirpStorage {

	public ArrayList<Chirp> getChirps(String email);
	public void addChirp(Chirp c);
	
}

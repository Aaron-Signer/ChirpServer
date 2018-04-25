package edu.wc.cs383.chirp;

import java.util.ArrayList;

public interface ChirpStorage {

	public ArrayList<Chirp> getChirps(String email);
	public void addChirp(Chirp c);
	public void removeChirp(String email);
	public int getNumberOfChirps();
	public int getUserIndex(String email);
	
}

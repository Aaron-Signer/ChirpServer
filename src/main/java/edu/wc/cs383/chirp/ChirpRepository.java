package edu.wc.cs383.chirp;

import java.util.ArrayList;

public class ChirpRepository {
	
	private ArrayList<Chirp> chirps;
	private static ChirpRepository instance = null;
	
	private ChirpRepository()
	{
		chirps = new ArrayList<Chirp>();
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Hello!"));
	}
	
	public static ChirpRepository getInstance()
	{
		if(instance == null)
			instance = new ChirpRepository();
		//butt
		return instance;
	}
	
	public ArrayList<Chirp> getChirps(String email)
	{
		ArrayList<Chirp> tempChirps = new ArrayList<>();
		for(int i = 0; i < chirps.size(); i++)
			if(chirps.get(i).getEmail().equals(email))
				tempChirps.add(chirps.get(i));
		
		return tempChirps;
	}
	
	public void addChirp(Chirp c)
	{
		chirps.add(c);
	}

}

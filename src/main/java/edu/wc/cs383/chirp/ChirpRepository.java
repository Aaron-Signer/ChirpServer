package edu.wc.cs383.chirp;

import java.util.*;

public class ChirpRepository{// implements ChirpStorage{
	
	private ArrayList<Chirp> chirps;
	private static ChirpRepository instance = null;
	
	private ChirpRepository()
	{
		chirps = new ArrayList<Chirp>();
		resetChirpRepository();
	}
	
	public static ChirpRepository getInstance()
	{
		if(instance == null)
			instance = new ChirpRepository();
		return instance;
	}
	
	public void resetChirpRepository()
	{
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Hello!", new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Today is nice.", new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Bye.", new Date()));
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

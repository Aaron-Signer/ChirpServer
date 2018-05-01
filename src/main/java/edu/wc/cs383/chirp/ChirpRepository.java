package edu.wc.cs383.chirp;

import java.util.*;

public class ChirpRepository implements ChirpStorage{
	
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
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "WWII was a cover up.", new Date()));
		
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "I had pie today.", new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "It was raining today.", new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "Who wants to go to the park today?", new Date()));
		
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Dogs are better than cats!", new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Track is dumb.", new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "I went for a great walk today.", new Date()));
	}
	
	public ArrayList<Chirp> getChirps(String email) throws UserNotFoundException
	{
		if(UserRepository.getInstance().getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User Exist");
		
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
	
	public ArrayList<Chirp> getAllChirps()
	{
		return chirps;
	}
}

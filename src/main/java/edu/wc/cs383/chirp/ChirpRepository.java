package edu.wc.cs383.chirp;

import java.util.*;

public class ChirpRepository implements ChirpStorage{
	
	private ArrayList<Chirp> chirps;
	private static ChirpRepository instance = null;
	
	private ChirpRepository()
	{
		chirps = new ArrayList<Chirp>();
	}
	
	public static ChirpRepository getInstance()
	{
		if(instance == null)
			instance = new ChirpRepository();
		return instance;
	}
	
//	For testing
	public void resetChirpRepository()
	{
		chirps = new ArrayList<Chirp>();
		try {
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Hello!",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Today is nice.",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "WWII was @a cover up.",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		
		
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "I had pie today.",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "It was raining today.",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "Who wants to go to the park today?",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Dogs are better than cats!",
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Track is dumb.", 
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "I went for a great walk today.",
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		
		}
		catch(UserNotFoundException e)
		{
			
		}
	}
	
//	For testing
	public void resetChirpRepositor2()
	{
		chirps = new ArrayList<Chirp>();
		try {
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Hello!",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "Today is nice. &s",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "WWII was & a cover up.",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("signap22@wclive.westminster.edu", "I hate &AaronSigner",
				UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(),new Date()));
		
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "I had pie today.&",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "It was raining today.",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "&Who wants t&o go to the park today?",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("gurnmc22@wclive.westminster.edu", "&AaronSigner Please kys.",
				UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getHandle(),new Date()));
		
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Dogs are better than cats! & ",
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Track is dumb.", 
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "I went for a great walk today. &",
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Hey, &AaronSigner You stink.",
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		chirps.add(new Chirp("thomjm22@wclive.westminster.edu", "Hey, &MattGurneal You stink.",
				UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getHandle(),new Date()));
		}
		catch(UserNotFoundException e)
		{
			
		}
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

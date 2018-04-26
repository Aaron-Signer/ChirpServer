package edu.wc.cs383.chirp;

import java.util.*;

public class User {

	private String handle, email, password;
	private ArrayList<User> watchlist;
	private PriorityQueue<Chirp> sortedWatchlist;
	
	public User(String h, String e, String p)
	{
		handle = h;
		email = e;
		password = p;
		ChirpComparator comp = new ChirpComparator();
		sortedWatchlist = new PriorityQueue<Chirp>(1, comp);
		watchlist = new ArrayList<User>();
	}
	
	public String getHandle()
	{
		return handle;
	}

	public String getEmail()
	{
		return email;
	}
	
	public ArrayList<User> getWatchlist()
	{
		return watchlist;
	}
	
	public PriorityQueue<Chirp> getSortedWatchlist() throws StorageException
	{
		for(User u: watchlist)
		{
			for(Chirp c: ChirpRepository.getInstance().getChirps(u.getEmail()))
				sortedWatchlist.add(c);
		}
		
		return sortedWatchlist;
		
	}
	
	public void setHandle(String h)
	{
		handle = h;
	}
	
	public void setPassword(String p)
	{
		password = p;
	}
	
	public boolean checkPassword(String p)
	{
		return password.equals(p);
	}
	
	public void addWatched(String email) throws StorageException
	{
		watchlist.add(UserRepository.getInstance().getUserByEmail(email));
	}
}

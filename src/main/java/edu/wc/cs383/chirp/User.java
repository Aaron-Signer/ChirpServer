package edu.wc.cs383.chirp;

import java.util.*;

public class User {

	private String handle, email, password;
	private PriorityQueue<Chirp> sortedWatchlist;
	private ArrayList<User> watched;
	private ChirpComparator comp;

	public User(String h, String e, String p)
	{
		handle = h;
		email = e;
		password = p;
		comp = new ChirpComparator();
		watched = new ArrayList<User>();
		sortedWatchlist = new PriorityQueue<Chirp>(1, comp);
	}

	public String getHandle()
	{
		return handle;
	}

	public String getEmail()
	{
		return email;
	}

	public int getWatchlistSize()
	{
		return sortedWatchlist.size();
	}

	public void setHandle(String h)
	{
		handle = h;
	}

	public void setPassword(String p)
	{
		password = p;
	}

	public PriorityQueue<Chirp> getSortedWatchlist() throws StorageException
	{
		return sortedWatchlist;
	}

	public void checkPassword(String p) throws InvalidPermissionException
	{
		if(!password.equals(p))
			throw new InvalidPermissionException("Wrong password");
	}

	public void addWatched(String email) throws AlreadyOnWatchlistException, UserNotFoundException
	{
		if(getUserIndex(email) != -1)
			throw new AlreadyOnWatchlistException("Already on watchlist");
		watched.add(UserRepository.getInstance().getUserByEmail(email));
		updateWatchlist();
	}

	private void updateWatchlist() throws UserNotFoundException
	{
		sortedWatchlist = new PriorityQueue<Chirp>(1, comp);
		for(User u: watched)
		{
			for(Chirp c: ChirpRepository.getInstance().getChirps(u.getEmail()))
				sortedWatchlist.add(c);
		}
	}

	public void removeWatched(String email) throws UserNotFoundException
	{
		int index = getUserIndex(email);
		if(index == -1)
			throw new UserNotFoundException("No User");
		watched.remove(index);
		updateWatchlist();
	}
	
	private int getUserIndex(String email)
	{
		for(int i = 0; i < watched.size(); i++)
		{
			if(watched.get(i).getEmail().equals(email))
				return i;
		}
		return -1;
	}

	//	public List<Chirp> getSortedWatchlist() throws StorageException
	//	{
	//		for(User u: watchlist)
	//		{
	//			for(Chirp c: ChirpRepository.getInstance().getChirps(u.getEmail()))
	//				sortedWatchlist.add(c);
	//		}
	//		System.out.println(sortedWatchlist.size());
	//		Chirp [] array = new Chirp[sortedWatchlist.size()];
	//		Chirp [] array2 = sortedWatchlist.toArray(array);
	//		for(Chirp c: array2)
	//			System.out.println(c.message);
	//		return new ArrayList<Chirp>(Arrays.asList(array2));
	//	}
}

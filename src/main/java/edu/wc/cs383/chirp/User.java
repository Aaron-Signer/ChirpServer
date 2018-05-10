package edu.wc.cs383.chirp;

import java.util.*;

public class User {

	String handle, email, password;
	PriorityQueue<Chirp> sortedWatchlist;
	ArrayList<User> watched;
	ChirpComparator comp;

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

	public ArrayList<User> getWatchedUsers()
	{
		return watched;
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

	public void addWatched(String handle) throws UserNotFoundException , AlreadyOnWatchlistException
	{
		if(getUserIndexByHandle(handle) != -1)
			throw new AlreadyOnWatchlistException("Already on watchlist");
		watched.add(UserRepository.getInstance().getUserByHandle(handle));
		updateWatchlist();
	}

	public void updateWatchlist() throws UserNotFoundException
	{
		sortedWatchlist = new PriorityQueue<Chirp>(1, comp);
		for(User u: watched)
		{
			for(Chirp c: ChirpRepository.getInstance().getChirps(u.getEmail()))
				sortedWatchlist.add(c);

			for(Chirp c: ChirpRepository.getInstance().getAllChirps())
			{
				
				if(checkChirp(c, u) && !inChirpWatchlist(c))
				{
					System.out.println("added to watchlist");
					sortedWatchlist.add(c);
				}
			}
		}
	}

	public boolean inChirpWatchlist(Chirp newChirp)
	{
		for(Chirp c: sortedWatchlist)
		{
			if(newChirp.getMessage().equals(c.getMessage()) && newChirp.getHandle().equals(c.getHandle()))
				return true;
		}
		return false;
	}
	
	public boolean checkChirp(Chirp c, User u)
	{
		boolean answer = false;
		char [] message = c.getMessage().toCharArray();
		String handle = "";
		if(message.length > 1)
		{
			for(int i = 0; i < message.length; i++)
			{
				if(i == 0)
				{
					if(message[i] == '&' && message[i+1] != ' ')
					{
						int j = i+1;
						while(message[j] != ' ' && (Character.isLetterOrDigit(message[j])))
						{
							handle += message[j];
							j++;
							if(j == message.length)
								break;
						}
						System.out.println(handle + " -- " + c.getMessage());
						if(handle.equals(u.getHandle()))
							answer = true;
					}
				}
				if(i != 0 && i != (message.length -1))
				{
					if(message[i-1] == ' ' && message[i] == '&' && message[i+1] != ' ')
					{
						int j = i+1;
						while(message[j] != ' ' && (Character.isLetterOrDigit(message[j])))
						{
							handle += message[j];
							j++;
							if(j == message.length)
								break;
						}
						System.out.println(handle + " ++ " + c.getMessage());
						if(handle.equals(u.getHandle()))
							answer = true;
					}
				}
			}
		}
		return answer;
	}

	public void removeWatched(String handle) throws UserNotFoundException
	{
		int index = getUserIndexByHandle(handle);
		if(index == -1)
			throw new UserNotFoundException("No User");
		watched.remove(index);
		updateWatchlist();
	}

	private int getUserIndexByEmail(String email)
	{
		for(int i = 0; i < watched.size(); i++)
		{
			if(watched.get(i).getEmail().equals(email))
				return i;
		}
		return -1;
	}

	private int getUserIndexByHandle(String handle)
	{
		for(int i = 0; i < watched.size(); i++)
		{
			if(watched.get(i).getHandle().equals(handle))
				return i;
		}
		return -1;
	}

	private int getUserIndexByHandle2(String handle)
	{
		for(int i = 0; i < UserRepository.getInstance().getNumberOfUsers(); i++)
		{
			if(UserRepository.getInstance().getUsers().get(i).getHandle().equals(handle))
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

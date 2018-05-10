package edu.wc.cs383.chirp;

import java.util.*;

public class UserRepository implements UserStorage{

	private ArrayList<User> users;
	private static UserRepository instance = null;
	
	private UserRepository()
	{
		users = new ArrayList<User>();
	}
	
	public static UserRepository getInstance()
	{
		if(instance == null)
			instance = new UserRepository();
		
		return instance;
	}
	
//	For testing
	public void resetRepository()
	{
		users = new ArrayList<User>();
		users.add(new User("AaronSigner","signap22@wclive.westminster.edu", "Aaron"));
		users.add(new User("MattGurneal","gurnmc22@wclive.westminster.edu", "Matt"));
		users.add(new User("JamieThompson","thomjm22@wclive.westminster.edu", "Jamie"));
	}
	
	public void addUser(User u) throws DuplicateEmailException, DuplicateHandleException
	{
		if(getUserIndexByEmail(u.getEmail()) != -1)
			throw new DuplicateEmailException("User already exits");
		if(getUserIndexByHandle(u.getHandle()) != -1)
			throw new DuplicateHandleException("No User");
		
		users.add(u);
	}
	
	public User getUserByEmail(String email) throws UserNotFoundException
	{		
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
		return users.get(getUserIndexByEmail(email));
	}
	
	public User getUserByHandle(String handle) throws UserNotFoundException
	{		
		if(getUserIndexByHandle(handle) == -1)
			throw new UserNotFoundException("No User");
		return users.get(getUserIndexByHandle(handle));
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public void removeUserByEmail(String email) throws UserNotFoundException
	{
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");

		users.remove(getUserByEmail(email));
	}
	
	public int getNumberOfUsers()
	{
		return users.size();
	}
	
	public void updateUserByEmail(String email, String handle, String password) throws UserNotFoundException, DuplicateHandleException
	{
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
		if(getUserIndexByHandle(handle) != -1)
			throw new DuplicateHandleException("No User");
		
		users.get(getUserIndexByEmail(email)).setHandle(handle);
		users.get(getUserIndexByEmail(email)).setPassword(password);
	}
	
	public ArrayList<String> getWatchedUsers(String email) throws UserNotFoundException
	{
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
		ArrayList<User> temp = getUserByEmail(email).getWatchedUsers();
		ArrayList<String> result = new ArrayList<String>();
		for(User u: temp)
			result.add(u.getHandle());
		return result;
	}
	
	public int getUserIndexByEmail(String email)
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getEmail().equals(email))
				return i;
		}
		return -1;
	}
	
	public int getUserIndexByHandle(String handle)
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getHandle().equals(handle))
				return i;
		}
		return -1;
	}
	
	public PriorityQueue<Chirp> getWatchlistByEmail(String email) throws StorageException
	{
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
		
		getUserByEmail(email).updateWatchlist();	
		return getUserByEmail(email).getSortedWatchlist();
	}
	
	public void removeUserFromWatchlist(String email1, String handle) throws UserNotFoundException, AlreadyOnWatchlistException
	{
		if(getUserIndexByHandle(handle) == -1)
			throw new UserNotFoundException("No User");
		
		getUserByEmail(email1).removeWatched(handle);
	}
	
	public void addUserToWatchlist(String email1, String handle) throws UserNotFoundException, AlreadyOnWatchlistException
	{
		
		if(getUserIndexByHandle(handle) == -1)
			throw new UserNotFoundException("No User");
		
		getUserByEmail(email1).addWatched(handle);
	}

	public void verifyUser(String email, String password) throws InvalidPermissionException, UserNotFoundException
	{
		getUserByEmail(email).checkPassword(password);
	}

	public ArrayList<String> getHandleList() 
	{
		ArrayList<String> handles = new ArrayList<String>();
		for(User u: users)
			handles.add(u.getHandle());
		return handles;
	}
	
	public ArrayList<String> getEmailList() 
	{
		ArrayList<String> emails = new ArrayList<String>();
		for(User u: users)
			emails.add(u.getEmail());
		return emails;
	}
	
}

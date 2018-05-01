package edu.wc.cs383.chirp;

import java.util.*;

public class UserRepository implements UserStorage{

	private ArrayList<User> users;
	private static UserRepository instance = null;
	
	private UserRepository()
	{
		resetRepository();
	}
	
	public static UserRepository getInstance()
	{
		if(instance == null)
			instance = new UserRepository();
		
		return instance;
	}
	
	public void resetRepository()
	{
		users = new ArrayList<User>();
		users.add(new User("Aaron-Signer","signap22@wclive.westminster.edu", "Aaron"));
		users.add(new User("Matt-Gurneal","gurnmc22@wclive.westminster.edu", "Matt"));
		users.add(new User("Jamie-Thompson","thomjm22@wclive.westminster.edu", "Jamie"));
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
	
	public void updateUserByEmail(String email, String password, String handle) throws UserNotFoundException
	{
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
		if(getUserIndexByHandle(handle) == -1)
			throw new UserNotFoundException("No User");
		
		users.get(getUserIndexByEmail(email)).setHandle(handle);
		users.get(getUserIndexByEmail(email)).setPassword(password);
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
			if(users.get(i).getEmail().equals(handle))
				return i;
		}
		return -1;
	}
	
	public PriorityQueue<Chirp> getWatchlistByEmail(String email) throws StorageException
	{
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
		
		return getUserByEmail(email).getSortedWatchlist();
	}
	
//	public PriorityQueue<Chirp> getWatchlistByEmail2(String email) throws StorageException
//	{
//		if(getUserIndexByEmail(email) == -1)
//			throw new UserNotFoundException("No User");
//		
//		return getUserByEmail(email).getSortedWatchlistPQ();
//	}
	
	public void checkPassword(String email, String password) throws InvalidPermissionException
	{
		users.get(getUserIndexByEmail(email)).checkPassword(password);
	}
	
	public void verifyUser(String email, String password) throws InvalidPermissionException, UserNotFoundException
	{
		checkPassword(email, password);
		if(getUserIndexByEmail(email) == -1)
			throw new UserNotFoundException("No User");
	}

	public ArrayList<String> getHandleList() 
	{
		ArrayList<String> handles = new ArrayList<String>();
		for(User u: users)
			handles.add(u.getHandle());
		return handles;
	}
	
}

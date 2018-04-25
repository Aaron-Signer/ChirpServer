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
	
	public void addUser(User u) throws DuplicateEmailException
	{
		if(getUserIndex(u.getEmail()) != -1)
			throw new DuplicateEmailException("User already exits");
		
		users.add(u);
	}
	
	public User getUserByEmail(String email) //throws UserNotFoundException
	{
//		if(getUserIndex(UserRepository.getInstance().getUserByEmail(email).getEmail()) == -1)
//			throw new UserNotFoundException("No User");
			
		return users.get(getUserIndex(email));
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
//	public void removeUserByEmail(String email)
//	{
//		User u = getUserByEmail(email);
//		users.remove(u);
//	}
	
	public int getNumberOfUsers()
	{
		return users.size();
	}
	
	public void updateUserByEmail(String email, String password, String handle)
	{
		users.get(getUserIndex(email)).setHandle(handle);
		users.get(getUserIndex(email)).setPassword(password);
	}
	
	public int getUserIndex(String email)
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getEmail().equals(email))
				return i;
		}
		return -1;
	}
	
}

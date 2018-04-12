package edu.wc.cs383.chirp;

import java.util.*;

public class UserRepository {

	private ArrayList<User> users;
	private static UserRepository instance = null;
	
	private UserRepository()
	{
		users = new ArrayList<User>();
		users.add(new User("Aaron", "Aaron-Signer","signap22@wclive.westminster.edu"));
		users.add(new User("Matt", "Matt-Gurneal","gurnmc22@wclive.westminster.edu"));
		users.add(new User("Jamie", "Jamie-Thompson","thomjm22@wclive.westminster.edu"));


	}
	
	public static UserRepository getInstance()
	{
		if(instance == null)
			instance = new UserRepository();
		
		return instance;
	}
	
	public void addUser(User u)
	{
		users.add(u);
	}
	
	public User getUserByEmail(String email)
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getEmail().equals(email))
				return users.get(i);
		}
		return null;
	}
	
}

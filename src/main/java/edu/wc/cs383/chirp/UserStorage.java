package edu.wc.cs383.chirp;

import java.util.*;

public interface UserStorage{

	public void addUser(User u) throws StorageException;
	public User getUserByEmail(String email) throws StorageException;
	public ArrayList<User> getUsers();
//	public void removeUserByEmail(String email);
	public int getNumberOfUsers();
	public void updateUserByEmail(String email, String name, String handle);
	public int getUserIndex(String email);
	
}

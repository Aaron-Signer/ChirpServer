package edu.wc.cs383.chirp;

import java.util.*;

public interface UserStorage{

	public void addUser(User u) throws StorageException;	//In UserController
	public User getUserByEmail(String email) throws StorageException;	//In UserController
	public ArrayList<User> getUsers();	//In UserController
	public void removeUserByEmail(String email) throws StorageException;	//In UserController
	public int getNumberOfUsers();
	public void updateUserByEmail(String email, String name, String handle) throws StorageException;
	public int getUserIndexByEmail(String email);
	public PriorityQueue<Chirp> getWatchlistByEmail(String email) throws StorageException;
	public ArrayList<String> getHandleList();
}

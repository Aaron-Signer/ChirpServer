package edu.wc.cs383.chirp;

import java.util.*;

public interface UserStorage{

	public void resetRepository();
	public void addUser(User u) throws StorageException;	//In UserController
	public User getUserByEmail(String email) throws StorageException;	//In UserController
	public User getUserByHandle(String handle) throws UserNotFoundException;
	public ArrayList<User> getUsers();	//In UserController
	public void removeUserByEmail(String email) throws StorageException;	//In UserController
	public int getNumberOfUsers();
	public void updateUserByEmail(String email, String name, String handle) throws StorageException;
	public ArrayList<String> getWatchedUsers(String email) throws UserNotFoundException;
	public int getUserIndexByEmail(String email);
	public int getUserIndexByHandle(String handle);
	public PriorityQueue<Chirp> getWatchlistByEmail(String email) throws StorageException;
	public void removeUserFromWatchlist(String email1, String handle) throws UserNotFoundException, AlreadyOnWatchlistException;
	public void addUserToWatchlist(String email1, String handle) throws UserNotFoundException, AlreadyOnWatchlistException;
	public void verifyUser(String email, String password) throws InvalidPermissionException, UserNotFoundException;
	public ArrayList<String> getHandleList();
	public ArrayList<String> getEmailList() ;
}

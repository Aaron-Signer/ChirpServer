package edu.wc.cs383.chirp;

import java.util.*;

public interface UserStorage {

	public void addUser(User u);
	public User getUserByEmail(String email);
	public ArrayList<User> getUsers();
	
}

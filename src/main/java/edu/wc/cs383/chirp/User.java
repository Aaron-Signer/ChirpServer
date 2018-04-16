package edu.wc.cs383.chirp;

import java.util.*;

public class User {

	private String name, handle, id, email;
	private int ids [];
	
	public User(String n, String h, String e)
	{
		name = n;
		handle = h;
		email = e;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getHandle()
	{
		return handle;
	}

	public String getEmail()
	{
		return email;
	}
	
	public void setName(String u)
	{
		name = u;
	}
	
	public void setHandle(String h)
	{
		handle = h;
	}
	
	public void setEmail(String e)
	{
		email = e;
	}

	public boolean equals(User u2) {
		if(u2.getEmail() == email)
	}
	
}

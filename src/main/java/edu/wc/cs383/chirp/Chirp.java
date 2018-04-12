package edu.wc.cs383.chirp;
import java.util.*;

public class Chirp {

	String message, email;
	UUID id;
	
	public Chirp(String em, String m)
	{
		email = em;
		message = m;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String getEmail()
	{
		return email;
	}
	
}

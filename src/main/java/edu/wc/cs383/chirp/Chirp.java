package edu.wc.cs383.chirp;
import java.util.*;

public class Chirp {

	String message, email;
	Date date;
	UUID id;
	
	public Chirp(String em, String m, Date d)
	{
		email = em;
		message = m;
		date = d;
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

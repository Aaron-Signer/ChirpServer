package edu.wc.cs383.chirp;
import java.util.*;

public class Chirp {

	private String message, email, handle;
	private Date date;
	private UUID id;
	private Byte [] image;
	
	public Chirp(String em, String m, String h, Date d)
	{
		email = em;
		message = m;
		handle = h;
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
	
	public String getHandle()
	{
		return handle;
	}
	
	public Date getDate()
	{
		return date;
	}
	
}

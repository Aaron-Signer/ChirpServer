package edu.wc.cs383.chirp;

public class UserNotFoundException extends StorageException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Throwable t) {
		super(message, t);
	}

}

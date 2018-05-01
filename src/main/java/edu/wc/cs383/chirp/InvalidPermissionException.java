package edu.wc.cs383.chirp;

public class InvalidPermissionException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidPermissionException(String message) {
		super(message);
	}

	public InvalidPermissionException(String message, Throwable t) {
		super(message, t);
	}

}

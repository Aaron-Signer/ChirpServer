package edu.wc.cs383.chirp;

public class DuplicateHandleException extends StorageException{

	private static final long serialVersionUID = 1L;

	public DuplicateHandleException(String message) {
		super(message);
	}

	public DuplicateHandleException(String message, Throwable t) {
		super(message, t);
	}

}

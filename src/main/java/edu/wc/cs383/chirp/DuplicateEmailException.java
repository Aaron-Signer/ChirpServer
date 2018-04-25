package edu.wc.cs383.chirp;

public class DuplicateEmailException extends StorageException{

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String message) {
		super(message);
	}

	public DuplicateEmailException(String message, Throwable t) {
		super(message, t);
	}

}

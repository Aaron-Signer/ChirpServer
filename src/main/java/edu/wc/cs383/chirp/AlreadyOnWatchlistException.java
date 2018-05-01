package edu.wc.cs383.chirp;

public class AlreadyOnWatchlistException extends StorageException{

	private static final long serialVersionUID = 1L;

	public AlreadyOnWatchlistException(String message) {
		super(message);
	}

	public AlreadyOnWatchlistException(String message, Throwable t) {
		super(message, t);
	}

}

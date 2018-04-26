package edu.wc.cs383.chirp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
	@Test
	public void setHandleTest() throws StorageException
	{
		UserRepository.getInstance().resetRepository();
		assertEquals(UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(), "Aaron-Signer");
		UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").setHandle("Signer");
		assertEquals(UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(), "Signer");
	}
	
	@Test
	public void removeUserTest() throws StorageException
	{
		UserRepository.getInstance().resetRepository();
		assertEquals(UserRepository.getInstance().getNumberOfUsers(), 3);
		UserRepository.getInstance().removeUserByEmail("signap22@wclive.westminster.edu");
		assertEquals(UserRepository.getInstance().getNumberOfUsers(), 2);
	}
	
	@Test
	public void updateUserTest() throws StorageException
	{
		UserRepository.getInstance().resetRepository();
		UserRepository.getInstance().updateUserByEmail("signap22@wclive.westminster.edu", "Aaron Signer", "Sigstar");
		assertEquals(UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(), "Sigstar");
	}
	
	@Test
	public void getChirpTest() throws StorageException
	{
		UserRepository.getInstance().resetRepository();
		assertEquals(ChirpRepository.getInstance().getChirps("signap22@wclive.westminster.edu").get(0).getMessage(), "Hello!");
	}
	
	@Test
	public void getSortedWatchlist() throws StorageException
	{
		UserRepository.getInstance().resetRepository();
		UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").addWatched("signap22@wclive.westminster.edu");
		assertEquals(UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getSortedWatchlist().peek().getMessage(), "Hello!");
	}
}

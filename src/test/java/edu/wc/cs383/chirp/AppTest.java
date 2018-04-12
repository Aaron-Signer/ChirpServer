package edu.wc.cs383.chirp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	@Test
	public void getNameTest()
	{
		assertEquals(UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getName(), "Aaron");
		assertEquals(UserRepository.getInstance().getUserByEmail("gurnmc22@wclive.westminster.edu").getName(), "Matt");
		assertEquals(UserRepository.getInstance().getUserByEmail("thomjm22@wclive.westminster.edu").getName(), "Jamie");

	}

	@Test
	public void setHandleTest()
	{
		assertEquals(UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(), "Aaron-Signer");
		UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").setHandle("Signer");
		assertEquals(UserRepository.getInstance().getUserByEmail("signap22@wclive.westminster.edu").getHandle(), "Signer");
	}
	
	@Test
	public void getChirpTest()
	{
		assertEquals(ChirpRepository.getInstance().getChirps("signap22@wclive.westminster.edu").get(0).getMessage(), "Hello!");
	}
	
}

package com.pack.atmTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pack.model.Atm;
import com.pack.model.User;

public class AtmTest {
	private User userCreate; 
	private User userLogin;
	private User userIsLoggedIn;
	Atm atm;

	@BeforeEach
	void setUp() throws Exception {
		userCreate = new User(12,"Harry","harrypassword",400);
		userLogin = new User(11,"Johnny","johnnypassword",500);
		atm = new Atm(33,"New Orleans",11);
	}

	@AfterEach
	void resetSetup() {
		userCreate = null;
		userLogin = null;

		atm = null;	
	}

	@Test
	void userCreateTest() {
		assertEquals(userCreate.getUsername(),"Harry");
		assertNotSame(userCreate.getUsername(),"arry");
	}

	@Test
	void atmCreateTest() {
		assertEquals(atm.getAtmID(),33);
		assertEquals(atm.getCity(),"New Orleans");
		assertNotSame(atm.getAtmID(),32);
		assertNotSame(atm.getCity(),"New rleans");
	}
}

package edu.uic.cs442.cs442project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uic.cs442.cs442project.database.User;

public class LoginScreenTests {
	private EntityManagerFactory emf;

	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("mySQLdatabase");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public static User createNewTestUser(EntityManager em){
		CreateNewUserConsole newUser = new CreateNewUserConsole(em);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String userName = "tu" + format.format(date);
		String first = "test";
		String last = "name";
		String password = "asdf";
		newUser.insertNewUser(first, last, userName, password);
		
		LoginScreen login = new LoginScreen(em);
		User result = login.getUser(userName);
		return result;
	}
	
	@Test
	public void userExistsTest(){
		CreateNewUserConsole newUser = new CreateNewUserConsole(em);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String userName = "tu" + format.format(date);
		String first = "test";
		String last = "name";
		String password = "asdf";
		newUser.insertNewUser(first, last, userName, password);
		
		LoginScreen login = new LoginScreen(em);
		String failureDetails = "Newly inserted user was not returned";
		User result = login.getUser(userName);
		Assert.assertTrue(failureDetails, result != null);
	}
	
	@Test
	public void nullUserTest(){
		LoginScreen login = new LoginScreen(em);
		String failureDetails = "Newly inserted user was not returned";
		User result = login.getUser("userijustmadeupthatdoesntexist");
		Assert.assertTrue(failureDetails, result == null);
	}
	
	@Test
	public void validateCorrectPassword(){
		CreateNewUserConsole newUser = new CreateNewUserConsole(em);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String userName = "tu" + format.format(date);
		String first = "test";
		String last = "name";
		String password = "asdf";
		newUser.insertNewUser(first, last, userName, password);
		
		LoginScreen login = new LoginScreen(em);
		String failureDetails = "IsCorrectPassword returns false when it should have returned true";
		User user = login.getUser(userName);
		Assert.assertTrue(failureDetails, login.isCorrectPassword(user, password));
	}
	
	@Test
	public void validateIncorrectPassword(){
		CreateNewUserConsole newUser = new CreateNewUserConsole(em);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String userName = "tu" + format.format(date);
		String first = "test";
		String last = "name";
		String password = "asdf";
		newUser.insertNewUser(first, last, userName, password);
		
		LoginScreen login = new LoginScreen(em);
		String failureDetails = "IsCorrectPassword returns true when it should have returned false";
		User user = login.getUser(userName);
		Assert.assertFalse(failureDetails, login.isCorrectPassword(user, "nottherightpassword"));
	}
}

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

public class CreateNewUserTests {

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
	
	
	@Test
	public void createNewUserTest(){
		CreateNewUserConsole newUser = new CreateNewUserConsole(em);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String userName = "tu" + format.format(date);
		String first = "test";
		String last = "name";
		String password = "asdf";
		newUser.insertNewUser(first, last, userName, password);
		
		User actual = Utility.findUserByUsername(em, userName);
		
		String expectedValue = userName;
		String actualValue = actual.getUsername();
		String failureDetails = "userName is incorrect! Expected: '" + expectedValue + "' Actual: '" + actualValue + "'";
		Assert.assertTrue(failureDetails, actualValue.equals(expectedValue));
		
		expectedValue = first;
		actualValue = actual.getFirstName();
		failureDetails = "First Name is incorrect! Expected: '" + expectedValue + "' Actual: '" + actualValue + "'";
		Assert.assertTrue(failureDetails, actualValue.equals(expectedValue));
		
		expectedValue = last;
		actualValue = actual.getLastName();
		failureDetails = "Last Name is incorrect! Expected: '" + expectedValue + "' Actual: '" + actualValue + "'";
		Assert.assertTrue(failureDetails, actualValue.equals(expectedValue));
		
		expectedValue = password;
		actualValue = actual.getPassword();
		failureDetails = "Password is incorrect! Expected: '" + expectedValue + "' Actual: '" + actualValue + "'";
		Assert.assertTrue(failureDetails, actualValue.equals(expectedValue));
	}

}

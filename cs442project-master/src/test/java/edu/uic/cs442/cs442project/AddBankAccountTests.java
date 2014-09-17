package edu.uic.cs442.cs442project;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uic.cs442.cs442project.database.BankAccount;
import edu.uic.cs442.cs442project.database.User;

public class AddBankAccountTests {

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
	
	public static void generateXBankAccounts(EntityManager em, int userID, int x){
		AddBankAccountConsole abac = new AddBankAccountConsole(em, userID);
		for(int i = 0; i < x; i++){
			abac.insertBankAccount(userID, Utility.generateRandomNumber(100000), Utility.generateRandomNumber(100000),
					Utility.generateRandomString(5), Utility.generateRandomNumber(9001));
		}
	}
	
	@Test
	public void insertZeroBankAccountsTest(){
		User testUser = LoginScreenTests.createNewTestUser(em);
		int id = testUser.getUser_id();
		
		List<BankAccount> accounts = Utility.findBankAccountsByUserID(em, id);
		
		int expectedSize = 0;
		int actualSize = accounts.size();
		String failureDetails = "Size of accounts list is incorrect. Expected: '" + expectedSize + "' Actual: '" + actualSize + "'";
		Assert.assertTrue(failureDetails, expectedSize == actualSize);
		
	}
	
	@Test
	public void insertOneBankAccountTest(){
		User testUser = LoginScreenTests.createNewTestUser(em);
		int id = testUser.getUser_id();
		int routingNumber = 123456;
		int accountNumber = 54321;
		String name = "Checking";
		double balance = 9001.99;
		
		AddBankAccountConsole abac = new AddBankAccountConsole(em, id);
		abac.insertBankAccount(id, routingNumber, accountNumber, name, balance);
		
		List<BankAccount> accounts = Utility.findBankAccountsByUserID(em, id);
		
		int expectedSize = 1;
		int actualSize = accounts.size();
		String failureDetails = "Size of accounts list is incorrect. Expected: '" + expectedSize + "' Actual: '" + actualSize + "'";
		Assert.assertTrue(failureDetails, expectedSize == actualSize);
		
		BankAccount account = accounts.get(0);
		
		
		int expectedValue = id;
		int actualValue = account.getUser();
		failureDetails = "Id is incorrect. Actual: '" + accountNumber + "' Expected: '" + expectedSize + "'";
		Assert.assertTrue(failureDetails, expectedValue == actualValue);
		
		expectedValue = accountNumber;
		actualValue = account.getAccount_number();
		failureDetails = "Account number is incorrect. Actual: '" + accountNumber + "' Expected: '" + expectedSize + "'";
		Assert.assertTrue(failureDetails, expectedValue == actualValue);
		
		String actualName = account.getAccount_name();
		failureDetails = "Account number is incorrect. Actual: '" + actualName + "' Expected: '" + name + "'";
		Assert.assertTrue(failureDetails, name.equals(actualName));
		
		double actualBalance = account.getBalance();
		failureDetails = "Balance is incorrect. Actual: '" + actualBalance + "' Expected: '" + balance + "'";
		Assert.assertTrue(failureDetails, balance  == actualBalance);

	}
	
	@Test
	public void insertTwoBankAccountsTest(){
		User testUser = LoginScreenTests.createNewTestUser(em);
		int id = testUser.getUser_id();
		int routingNumber = 123456;
		int accountNumber = 54321;
		String name = "Checking";
		double balance = 9001.99;
		int routingNumber2 = 123456;
		int accountNumber2 = 654321;
		String name2 = "Savings";
		double balance2 = 1009.99;
		
		AddBankAccountConsole abac = new AddBankAccountConsole(em, id);
		abac.insertBankAccount(id, routingNumber, accountNumber, name, balance);
		abac.insertBankAccount(id, routingNumber2, accountNumber2, name2, balance2);
		
		List<BankAccount> accounts = Utility.findBankAccountsByUserID(em, id);
		
		int expectedSize = 2;
		int actualSize = accounts.size();
		String failureDetails = "Size of accounts list is incorrect. Expected: '" + expectedSize + "' Actual: '" + actualSize + "'";
		Assert.assertTrue(failureDetails, expectedSize == actualSize);
	}
}

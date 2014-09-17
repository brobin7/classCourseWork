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
import edu.uic.cs442.cs442project.database.Transaction;
import edu.uic.cs442.cs442project.database.User;

public class TransactionTests {

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
	public void createZeroTransactions(){
		User u = LoginScreenTests.createNewTestUser(em);
		AddBankAccountTests.generateXBankAccounts(em, u.getUser_id(), 1);
		BankAccount account= Utility.findBankAccountsByUserID(em, u.getUser_id()).get(0);
		
		int expected = 0;
		int actual = account.getTransactionList().size();
		String failureDetails = "Number of transactions is incorrect. Actual : " + actual + " Expected: " + expected;
		Assert.assertTrue(failureDetails, expected==actual);		
	}
	
	@Test
	public void createTransaction(){
		User u = LoginScreenTests.createNewTestUser(em);
		AddBankAccountTests.generateXBankAccounts(em, u.getUser_id(), 1);
		BankAccount account= Utility.findBankAccountsByUserID(em, u.getUser_id()).get(0);
		
		Transaction.generateRandomTransaction(account.getAccount_id(), 1);
		
		
		int expected = 1;
		int actual = account.getTransactionList().size();
		String failureDetails = "Number of transactions is incorrect. Actual : " + actual + " Expected: " + expected;
		Assert.assertTrue(failureDetails, expected==actual);	
	}
	
	@Test
	public void createTwoTransactions(){
		User u = LoginScreenTests.createNewTestUser(em);
		AddBankAccountTests.generateXBankAccounts(em, u.getUser_id(), 1);
		BankAccount account= Utility.findBankAccountsByUserID(em, u.getUser_id()).get(0);
		
		Transaction.generateRandomTransaction(account.getAccount_id(), 1);
		Transaction.generateRandomTransaction(account.getAccount_id(), 2);
		
		int expected = 2;
		int actual = account.getTransactionList().size();
		String failureDetails = "Number of transactions is incorrect. Actual : " + actual + " Expected: " + expected;
		Assert.assertTrue(failureDetails, expected==actual);	
	}
	
	@Test
	public void multipleAccountMultipleTransactionTest(){
		User u = LoginScreenTests.createNewTestUser(em);
		AddBankAccountTests.generateXBankAccounts(em, u.getUser_id(), 2);
		List<BankAccount> accounts = Utility.findBankAccountsByUserID(em, u.getUser_id());
		for(int i = 0; i < 10; i ++){
			Transaction.generateRandomTransaction(accounts.get(0).getAccount_id(), 1);
			Transaction.generateRandomTransaction(accounts.get(1).getAccount_id(), 1);
		}
		
		int expected = 20;
		int actual = UserHomePage.getAllTransactionsForUser(em, u.getUser_id()).size();
		String failureDetails = "Number of transactions is incorrect. Actual : " + actual + " Expected: " + expected;
		Assert.assertTrue(failureDetails, expected==actual);	
		
	}

}

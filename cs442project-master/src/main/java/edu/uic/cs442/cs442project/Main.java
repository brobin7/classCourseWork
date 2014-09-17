package edu.uic.cs442.cs442project;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.uic.cs442.cs442project.database.Bank;
import edu.uic.cs442.cs442project.database.BankAccount;
import edu.uic.cs442.cs442project.database.Merchant;
import edu.uic.cs442.cs442project.database.Transaction;
import edu.uic.cs442.cs442project.database.User;

public class Main {
	/**
	 * Main entry point to bank application.
	 * @param args	command line arguments.
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mySQLdatabase");
		EntityManager em = emf.createEntityManager();

		boolean exit = false;
		
		LoginScreen login = new LoginScreen(em);
		Scanner in = new Scanner(System.in);
		
		while(!exit){
			exit = login.gotoLoginScreen(in);
		}
		
		in.close();
		em.close();
		emf.close();
	}
}


/*
Random rand = new Random();

// Create random bank and add to DB
Bank bank = Bank.generateRandomBank();
em.getTransaction().begin();
em.persist(bank);
em.getTransaction().commit();

// Create some random merchants and add to DB
List<Merchant> merchants = new ArrayList<Merchant>();
for(int i = 0; i < 10; i++) {
	Merchant merchant = Merchant.generateRandomMerchant();
	em.getTransaction().begin();
	em.persist(merchant);
	em.getTransaction().commit();
	merchants.add(merchant);
}

// Create some random users and add to DB
List<User> users = new ArrayList<User>();
for(int i = 0; i < 20; i++) {
	User user = User.generateRandomUser();
	em.getTransaction().begin();
	em.persist(user);
	em.getTransaction().commit();
	users.add(user);
}

// Populate random data for each user
for(User user : users) {
	//List<BankAccount> accounts = new ArrayList<BankAccount>();
	// Create some random accounts and add to DB
	for(int i = 0; i < 5; i++) {
		BankAccount account = BankAccount.generateRandomBankAccount(user.getUser_id(), bank.getBank_id());
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		//accounts.add(account);
		
		// Create some random transactions for each account and add to DB
		for(int j = 0; j < 10; j++) {
			Transaction transaction = Transaction.generateRandomTransaction(account.getAccount_id(),
					merchants.get(rand.nextInt(merchants.size())).getMerchant_id());
			em.getTransaction().begin();
			em.persist(transaction);
			em.getTransaction().commit();
		}
	}
}
*/

// Lists all users and account and transaction sizes
//List<User> users = Utility.findAllUsers(em);
/*for(User u : users) {
	System.out.println("User: " + u.getUsername() + " has: " + u.getBankAccountsList().size() + " bank accounts.");
	for(BankAccount b : u.getBankAccountsList()) {
		System.out.println("BankAccount: " + b.getAccount_name() + " has: " + b.getTransactionList().size() + " transactions.");
	}
}*/

// Make sure we have at least one user
/*
if(users.size() > 1) {
	UserHomePage page = new UserHomePage(em, users.get(0).getUser_id());
	page.gotoUserHomePage();
}
*/
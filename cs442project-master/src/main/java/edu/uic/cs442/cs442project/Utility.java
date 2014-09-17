package edu.uic.cs442.cs442project;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.uic.cs442.cs442project.database.BankAccount;
import edu.uic.cs442.cs442project.database.User;

public class Utility {
	public static final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static Random g_random = new Random();

	/**
	 * Generates a random combination of alpha numeric characters of specified length.
	 * @param len	length of string to generate
	 * @return	randomly generated string of characters
	 */
	public static String generateRandomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for(int i = 0; i < len; i++) 
			sb.append(ALPHA_NUM.charAt(g_random.nextInt(ALPHA_NUM.length())));
		return sb.toString();
	}

	/**
	 * Generates a random number between 0 and limit.
	 * @param limit	the upper bound of random generation
	 * @return	randomly generated integer
	 */
	public static Integer generateRandomNumber(int limit) {
		return g_random.nextInt(limit);
	}
	
	public static List<User> findAllUsers(EntityManager em) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("findAllUsers");
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		em.getTransaction().commit();
		return users;
	}

	public static User findUserByUsername(EntityManager em, String username){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("findUserByUsername");
		q.setParameter("username", username);
		User user = (User) q.getSingleResult();
		em.getTransaction().commit();
		return user;
	}
	
	public static List<BankAccount> findBankAccountsByUserID(EntityManager em, int user_id){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("findBankAccountsByUserId");
		q.setParameter("user_id", user_id);
		@SuppressWarnings("unchecked")
		List<BankAccount> bankaccounts = q.getResultList();
		em.getTransaction().commit();
		return bankaccounts;
	}
	
	public static BankAccount findBankAccountByAccountName(EntityManager em, String account_name){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("findBankAccountByAccountName");
		q.setParameter("account_name", account_name);
		BankAccount account = (BankAccount) q.getSingleResult();
		em.getTransaction().commit();
		return account;
	}
	
	public static Integer getBankIDbyRoutingNumber(EntityManager em, int routingNumber){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("getBankIDByRoutingNumber");
		q.setParameter("routing_number", routingNumber);
		int bank_id = (Integer) q.getSingleResult();
		em.getTransaction().commit();
		return bank_id;
	}
}

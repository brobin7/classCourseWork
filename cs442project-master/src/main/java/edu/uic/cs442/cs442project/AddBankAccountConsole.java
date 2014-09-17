package edu.uic.cs442.cs442project;

import java.util.Scanner;

import javax.persistence.EntityManager;

import edu.uic.cs442.cs442project.database.BankAccount;

public class AddBankAccountConsole {
	
	private EntityManager em;
	private int user_id;
	
	
	public AddBankAccountConsole(EntityManager em, int user_id){
		this.em = em;
		this.user_id = user_id;
	}
	
	public void insertBankAccount(Integer userID, Integer routingNumber, Integer accountNumber, String name, double balance){
		Integer bankID = Utility.getBankIDbyRoutingNumber(em, routingNumber);
		BankAccount b = new BankAccount(userID, bankID, accountNumber, name, balance );
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
	}
	
	public void createNewBankAccount(Scanner in){
		int userID;
		int routingNumber;
		int accountNumber;

		String name;
		userID = user_id;
		System.out.print("Enter the account name: ");
		name = in.next();
		routingNumber = getRoutingNumber(in);
		accountNumber = getAccountNumber(in);	
		
		System.out.println("UserID: " + userID + "\n"
						  +"Routing Number: " + routingNumber + "\n"
						  +"Account Number: " + accountNumber + "\n"
						  +"Account Name: " + name );	

		insertBankAccount(userID, routingNumber, accountNumber, name, (double) 0);	
		
	}
	
	/*
	public int getUserID(Scanner in){
		Integer userID = null;
		while(userID == null){
			System.out.print("Enter the user id: ");
			try{
				userID = in.nextInt();
			}
			catch(Exception e){
				System.out.print("user id must be an integer!");	
				userID = null;
				in.nextLine();
			}
		}
		in.nextLine();
		return userID;
	}
	*/
	
	public int getRoutingNumber(Scanner in){
		Integer routingNumber = null;
		while(routingNumber == null){
			System.out.print("Enter the routing number: ");
			try{
				routingNumber = in.nextInt();
			}
			catch(Exception e){
				System.out.print("routing number must be an integer!");	
				routingNumber = null;
			}
		}
		return routingNumber;
	}
	
	public int getAccountNumber(Scanner in){
		Integer accountNumber = null;
		while(accountNumber == null){
			System.out.print("Enter the account number: ");
			try{
				accountNumber = in.nextInt();
			}
			catch(Exception e){
				System.out.print("account number must be an integer!");	
				accountNumber = null;
			}
		}
		return accountNumber;
	}

}

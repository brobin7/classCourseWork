package edu.uic.cs442.cs442project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import edu.uic.cs442.cs442project.database.BankAccount;
import edu.uic.cs442.cs442project.database.Transaction;

public class UserHomePage {
	// Available commands
	public static final String COMMAND_ALL 		= "all";
	public static final String COMMAND_LINK 	= "link";
	public static final String COMMAND_VIEW 	= "view";
	public static final String COMMAND_UNLINK 	= "unlink";
	public static final String COMMAND_CHANGE 	= "change";
	public static final String COMMAND_LOGOUT 	= "logout";
	public static final String COMMAND_HELP 	= "help";
	public static final String COMMAND_EXIT 	= "exit";
	
	private EntityManager em;
	private Integer user_id;
	
	public UserHomePage(EntityManager em, Integer user_id) {
		this.em = em;
		this.user_id = user_id;
	}

	public void gotoUserHomePage(Scanner in){
		// Show the available commands and accounts first
		displayHelp();
		displayAccounts();
		
		String input = "";
		// Handle user commands
		while(true){
			// Prompt for input
			System.out.print("\nEnter command: ");
			input = in.next();
			if(input.equals(COMMAND_ALL)) {
				displayTransactionsForAllAccounts();
			}
			else if(input.equals(COMMAND_LINK)) {
				AddBankAccountConsole newbankaccount = new AddBankAccountConsole(em,user_id);
				newbankaccount.createNewBankAccount(in);
			}
			else if(input.equals(COMMAND_VIEW)) {
				if(!in.hasNext()) {
					System.out.println("Usage: view <account_name>");
					continue;
				}
				String account_name = in.next();
				displayTransactionsForAccount(account_name);
			}
			else if(input.equals(COMMAND_UNLINK)) {
				// TODO: handle account unlinking(delete account)
			}
			else if(input.equals(COMMAND_CHANGE)) {
				// TODO: handle account name changing
			}
			else if(input.equals(COMMAND_LOGOUT)) {
				in.close();
				return;
			}
			else if(input.equals(COMMAND_HELP)) {
				displayHelp();
			}
			else if(input.equals(COMMAND_EXIT)) {
				break;
			}
			else {
				System.out.println("Error: Unknown command...");
			}
		}
		System.out.println("Session ended...");
		System.out.println("Terminating application...");
		System.exit(0);
	}
	
	/**
	 * Displays user input options.
	 */
	private void displayHelp(){
		System.out.println("\nList of valid commands: \n"
				+ "\tall - Allows you to view all of your transactions across all accounts\n"
				+ "\tlink <account_name> - link a new Bank account\n"
				+ "\tview <account_name> - view the transactions of that account\n"
				+ "\tunlink <account_name> - unlink the account so it will no longer be displayed\n"
				+ "\tchange <old_account_name> <new_account_name> changes the name of the account\n"
				+ "\tlogout - logout of the system\n"
				+ "\thelp - display this message\n"
				+ "\texit - close the program\n");
	}
	
	/**
	 * Displays all available accounts.
	 */
	private void displayAccounts(){
		List<BankAccount> bankAccounts = Utility.findBankAccountsByUserID(em, user_id);
		
		System.out.println("Bank Accounts: ");
		for(BankAccount b : bankAccounts) {
			System.out.println("\tName: " + b.getAccount_name() + " \tBalance: " + b.getBalance());
		}
	}
	
	/**
	 * Displays all transactions on all accounts.
	 */
	private void displayTransactionsForAllAccounts() {		
		
		List<BankAccount> bankAccounts = Utility.findBankAccountsByUserID(em, user_id);
		
		System.out.println("Bank Accounts: ");
		for(BankAccount b : bankAccounts) {
			System.out.println("\tTransactions for account: " + b.getAccount_name());
			List<Transaction> transactions = b.getTransactionList();
			for(Transaction t : transactions) {
				System.out.println("\t\tMerchant: " + t.getMerchant() + "\tDate: " + t.getDate() 
						+ "\tAmount: " + t.getChanges() + "\tBalance: " + t.getBalance());
			}
		}
	}
	
	public static List<Transaction> getAllTransactionsForUser(EntityManager em, int userid){
		List<Transaction> transactions = new ArrayList<Transaction>();
		List<BankAccount> accounts = Utility.findBankAccountsByUserID(em, userid);
		for(BankAccount account : accounts){
			transactions.addAll(account.getTransactionList());
		}
		return transactions;
	}
	
	/**
	 * Displays all transactions for a particular account.
	 * @param account_name	account to display transactions for
	 */
	private void displayTransactionsForAccount(String account_name) {
		BankAccount account = Utility.findBankAccountByAccountName(em, account_name);
		
		System.out.println("Transactions for account: " + account.getAccount_name());
		List<Transaction> transactions = account.getTransactionList();
		for(Transaction t : transactions) {
			System.out.println("\tMerchant: " + t.getMerchant() + "\tDate: " + t.getDate() 
					+ "\tAmount: " + t.getChanges() + "\tBalance: " + t.getBalance());
		}
	}
}

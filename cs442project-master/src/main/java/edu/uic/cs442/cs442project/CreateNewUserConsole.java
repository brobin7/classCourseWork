package edu.uic.cs442.cs442project;

import java.util.Scanner;

import javax.persistence.EntityManager;

import edu.uic.cs442.cs442project.database.User;


public class CreateNewUserConsole {

	private EntityManager em;

	public CreateNewUserConsole(EntityManager em) {
		this.em = em;
	}

	public void insertNewUser(String first, String last, String un, String pw){
		// insert account into database
		em.getTransaction().begin();
		User u  = new User(un, pw, first, last, 0);
		em.persist(u);
		em.getTransaction().commit();
	}

	public void createNewUser(Scanner in){
		String firstName;
		String lastName;
		String userName;
		String password;

		System.out.print("Enter first name: ");
		firstName = in.nextLine();

		System.out.print("Enter last name: ");
		lastName = in.nextLine();

		//TODO Check if the username already exist in the database
		System.out.print("Enter desired username: ");
		userName = in.nextLine();

		while(true){
			System.out.print("Enter password: ");
			password = in.nextLine();
			System.out.print("Re-Enter password: ");
			if(password.equals(in.nextLine())){
				break;
			}
			else{
				System.out.println("Passwords do not match!");
			}
		}

		System.out.println("Account created\n"
				+ "Name: " + firstName + " " + lastName + "\n"
				+ "Username: " + userName + "\n" 
				+ "Password: " + password
				);
		insertNewUser(firstName, lastName, userName, password);
		return;
	}

}

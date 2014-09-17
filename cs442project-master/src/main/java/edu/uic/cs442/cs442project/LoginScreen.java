package edu.uic.cs442.cs442project;

import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import edu.uic.cs442.cs442project.database.User;

public class LoginScreen {

	private EntityManager em;
	public LoginScreen(EntityManager em) {
		this.em = em;
	}

	public User getUser(String username){
		//Check if user exists in DB by searching username
		em.getTransaction().begin();
		Query q = em.createNamedQuery("findUserByUsername");
		q.setParameter("username", username);
		User user = new User();
		try {
			user = (User) q.getSingleResult();
		}
		catch(NoResultException e){
			em.getTransaction().commit();
			return null;
		}
		em.getTransaction().commit();
		return user;
	}
	
	public boolean isCorrectPassword(User u, String pw){
		return u.getPassword().equals(pw);
	}

	public boolean gotoLoginScreen(Scanner in){
		String username;
		String password;
		String response;
		boolean exit = false;

		System.out.println("This is the login Screen");
		System.out.println("login - login into your account");
		System.out.println("new - create a new user account");
		System.out.println("exit - close the program");
		//User can Login, create a new account or exit
		response = in.nextLine();
		if(response.equals("new")){
			CreateNewUserConsole newUser = new CreateNewUserConsole(em);
			newUser.createNewUser(in);
			return exit;
		} 
		if(response.equals("exit")){
			exit = true;
			System.out.println("Session ended...");
			System.out.println("Terminating application...");
			return exit;
		}
		//TODO if statement for login
		//TODO if statement for misc input


		//Get user input for username and password
		System.out.println("Enter username: ");
		username = in.nextLine();
		System.out.print("Enter password: ");
		password = in.nextLine();

		User user = getUser(username);
		if(user == null){
			System.out.println("Invalid username or password");
			return exit;
		}


		/*
		//If the user does not exist then ask if they would want to create a new account
		if (user == null) {
			System.out.println("Username does not exist");
			System.out
					.println("Would you like to create a new account? (yes, no or exit)");
			response = in.nextLine();
			if (response.equals("yes")) {
				CreateNewUserConsole newUser = new CreateNewUserConsole(em);
				newUser.createNewUser();
			}
			if (response.equals("exit")){
				exit = true;
				in.close();
				return exit;
			}
			if (response.equals("no")){
				in.close();
				return exit;
			}
		} 
		 */

		//If user does exists then ask for password
		//If password matches then allow user to continue to UserHomePage
		if (isCorrectPassword(user, password)) {
			UserHomePage userHomePage = new UserHomePage(em,user.getUser_id());
			userHomePage.gotoUserHomePage(in);
		} else{//If password does not match
			System.out.println("Invalid username or password");
			System.out.println("If you forgot password please contact administrator");
		}
		return exit;
	}
}

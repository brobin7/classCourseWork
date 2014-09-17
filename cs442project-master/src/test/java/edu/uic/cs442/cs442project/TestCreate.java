package edu.uic.cs442.cs442project;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uic.cs442.cs442project.database.User;

public class TestCreate {
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
	public void testCreateUser() {
		Scanner in = new Scanner(System.in);
		String username = in.nextLine();
		String password = in.nextLine();
		String firstname = in.nextLine();
		String lastname = in.nextLine();
		int admin = in.nextInt();
		
		User user = new User(username,password,firstname,lastname,admin);
		em.persist(user);
	}
	
	@Test
	public void getalluserbyusername(){
		Query q = em.createNamedQuery("findAllUsers");
		List<User> l = q.getResultList();
		for(User u : l){
			System.out.println(u.toString());
		}
	}
}

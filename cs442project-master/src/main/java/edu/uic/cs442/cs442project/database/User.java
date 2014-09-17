package edu.uic.cs442.cs442project.database;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.uic.cs442.cs442project.Utility;

@NamedQueries({
	@NamedQuery(
			name = "findUserByUsername",
			query = "from User user where user.username = :username"
	),
	@NamedQuery(
			name = "findAllUsers",
			query = "from User"
			)
})

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id",nullable=false,unique=true)
	private Integer user_id;
	@Column(name = "username",nullable=false,unique=true)
	private String username;
	@Column(name = "password",nullable=false)
	private String password;
	@Column(name = "firstname",nullable=false)
	private String firstName;
	@Column(name = "lastname",nullable=false)
	private String lastName;
	@Column(name = "administrator",nullable=false)
	private Integer admin;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<BankAccount> bankAccountsList;
	
	public User(){
	}
	
	public User(String username, String password, String firstName,
			String lastName, Integer admin) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = admin;
	}

	public final Integer getUser_id() {
		return user_id;
	}
	public final void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public final String getFirstName() {
		return firstName;
	}
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public final String getLastName() {
		return lastName;
	}
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public final Integer getAdmin() {
		return admin;
	}
	public final void setAdmin(Integer admin) {
		this.admin = admin;
	}
	public final List<BankAccount> getBankAccountsList() {
		return bankAccountsList;
	}
	public final void setBankAccountsList(List<BankAccount> bankAccountsList) {
		this.bankAccountsList = bankAccountsList;
	}
	@Override
	public String toString() {
		return "User[username: " + this.username + ", password: " + this.password + ", First Name: " + this.firstName + ", Last Name: " + this.lastName + ", is Admin: " + this.admin + "]";
	}
	
	/**
	 * Generates a randomly populated user.
	 * @return	a randomly populated user
	 */
	public static User generateRandomUser() {
		return new User(Utility.generateRandomString(7),
				Utility.generateRandomString(7),
				Utility.generateRandomString(7),
				Utility.generateRandomString(7),
				Utility.generateRandomNumber(2));
	}
}

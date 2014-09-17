package edu.uic.cs442.cs442project.database;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import edu.uic.cs442.cs442project.Utility;

@NamedQueries({
	@NamedQuery(
			name = "findBankAccountsByUserId",
			query = "from BankAccount b where b.user = :user_id"
			),
	@NamedQuery(
			name = "findBankAccountByAccountName",
			query = "from BankAccount b where b.account_name = :account_name"
			)
})

@Entity
@Table(name = "BankAccounts")
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "account_id")
	private Integer account_id;
	@Column(name = "user_id")
	private Integer user;
	@Column(name = "bank_id")
	private Integer bank;
	@Column(name = "account_number")
	private Integer account_number;
	@Column(name = "account_name")
	private String account_name;
	@Column(name = "balance")
	private Double balance;
	@OneToMany(mappedBy = "account_id", fetch = FetchType.LAZY)
	private List<Transaction> transactionList;
	
	public BankAccount(){
	}
	public BankAccount(Integer user_id, Integer bank_id ,Integer account_number, String account_name, Double balance){
		this.user = user_id;
		this.bank = bank_id;
		this.account_number = account_number;
		this.account_name = account_name;
		this.balance = balance;
	}
	
	public final Integer getAccount_number() {
		return account_number;
	}
	public final void setAccount_number(Integer account_number) {
		this.account_number = account_number;
	}
	public final Integer getAccount_id() {
		return account_id;
	}
	public final void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public final Integer getUser() {
		return user;
	}
	public final void setUser(Integer user) {
		this.user = user;
	}
	public final Integer getBank() {
		return bank;
	}
	public final void setBank(Integer bank) {
		this.bank = bank;
	}
	public final String getAccount_name() {
		return account_name;
	}
	public final void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public final Double getBalance() {
		return balance;
	}
	public final void setBalance(Double balance) {
		this.balance = balance;
	}
	public final List<Transaction> getTransactionList() {
		return transactionList;
	}
	public final void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	/**
	 * Generates a randomly populated bank account.
	 * @param user_id	id of user to generate account for
	 * @param bank_id	id of bank to generate account for
	 * @return	a randomly populated bank account
	 */
	public static BankAccount generateRandomBankAccount(Integer user_id, Integer bank_id) {
		return new BankAccount(user_id,
				bank_id,
				Utility.generateRandomNumber(100000),
				Utility.generateRandomString(7),
				(double)Utility.generateRandomNumber(100000));
	}
}

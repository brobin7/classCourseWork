package edu.uic.cs442.cs442project.database;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.uic.cs442.cs442project.Utility;

@Entity
@Table(name = "Transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "transaction_id")
	private Integer transaction_id;
	@Column(name = "account_id")
	private Integer account_id;
	@Column(name = "merchant_id")
	private Integer merchant;
	@Column(name = "transaction_date")
	private Date date;
	@Column(name = "changes")
	private Double changes;
	@Column(name = "balance")
	private Double balance;
	
	public Transaction(){
	}
	
	public Transaction(Integer account_id, Integer merchant, Date date,
			Double changes, Double balance) {
		super();
		this.account_id = account_id;
		this.merchant = merchant;
		this.date = date;
		this.changes = changes;
		this.balance = balance;
	}
	
	public final Integer getTransaction_id() {
		return transaction_id;
	}
	public final void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}
	public final Integer getAccount_id() {
		return account_id;
	}
	public final void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public final Integer getMerchant() {
		return merchant;
	}
	public final void setMerchant(Integer merchant) {
		this.merchant = merchant;
	}
	public final Date getDate() {
		return date;
	}
	public final void setDate(Date date) {
		this.date = date;
	}
	public final Double getChanges() {
		return changes;
	}
	public final void setChanges(Double changes) {
		this.changes = changes;
	}
	public final Double getBalance() {
		return balance;
	}
	public final void setBalance(Double balance) {
		this.balance = balance;
	}
	
	/**
	 * Generates a randomly populated transaction.
	 * @param account_id	id of account to create transaction for
	 * @param merchant_id	id of merchant to create transaction for
	 * @return	a randomly populated transaction.
	 */
	public static Transaction generateRandomTransaction(Integer account_id, Integer merchant_id) {
		return new Transaction(account_id,
				merchant_id,
				new Date(),
				(double)Utility.generateRandomNumber(100),
				(double)Utility.generateRandomNumber(1000000));
	}
}

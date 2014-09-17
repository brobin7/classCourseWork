package edu.uic.cs442.cs442project.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import edu.uic.cs442.cs442project.Utility;

@NamedQueries({
		@NamedQuery(
				name = "getBankIDByRoutingNumber",
				query = "select bank_id from Bank bank where bank.routing_number = :routing_number"
				)
})


@Entity
@Table(name = "Banks")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "bank_id")
	private Integer bank_id;
	@Column(name = "routing_number")
	private Integer routing_number;
	@Column(name = "bank_name")
	private String bank_name;
	
	public Bank() {
	}
	
	public Bank(Integer routing_number, String bank_name) {
		super();
		this.routing_number = routing_number;
		this.bank_name = bank_name;
	}
	
	public final Integer getRouting_number() {
		return routing_number;
	}
	public final void setRouting_number(Integer routing_number) {
		this.routing_number = routing_number;
	}
	public final Integer getBank_id() {
		return bank_id;
	}
	public final void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}
	public final String getBank_name() {
		return bank_name;
	}
	public final void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	/**
	 * Generates a randomly populated bank.
	 * @return	a randomly populated bank
	 */
	public static Bank generateRandomBank() {
		return new Bank(Utility.generateRandomNumber(1000000),
				Utility.generateRandomString(7));
	}
}

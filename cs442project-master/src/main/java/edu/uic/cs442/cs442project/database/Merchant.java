package edu.uic.cs442.cs442project.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.uic.cs442.cs442project.Utility;

@Entity
@Table(name = "Merchants")
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "merchant_id")
	private Integer merchant_id;
	@Column(name = "category")
	private String category;
	@Column(name = "merchant_name")
	private String merchant_name;
	
	public Merchant() {
	}
	
	public Merchant(String category, String merchant_name) {
		super();
		this.category = category;
		this.merchant_name = merchant_name;
	}
	
	public final Integer getMerchant_id() {
		return merchant_id;
	}
	public final void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public final String getCategory() {
		return category;
	}
	public final void setCategory(String category) {
		this.category = category;
	}
	public final String getMerchant_name() {
		return merchant_name;
	}
	public final void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	
	/**
	 * Generates a randomly populated merchant.
	 * @return	a randomly populated merchant
	 */
	public static Merchant generateRandomMerchant() {
		return new Merchant(Utility.generateRandomString(7),
				Utility.generateRandomString(7));
	}
}

package it.polito.latazza.data;

import java.util.*;

/**
 * @author elia
 *
 */

public class Transaction {

	int id;
	Date transactionDate;
	char type;
	int boxQuantity;
	int employeeID;
	int beverageID;
	double amount;
	boolean fromAccount;
	
	public Transaction(int id, Date transactionDate, char type, int boxQuantity, int employeeID, int beverageID,
			double amount, boolean fromAccount) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.type = type;
		this.boxQuantity = boxQuantity;
		this.employeeID = employeeID;
		this.beverageID = beverageID;
		this.amount = amount;
		this.fromAccount = fromAccount;
	}

	public int getId() {
		return id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public char getType() {
		return type;
	}

	public int getBoxQuantity() {
		return boxQuantity;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public int getBeverageID() {
		return beverageID;
	}

	public double getAmount() {
		return amount;
	}

	public boolean isFromAccount() {
		return fromAccount;
	}
	

   
}

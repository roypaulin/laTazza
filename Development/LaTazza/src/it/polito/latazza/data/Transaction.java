package it.polito.latazza.data;

import java.util.*;

/**
 * @author pauli
 *
 */

public class Transaction {
   private Date date;
   char type;
   int beverageId;
   int employeeId;
   float amount;
   boolean fromAccount;
public Date getDate() {
	return date;
}
public Transaction(Date date, char type, int beverageId, int employeeId, float amount, boolean fromAccount) {
	super();
	this.date = date;
	this.type = type;
	this.beverageId = beverageId;
	this.employeeId = employeeId;
	this.amount = amount;
	this.fromAccount = fromAccount;
}
public char getType() {
	return type;
}
public int getBeverageId() {
	return beverageId;
}
public int getEmployeeId() {
	return employeeId;
}
public float getAmount() {
	return amount;
}
public boolean isFromAccount() {
	return fromAccount;
}
   
}

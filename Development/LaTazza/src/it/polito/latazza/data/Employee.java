/**
 * 
 */
package it.polito.latazza.data;

/**
 * @author pauli
 *
 */
public class Employee {
	int id;
	String name;
	String surname;
	double credit;
	
	public Employee(int id, String name, String surname, double d) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.credit = d;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public double getCredit() {
		return credit;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setCredit(double c) {
		this.credit=c;
	}

	public void updateCredit(Integer amountInCents) {
		// TODO Auto-generated method stub
		this.credit+=amountInCents;
	}
	
	
}

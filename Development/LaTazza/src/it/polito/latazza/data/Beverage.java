/**
 * 
 */
package it.polito.latazza.data;

/**
 * @author pauli
 *
 */
public class Beverage {
	int id,quantityAvaiable;
	double boxPrice;
	int capsulePerBox;
	String name;
	public Beverage(int id, int quantityAvaiable, double d, int capsulePerBox, String name) {
		super();
		this.id = id;
		this.quantityAvaiable = quantityAvaiable;
		this.boxPrice = d;
		this.capsulePerBox = capsulePerBox;
		this.name = name;
	}
	
	

}

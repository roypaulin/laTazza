/**
 * 
 */
package it.polito.latazza.data;

/**
 * @author elia
 *
 */
public class Beverage {
	int id,quantityAvaiable;
	double boxPrice;
	int capsulePerBox;
	
	public int getId() {
		return id;
	}
	public int getQuantityAvaiable() {
		return quantityAvaiable;
	}
	public double getBoxPrice() {
		return boxPrice;
	}
	public int getCapsulePerBox() {
		return capsulePerBox;
	}
	public String getName() {
		return name;
	}
	
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

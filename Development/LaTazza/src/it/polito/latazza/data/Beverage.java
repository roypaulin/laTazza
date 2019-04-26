/**
 * 
 */
package it.polito.latazza.data;
import java.sql.SQLException;

import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.data.Database;
/**
 * @author pauli
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
	
}

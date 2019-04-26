/**
 * 
 */
package it.polito.latazza.data;


import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.data.Database;
import it.polito.latazza.exceptions.BeverageException;

/**
 * @author elia
 *
 */
public class Beverage {
	int id,quantityAvailable;
	double boxPrice;
	int capsulePerBox;
	
	public int getId() {
		return id;
	}
	public int getQuantityAvailable() {
		return quantityAvailable;
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
	public Beverage(int id, int quantityAvailable, double boxPrice, int capsulePerBox, String name) {
		super();
		this.id = id;
		this.quantityAvailable = quantityAvailable;
		this.boxPrice = boxPrice;
		this.capsulePerBox = capsulePerBox;
		this.name = name;
	}

    /*The Quantity can be either positive buying capsules or negative selling*/
	public void updateCapsuleQuantity(int quantity)throws BeverageException {
		if((this.quantityAvailable +quantity)< 0) {
			throw new BeverageException() ;
		}
		 this.quantityAvailable += quantity ;
		 return ;
	}

	
	

}

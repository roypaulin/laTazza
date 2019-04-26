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
	/**
	 * @param id
	 * @param name
	 * @param quantiyAvailable
	 * @param capsulePerBox
	 * @param boxPrice
	 */
	public Beverage(int id, String name, int quantiyAvailable, int capsulePerBox, float boxPrice) {
		//super();
		this.id = id;
		this.name = name;
		this.quantiyAvailable = quantiyAvailable;
		this.CapsulePerBox = capsulePerBox;
		this.boxPrice = boxPrice;
	}
	private int id;
	private String name;
	private int quantiyAvailable;// exact number of capsules available
	private int CapsulePerBox; //number of capsules per box
	private float boxPrice; //price of a capsule box
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantiyAvailable() {
		return quantiyAvailable;
	}
	public void setQuantiyAvailable(int quantiyAvailable) {
		this.quantiyAvailable = quantiyAvailable;
	}
	public int getCapsulePerBox() {
		return CapsulePerBox;
	}
	public void setCapsulePerBox(int capsulePerBox) {
		CapsulePerBox = capsulePerBox;
	}
	public float getBoxPrice() {
		return boxPrice;
	}
	public void setBoxPrice(float boxPrice) {
		this.boxPrice = boxPrice;
	}

	
}

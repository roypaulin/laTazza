package it.polito.latazza;

import java.sql.SQLException;

import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.data.Database;
import it.polito.latazza.exceptions.NotEnoughBalance;
//import it.polito.latazza.gui.MainSwing;

public class LaTazza {
	double balance=0;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DataInterface data = new DataImpl();
		//new MainSwing(data);
       //System.console().printf("prova");
		System.out.println("Good Test\n");
		Database database = new Database();
	}

	public void updateBalance(double amount) throws NotEnoughBalance{
		if((this.balance +amount)<0) {
			throw new NotEnoughBalance();
		}
		this.balance += amount;
	}
}

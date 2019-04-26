package it.polito.latazza;

import java.sql.SQLException;

import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.data.Database;
//import it.polito.latazza.gui.MainSwing;

public class LaTazza {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DataInterface data = new DataImpl();
		//new MainSwing(data);
       //System.console().printf("prova");
		System.out.println("Good Test\n");
		Database database = new Database();
	}

}

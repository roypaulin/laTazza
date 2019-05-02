package it.polito.latazza;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.data.Database;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.gui.MainSwing;

public class LaTazza {
	double balance=0;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DataInterface data = new DataImpl();
		new MainSwing(data);
       //System.console().printf("prova");
		Database database = new Database();
		DataImpl di = new DataImpl();
		/*Date date = new Date();
		System.out.println("the actual date is "+date);
		System.out.println("the converted date with / is  "+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
		System.out.println("the converted date with - is  "+ di.convDate(date));*/
		System.out.println(DecimalFormat.getCurrencyInstance(Locale.GERMANY).format( 123.45)) ;
		

		System.out.println(di.convAmountWithCurrency(100.12443)); 
	}

	public void updateBalance(double amount) throws NotEnoughBalance{
		if((this.balance +amount)<0) {
			throw new NotEnoughBalance();
		}
		this.balance += amount;
	}
}

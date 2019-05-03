package it.polito.latazza;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.data.Database;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.gui.MainSwing;

public class LaTazza {
	double balance=0;
	public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception{
		
		DataInterface data = new DataImpl();
		//data.reset();
		data.createEmployee("ndjekoua", "sandjo");
		new MainSwing(data);
       //System.console().printf("prova");
		Database database = new Database();
		DataImpl di = new DataImpl();
		/*Date date = new Date();
		System.out.println("the actual date is "+date);
		System.out.println("the converted date with / is  "+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
		System.out.println("the converted date with - is  "+ di.convDate(date));*/
		System.out.println(DecimalFormat.getCurrencyInstance(Locale.GERMANY).format( 123.45)) ;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1); // number represents number of days
		Date yesterday = cal.getTime();
		cal.add(Calendar.DATE, +2); // number represents number of days
		Date tomorow = cal.getTime();
		System.out.println("Yesterday's date is: " + shiftDate(-1));
		System.out.println("tomorow date is: " + shiftDate(+1));

		 
	}

	public static Date shiftDate(int dayNumber) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, dayNumber); // number represents number of days
		Date shiftedDate = cal.getTime();
		return shiftedDate;
	}
	public void updateBalance(double amount) throws NotEnoughBalance{
		if((this.balance +amount)<0) {
			throw new NotEnoughBalance();
		}
		this.balance += amount;
	}
}

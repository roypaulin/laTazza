package it.polito.latazza.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import it.polito.latazza.data.Beverage;
import it.polito.latazza.data.Database;
import it.polito.latazza.data.Employee;
import it.polito.latazza.data.Transaction;
import it.polito.latazza.exceptions.BeverageException;

public class TestDataImpl {

	@Test
	public void testExample() {
		assertEquals(2, 1 + 1);

	}
	
	public Date getDate(int year,int month,int day) {
		return new GregorianCalendar(year,month-1,day).getTime();
	}
	
	@Test
	public void testDatabase() throws ClassNotFoundException, SQLException, Exception {
		Database database = null;
		
		database = new Database();
		database.truncateTables();
		
		int id = database.addEmployee(new Employee(-1,"Morisio","Maurizio",1000000.99));
		
		assertEquals(id>=0, true);
		
		List<Employee> list = null;
		
		list = database.getListEmployee();
		
		Employee emp = database.getEmployeeData(1);
		assertNotEquals(null, emp);
		
		database.updateBalance(1.1);
		double balance = database.getBalance();
		assertEquals(1.1, balance);
		

		id = database.addBeverage(new Beverage(-1,10,10.1,50,"do you wanna a Kaffè"));
		assertEquals(id>=0, true);
		
		ArrayList<Beverage> bev = (ArrayList<Beverage>) database.getListOfBeverages();
		assertNotEquals(null, bev);
		assertEquals(bev.size()>=1, true);
		
		Beverage bevan = database.getBeverageData(1);
		assertNotEquals(bevan, null);
		
		id = database.registerTransaction(new Transaction(1,getDate(2011, 11, 10),'P',1,1,1, 1.0,true));
		assertEquals(id>=0, true);
		
		try {
			database.updateBeverage(new Beverage(-1,0,1.4,50,"do you want a Kaffè!?!!!"));
		} catch(BeverageException e) {
			System.out.println("works correctly, it launch an exception if id is wrong!");
		}
		
		
		database.updateEmployee(new Employee(1,"Morisio","Maurizio",1.99));
		
		List<Transaction> trans = database.getEmployeeReport(1,getDate(2010, 12, 10),getDate(2011, 12, 10));
		assertEquals(trans.size()>0, true);
		assertEquals(trans.get(0).getTransactionDate().toLocaleString(),getDate(2011, 11, 10).toLocaleString());
		
		trans = database.getReport(getDate(2020, 12, 10),getDate(2031, 12, 10));
		assertEquals(trans.size()==0, true);
		
		assertNotEquals(null, list);

	}

}

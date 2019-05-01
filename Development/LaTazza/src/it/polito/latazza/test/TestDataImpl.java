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
import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.Database;
import it.polito.latazza.data.Employee;
import it.polito.latazza.data.Transaction;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;

public class TestDataImpl {
	/*can be used by other developper, no need to redefine thm again*/
	Database database = new Database();
	DataImpl dataImpl = new DataImpl();
	@Test
	public void testExample() {
		assertEquals(2, 1 + 1);

	}
	
	public Date getDate(int year,int month,int day) {
		return new GregorianCalendar(year,month-1,day).getTime();
	}
	
	@Test
	public void testDatabase() throws ClassNotFoundException, SQLException, Exception {
		
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
	
	@Test
	public void testCreateBeverage() throws Exception{
		dataImpl.reset();
		int id=-1;
		id=dataImpl.createBeverage("coffee",10, 100);
		assertNotEquals(id,-1);
		Beverage bev = database.getBeverageData(id);
		assertEquals("coffee",bev.getName());
		assertEquals(10,bev.getCapsulePerBox());
		assertEquals(100,bev.getBoxPrice());
	}
	
	@Test
	public void testUpdateBeverage() throws Exception {
		dataImpl.reset();
		int id =-1;
		id=dataImpl.createBeverage("Tea",10, 100);
	    Beverage bev = database.getBeverageData(id);
	    bev.setBoxPrice(200);
	    dataImpl.updateBeverage(id, bev.getName(),bev.getCapsulePerBox() , (int)Math.round(bev.getBoxPrice()));
	    //everything is correct so the object should be updated
	    assertEquals(200,database.getBeverageData(id).getBoxPrice());
	    
	    //i put and id that does not exist => i should catch the exception
	  
	    try {
	    	
	    	dataImpl.updateBeverage(-1,"coffee",10,100);
	    	
	    }catch(Exception e) {
	    	System.out.println("thorws correctly the exception");
	    	assertEquals(e instanceof BeverageException,true);
	    }
	    
	    //the price and CapsulesPerBoxt are negative so i should catch a BeverageException
        
	    try {
	    	//database.updateBeverage(bev);
	    	dataImpl.updateBeverage(id, bev.getName(),-10,-2);
	    	
	    }catch(Exception e) {
	    	System.out.println("throws correctly the exception");
	    	assertEquals(e instanceof BeverageException,true); //
	    }
	}
	@Test
	public void testReset() throws Exception {
		
			database.addBeverage(new Beverage(10, 0, 10, 100, "Lemon"));
			database.addEmployee(new Employee(10,"ndjekoua","sandjo",0));
			database.updateBalance(10);
		
		//After calling this function, the databse should be empty
            dataImpl.reset();
		
		    List<Employee> empList = null;
		    List<Beverage> bevList = null;
		    double balance =-1;
	        empList=database.getListEmployee();
		    bevList =database.getListOfBeverages();
		    balance = database.getBalance();
		
		assertEquals(true,bevList.isEmpty());
		assertEquals(true,empList.isEmpty());
		assertEquals(balance,0);
	}
	@Test
	public void testGetBeverageName() throws BeverageException {
		dataImpl.reset();// used to clear everything before starting the Test
		int id=-1;
		id=dataImpl.createBeverage("coffee",10, 100);
		//i get the beverageName of the created string
		String bevName = dataImpl.getBeverageName(id);
		assertEquals(bevName,"coffee");
         
		//this should throw a BeverageException because the Beverage does not exist
		try {
			bevName=dataImpl.getBeverageName(-1);
		}catch(BeverageException be){
			assertEquals(be instanceof BeverageException,true);
		}
	}
	
	@Test
	public void testGetBeverageCapsulesPerBox() throws BeverageException {
		dataImpl.reset();
		int id=-1;
		id=dataImpl.createBeverage("coffee",10, 100);
		int capsulesPerBox = dataImpl.getBeverageCapsulesPerBox(id);
		assertEquals(capsulesPerBox,10);
		
		 
		//this should throw a BeverageException because the Beverage does not exist
		try {
			capsulesPerBox=dataImpl.getBeverageCapsulesPerBox(-1);
		}catch(BeverageException be){
			assertEquals(be instanceof BeverageException,true);
		}
	}

}

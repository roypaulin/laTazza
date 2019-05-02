package it.polito.latazza.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polito.latazza.data.Beverage;
import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.Database;
import it.polito.latazza.data.Employee;
import it.polito.latazza.data.Transaction;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;

public class TestDataImpl {
	/*can be used by other developper, no need to redefine thm again*/
	Database database = new Database();
	DataImpl dataImpl = new DataImpl();
	@Test
	public void testExample() {
		assertEquals(2, 1 + 1);

	}
	
	public Date getDate(int year,int month,int day,int hour,int minute,int sec) {
		LocalDate d = LocalDateTime.of(year, month, day, hour, minute, sec).toLocalDate();
		Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
		date.setHours(hour);
		date.setMinutes(minute);
		date.setSeconds(sec);
		return date;
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
		
		ArrayList<Beverage> bev = (ArrayList<Beverage>) database.getListOfBeverages();
		assertNotEquals(null, bev);
		assertEquals(bev.size()>=1, true);
		
		Beverage bevan = database.getBeverageData(1);
		assertNotEquals(bevan, null);
		
		id = database.registerTransaction(new Transaction(1,getDate(2010, 8, 21, 10, 5, 3),'P',1,1,1,1, 1.0,true));
		assertEquals(id>=0, true);
		
		try {
			database.updateBeverage(new Beverage(-1,0,1.4,50,"do you want a Kaffè!?!!!"));
		} catch(BeverageException e) {
			System.out.println("works correctly, it launch an exception if id is wrong!");
		}
		
		
		database.updateEmployee(new Employee(1,"Morisio","Maurizio",1.99));
		
		List<Transaction> trans = database.getEmployeeReport(1,getDate(2009, 8, 21, 10, 5, 3),getDate(2011, 8, 21, 10, 5, 3));
		assertEquals(trans.size()>0, true);
		assertEquals(trans.get(0).getTransactionDate().toLocaleString(),getDate(2010, 8, 21, 10, 5, 3).toLocaleString());
		
		trans = database.getReport(getDate(2011, 8, 21, 10, 5, 3),getDate(2013, 8, 21, 10, 5, 3));
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
	    	
	    	System.out.println("correctly throws exception for dataImpl.updateBeverage(-1, bev.getName(),10,100) because id is not valid");
	    }
	    
	    //the price and CapsulesPerBox are negative so i should catch a BeverageException
        
	    try {
	    	//database.updateBeverage(bev);
	    	dataImpl.updateBeverage(id, bev.getName(),-10,-2);
	    	
	    }catch(Exception e) {
	    	System.out.println("correctly throws exception for dataImpl.updateBeverage(id, bev.getName(),-10,-2) because boxPrice and price are <0");
	    	//assertEquals(e instanceof BeverageException,true); //
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
			System.out.println("correctly throws exception for dataImpl.getBeverageName(-1) because id is not valid");
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
			//assertEquals(be instanceof BeverageException,true);
			System.out.println("correctly throws exception for dataImpl.getBeverageCapsulesPerBox(-1) because id is not valid");
		}
	}
	
	@Test
	public void getBeverageBoxPrice() throws BeverageException {
		dataImpl.reset();
		int id=-1;
		id=dataImpl.createBeverage("coffee",10, 100);
		Integer boxPrice = dataImpl.getBeverageBoxPrice(id);
		assertEquals(boxPrice,100);
		
		//this should throw a BeverageException because the Beverage does not exist
		try {
			boxPrice=dataImpl.getBeverageBoxPrice(-1);
		}catch(BeverageException be){
			
			System.out.println("correctly throws exception for dataImpl.getBeverageBoxPrice(-1) because id is not valid");
		}
	}
	
	@Test
	public void testGetBeveragesId() throws BeverageException {
		dataImpl.reset();
		List<Integer> expectedList = new ArrayList<>();
		List<Integer> returnList;
		int id,id1,id2;
		id=dataImpl.createBeverage("coffee",10, 100);
		id1=dataImpl.createBeverage("Tea",10, 100);
		id2=dataImpl.createBeverage("Lemon", 20,150);
		expectedList.add(id);
		expectedList.add(id1);
		expectedList.add(id2);
		returnList= dataImpl.getBeveragesId();
		assertEquals(returnList,expectedList);
	}

    @Test
    public void testGetBeverages() throws BeverageException {
    	dataImpl.reset();
    	Map<Integer, String> returnMap = new HashMap<>();
    	Map<Integer, String> expectedMap = new HashMap<>();
    	int id,id1,id2;
		id=dataImpl.createBeverage("coffee",10, 100);
		id1=dataImpl.createBeverage("Tea",10, 100);
		id2=dataImpl.createBeverage("Lemon", 20,150);
		expectedMap.put(id, "coffee");
		expectedMap.put(id1, "Tea");
		expectedMap.put(id2, "Lemon");
		
		returnMap= dataImpl.getBeverages();
		assertEquals(expectedMap,returnMap);
    }
    
    @Test
    public void testGetBeverageCapsules() throws Exception {
    	dataImpl.reset();
    	int id;
		id=dataImpl.createBeverage("coffee",10, 100);
		Beverage bev = database.getBeverageData(id);
		//when we create a beverage, the nitial quantity is always 0
		Integer capsulesQuantity = bev.getQuantityAvailable();
		assertEquals(capsulesQuantity,0);
		
		bev.setQuantityAvailable(10);
		database.updateBeverage(bev);
		capsulesQuantity = bev.getQuantityAvailable();
		assertEquals(capsulesQuantity,10);
    }
    
    @Test
    public void testBuyBoxes() throws Exception {
    	dataImpl.reset();
    	database.updateBalance(500);
    	int id;
		id=dataImpl.createBeverage("coffee",10, 100);
		dataImpl.buyBoxes(id,3);// so i will spend 3*100cent to buy 3*10 capsules
		
		// check laTazza balance have been updated
		double balance = database.getBalance();
		assertEquals(balance,500-300);
		
		// check the Quantity available for this Beverage have been correctly updated
		Integer quantityAvailable = database.getBeverageData(id).getQuantityAvailable();
		assertEquals(quantityAvailable,0+30);
		
		 //check the Transaction have been created: wait untill we define the final format for the date.
		 Date date = new Date();
		   Date date1 = this.getDate(2018, 02, 12, 13,12, 20);
		   //Date date2 = this.getDate(2018, 02, 12, 13,12, 20);
		   List<Transaction> transactionList = database.getReport(date,new Date());
		   //System.out.println("Transaction date "+transactionList.get(0).getTransactionDate());
		   assertEquals(1,transactionList.size());
		//now try to buyBoxes for an identifier which is not valid so i should catch a BeverageException
		       try{
		         	dataImpl.buyBoxes(-1,3);
		       }catch(BeverageException be) {
		    	System.out.println("correctly throws exception for dataImpl.buyBoxes(-1,3) because id is not valid");
			   }
		       
		 // now consider the case i do not have enough balance so i should throw notEnoughBalnceException
		        dataImpl.reset();
		        database.updateBalance(100);
		        id=dataImpl.createBeverage("Tea",10, 100);
		        try {
				dataImpl.buyBoxes(id,3);// so i will spend 3*100cent to buy 3*10 capsules
		        }catch(NotEnoughBalance  e) {
		        	System.out.println("correctly throws exception for dataImpl.buyBoxes(id,3) because there is not enough balance");
		        }
	  }

	
}



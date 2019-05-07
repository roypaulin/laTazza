package it.polito.latazza.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

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
	    	
	    	System.out.println("correctly throws Exception for dataImpl.updateBeverage(-1, bev.getName(),10,100) because id is not valid");
	    }
	    
	    //the price and CapsulesPerBox are negative so i should catch a BeverageException
        
	    try {
	    
	    	dataImpl.updateBeverage(id, bev.getName(),-10,-2);
	    	
	    }catch(Exception e) {
	    	System.out.println("correctly throws Exception for dataImpl.updateBeverage(id, bev.getName(),-10,-2) because boxPrice and price are <0");
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
			System.out.println("correctly throws BeverageException for dataImpl.getBeverageName(-1) because id is not valid");
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
			System.out.println("correctly throws BeverageException for dataImpl.getBeverageCapsulesPerBox(-1) because id is not valid");
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
			
			System.out.println("correctly throws BeverageException for dataImpl.getBeverageBoxPrice(-1) because id is not valid");
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
		
		 //check the Transaction has been created: wait untill we define the final format for the date.
		 Date date = new Date();
		   //Date date1 = this.getDate(2018, 02, 12, 13,12, 20);
		   //Date date2 = this.getDate(2018, 02, 12, 13,12, 20);
		   List<Transaction> transactionList = database.getReport(date,new Date());
		   //System.out.println("Transaction date "+transactionList.get(0).getTransactionDate());
		   assertEquals(1,transactionList.size());
		//now try to buyBoxes for an identifier which is not valid so i should catch a BeverageException
		       try{
		         	dataImpl.buyBoxes(-1,3);
		       }catch(BeverageException be) {
		    	System.out.println("correctly throws BeverageException for dataImpl.buyBoxes(-1,3) because id is not valid");
			   }
		       
		 // now consider the case i do not have enough balance so i should throw notEnoughBalnceException
		        dataImpl.reset();
		        database.updateBalance(100);
		        id=dataImpl.createBeverage("Tea",10, 100);
		        try {
				dataImpl.buyBoxes(id,3);// so i will spend 3*100cent to buy 3*10 capsules
		        }catch(NotEnoughBalance  e) {
		        	System.out.println("correctly throws NotEnoughBalancException for dataImpl.buyBoxes(id,3) because there is not enough balance");
		        }

	  }
    
    @Test
    public void testSellCapsules()
			throws Exception{
    	dataImpl.reset();
    	Date date=new Date();
      int emp1=dataImpl.createEmployee("john","doe");
      int emp2=dataImpl.createEmployee("jane","roberts");
      int bev1=dataImpl.createBeverage("tea", 10, 10);
      //int bev2=dataImpl.createBeverage("lemon", 20, 15);
      
  	  database.updateBalance(400);
      dataImpl.rechargeAccount(emp1, 10);
      
      dataImpl.buyBoxes(bev1, 1);
      dataImpl.sellCapsules(emp1, bev1, 1, true);
      
      // check LaTazza balance
      double balance=database.getBalance();
      assertEquals(balance,400 -10 +10);
      dataImpl.sellCapsules(emp2, bev1, 1, false);
      balance=database.getBalance();
      assertEquals(balance,400 -10 +10 +1);
      
      // check the beverage available quantity
      Beverage bev=database.getBeverageData(bev1);
      int q=bev.getQuantityAvailable();
      assertEquals(q,10-1-1);
      
      //check the employee account
      Employee emp=database.getEmployeeData(emp1);
      double d=emp.getCredit();
      assertEquals(d,10-1);
      
      //check that a transaction has been created for the employee
      List<Transaction> transactionList=database.getReport(shiftDate(-1), shiftDate(1));
      assertEquals(transactionList.size(),4);
      
      //dataImpl.rechargeAccount(emp2, 20);
      //check that the employee account has not been updated given that he pays by cash
      dataImpl.sellCapsules(emp1, bev1, 1, false);
      emp=database.getEmployeeData(emp1);
      d=emp.getCredit();
      assertEquals(d,10-1);
      bev=database.getBeverageData(bev1);
      q=bev.getQuantityAvailable();
      assertEquals(q,10-1-1-1);
      
      //using a non valid beverage id to buy boxes
      try {
    	  dataImpl.buyBoxes(-2, 1);
      }catch(BeverageException e) {
    	  System.out.println("correctly throws exception because Beverage id is not valid");
      }
      
      //using a non valid employee id to recharge account
      try {
    	  dataImpl.rechargeAccount(-1, 10);
      }catch(EmployeeException e) {
    	  System.out.println("correctly throws exception because Employee id is not valid");
      }
      
      //try to sell capsules to an employee with not enough credit
      try {
      dataImpl.sellCapsules(emp2, bev1, 1, true);
      }catch(EmployeeException e) {
    	  System.out.println("correctly throws exception because Employee account does not have enough credit");
      }
      
      //try to sell capsules whose available quantity is not enough
      dataImpl.rechargeAccount(emp2, 20);
      try {
    	  dataImpl.sellCapsules(emp2, bev1, 11, true);
      }catch(NotEnoughCapsules e) {
    	  System.out.println("correctly throws exception because there in not enough capsules for this beverage");
      }
    }
    
    @Test
     public void testsellCapsulesToVisitor() throws Exception {
    	dataImpl.reset();
    	
    	int bev1=dataImpl.createBeverage("tea", 20, 40);
    	database.updateBalance(100);
    	dataImpl.buyBoxes(bev1, 2);
    	dataImpl.sellCapsulesToVisitor(bev1, 2);
    	
    	//check available quantity
    	Beverage bev=database.getBeverageData(bev1);
    	assertEquals(bev.getQuantityAvailable(),38);
    	
    	// check the LaTazza Account
    	assertEquals(database.getBalance(),100-80+4);
    	
    	//check transactions have been created
    	 List<Transaction> transactionList=database.getReport(shiftDate(-1), shiftDate(1));
         assertEquals(transactionList.size(),2);
         
         //try to sell capsules whose available quantity is not enough
         
         try {
       	  dataImpl.sellCapsulesToVisitor(bev1, 40);
         }catch(NotEnoughCapsules e) {
       	  System.out.println("correctly throws exception because there in not enough capsules for this beverage");
         }
    	
    }
	
    @Test
    public void testRechargeAccount() throws Exception{
    	dataImpl.reset();
    	int emp1=dataImpl.createEmployee("john","doe");
    	
    	int credit=dataImpl.rechargeAccount(emp1, 10);
    	
    	//check employee credit
    	Employee emp=database.getEmployeeData(emp1);
    	assertEquals(emp.getCredit(),10);
    	assertEquals(credit,10);
    	//check that transactions hav been created 
        List<Transaction> transactionList=database.getReport(shiftDate(-1), shiftDate(1));
        assertEquals(transactionList.size(),1);
        
        //try recharge account with an invalid employee Id
        try {
        	dataImpl.rechargeAccount(-1, 1);
        }catch(EmployeeException e) {
        	System.out.println("correctly throws exception because Employee id is not valid");
        }
        
    	
    }
	    
    @Test
	public void testCreateEmployee() throws Exception{
		dataImpl.reset();
		int id=-1;
		id=dataImpl.createEmployee("john", "doe");
		assertNotEquals(id,-1);
		Employee emp = database.getEmployeeData(id);
		assertEquals("john",emp.getName());
		assertEquals("doe",emp.getSurname());
		assertEquals(0,emp.getCredit());
		
	}
    
    @Test
	public void testUpdateEmployee() throws Exception {
		dataImpl.reset();
		int id =-1;
		id=dataImpl.createEmployee("john","doe");
	    
	  
	    dataImpl.updateEmployee(id, "jane", "mary");
	    
	    //check if the object has been updated
	    Employee emp = database.getEmployeeData(id);
	    assertEquals("jane",emp.getName());
	    assertEquals("mary",emp.getSurname());
	    
	    // try with an invalid id
         try {
	    	
	    	dataImpl.updateEmployee(-1, "james", "roberts");
	    	
	    }catch(Exception e) {
	    	
	    	System.out.println("correctly throws Exception for dataImpl.updateEmployee(-1, \"james\", \"roberts\"); because id is not valid");
	    }
	   
	}
    
    @Test
	public void testGetEmployeeName() throws EmployeeException {
		dataImpl.reset();// used to clear everything before starting the Test
		int id=-1;
		id=dataImpl.createEmployee("john","doe");
		//i get the name of the created string
		String empName = dataImpl.getEmployeeName(id);
		assertEquals(empName,"john");
         
		// throw an EmployeeException because the id is invalid
		try {
			empName=dataImpl.getEmployeeName(-1);
		}catch(EmployeeException e){
			System.out.println("correctly throws EmployeeException for dataImpl.getEmployeeName(-1) because id is not valid");
		}
	}
    
    @Test
	public void testGetEmployeeSurName() throws EmployeeException {
		dataImpl.reset();// used to clear everything before starting the Test
		int id=-1;
		id=dataImpl.createEmployee("john","doe");
		//i get the Surname of the created string
		String empSurname = dataImpl.getEmployeeSurname(id);
		assertEquals(empSurname,"doe");
         
		// throw an EmployeeException because the id is invalid
		try {
			empSurname=dataImpl.getEmployeeSurname(-1);
		}catch(EmployeeException e){
			System.out.println("correctly throws EmployeeException for dataImpl.getEmployeeSurname(-1) because id is not valid");
		}
	}
    
    @Test
    public void testGetEmployeeBalance() throws EmployeeException {
    	dataImpl.reset();// used to clear everything before starting the Test
		int id=-1;
		id=dataImpl.createEmployee("john","doe");
		
		// check employee balance
		
		assertEquals(0,dataImpl.getEmployeeBalance(id));
		dataImpl.rechargeAccount(id, 10);
		assertEquals(10,dataImpl.getEmployeeBalance(id));
		
		// throw an EmployeeException because the id is invalid
		try {
			dataImpl.getEmployeeBalance(-1);
		}catch(EmployeeException e) {
			System.out.println("correctly throws EmployeeException for dataImpl.getEmployeeBalance(-1) because id is not valid");
		}
    }
    
    @Test
    public void testGetEmployeesId() throws EmployeeException {
    	dataImpl.reset();
		List<Integer> expectedList = new ArrayList<>();
		List<Integer> returnList;
		int id1,id2,id3;
		id1=dataImpl.createEmployee("john","doe");
		id2=dataImpl.createEmployee("chris","paul");
		id3=dataImpl.createEmployee("steven","adams");
		expectedList.add(id1);
		expectedList.add(id2);
		expectedList.add(id3);
		returnList= dataImpl.getEmployeesId();
		assertEquals(returnList,expectedList);
    	
    }
    
    @Test
    public void testGetEmployees() throws EmployeeException {
    	dataImpl.reset();
    	Map<Integer, String> returnMap = new HashMap<>();
    	Map<Integer, String> expectedMap = new HashMap<>();
    	int id1,id2,id3;
    	id1=dataImpl.createEmployee("john","doe");
		id2=dataImpl.createEmployee("chris","paul");
		id3=dataImpl.createEmployee("steven","adams");
		expectedMap.put(id1, "john "+"doe" );
		expectedMap.put(id2, "chris "+"paul");
		expectedMap.put(id3, "steven "+"adams");
		
		returnMap= dataImpl.getEmployees();
		assertEquals(expectedMap,returnMap);
    }
    
    @Test
    public void testGetBalance() throws Exception {
    	assertEquals(dataImpl.getBalance(),0);
    	database.updateBalance(100.50);
    	assertEquals(dataImpl.getBalance(),Math.round((float)100.50));
    }
    @Test
    public void TestGetReport() throws Exception {
    	dataImpl.reset();
    	Date date = new Date();
    	List<String> returnReport = new ArrayList<>();
    	List<String> excpectedReport = new ArrayList<>();
    	List<Transaction> transactionList;
    	String s;
    	int id,empId;
    	database.updateBalance(500);
    	empId= dataImpl.createEmployee("ndjekoua", "sandjo");
    	id=dataImpl.createBeverage("coffee",10, 100);
    	
    	//i make 5 transactions
    	dataImpl.buyBoxes(id,3);// so i will spend 2*100cent to buy 2*10 capsules; Transaction of TYPE= P
    	dataImpl.rechargeAccount(empId,500);// this should create a transaction of TYPE=R
		dataImpl.sellCapsules(empId,id,10,true);//TransactionType=C fromAccount=yes
		dataImpl.sellCapsules(empId,id,10,false);//TransactionType=C fromAccount=flase
		dataImpl.sellCapsulesToVisitor(id,10);//TransactionType=C TO VISITOR
    	
		//get all the 5 transactions from DB
    	transactionList = database.getReport(shiftDate(-1), shiftDate(1));
    	assertEquals(transactionList.size(),5); // to be uncommented after pasty has defined useful methods
    	Collections.sort(transactionList, new sortById());//i order to  be sure that strings are as i expect in order to build expected list
    	
    	//build now the excpected list of strings
    	s=dataImpl.convDate(transactionList.get(0).getTransactionDate())+" BUY"+" coffee"+" 3";
        excpectedReport.add(s);
        s=dataImpl.convDate(transactionList.get(1).getTransactionDate())+" RECHARGE"+" ndjekoua"+" sandjo"+" "+dataImpl.convAmountWithCurrency(500);
        excpectedReport.add(s);
        s=dataImpl.convDate(transactionList.get(2).getTransactionDate())+" BALANCE"+" ndjekoua"+" sandjo"+" coffee"+" "+10;
        excpectedReport.add(s);
        s=dataImpl.convDate(transactionList.get(3).getTransactionDate())+" CASH"+" ndjekoua"+" sandjo"+" coffee"+" "+10;
        excpectedReport.add(s);
        s=dataImpl.convDate(transactionList.get(4).getTransactionDate())+" VISITOR"+" coffee"+" "+10;
        excpectedReport.add(s);//will be uncommented when pasty will terminate methods like RechargeAccoun, sellCapsules, ect....
    	returnReport = dataImpl.getReport(shiftDate(-1), shiftDate(+1));
    	System.out.println("returnreport "+returnReport+"  excpected report"+excpectedReport);
    	assertEquals(excpectedReport,returnReport);
    	
    	//pass wrong dates so i should catch an exception: startDate > endDate
    	
    	try {
    		dataImpl.getReport(shiftDate(-1), shiftDate(1));
    	}catch(DateException de) {
    		System.out.println("correctly throws dateException for dataImpl.getReport(new Date(),date) because startDate> endDate");
    	}
    }
    
    @Test
    public void TestGetReportEmployee() throws Exception {
    	dataImpl.reset();
    	Date date = new Date();
    	List<String> returnReport = new ArrayList<>();
    	List<String> excpectedReport = new ArrayList<>();
    	List<Transaction> transactionList;
    	String s;
    	int id,empId;
    	database.updateBalance(500);
    	empId= dataImpl.createEmployee("ndjekoua", "sandjo");
    	id=dataImpl.createBeverage("coffee",10, 100);
    	
    	//i make 5 transactions
    	dataImpl.buyBoxes(id,3);// so i will spend 2*100cent to buy 2*10 capsules; Transaction of TYPE= P
    	dataImpl.rechargeAccount(empId,500);// this should create a transaction of TYPE=R
		dataImpl.sellCapsules(empId,id,10,true);//TransactionType=C fromAccount=yes
		dataImpl.sellCapsules(empId,id,10,false);//TransactionType=C fromAccount=flase
		dataImpl.sellCapsulesToVisitor(id,10);//TransactionType=C TO VISITOR
    	
		//get all the 5 transactions from DB
    	transactionList = database.getEmployeeReport(empId, shiftDate(-1), shiftDate(1)); 
    	assertEquals(transactionList.size(),3); // to be uncommented after pasty has defined useful methods
    	Collections.sort(transactionList, new sortById());//i order to  be sure that strings are as i expect in order to build expected list
    	
    	//build now the excpected list of strings
    	
        s=dataImpl.convDate(transactionList.get(0).getTransactionDate())+" RECHARGE"+" ndjekoua"+" sandjo"+" "+dataImpl.convAmountWithCurrency(500);
        excpectedReport.add(s);
        s=dataImpl.convDate(transactionList.get(1).getTransactionDate())+" BALANCE"+" ndjekoua"+" sandjo"+" coffee"+" "+10;
        excpectedReport.add(s);
        s=dataImpl.convDate(transactionList.get(2).getTransactionDate())+" CASH"+" ndjekoua"+" sandjo"+" coffee"+" "+10;
        excpectedReport.add(s);
    
    	returnReport = dataImpl.getEmployeeReport(empId,shiftDate(-1),shiftDate(1));
    	System.out.println("returnreport "+returnReport+"  excpected report"+excpectedReport);
    	assertEquals(excpectedReport,returnReport);
    	
    	//pass wrong dates so i should catch an exception: startDate > endDate
    	
    	try {
    		dataImpl.getReport(shiftDate(+1), new Date());
    	}catch(DateException de) {
    		System.out.println("correctly throws DateException for dataImpl.getReport(shiftDate(+1),date) because startDate> endDate");
    	}
    	
          //pass null dates so i should catch an exception: startDate==null
    	
    	try {
    		dataImpl.getReport(null, shiftDate(1));
    	}catch(DateException de) {
    		System.out.println("correctly throws DateException for dataImpl.getReport(null,date) because startDate> endDate");
    	}
    }


	private class sortById implements Comparator<Transaction>{

		@Override
		public int compare(Transaction t1, Transaction t2) {
			// TODO Auto-generated method stub
			return t1.getId() -t2.getId();
		}
		
	}
	/*@param dayNumber can be positive or negati and represent how much we want to shift
	 * eg : shiftDate(-1) return the date of yesterday
	 *       shiftDate(+1) return the date of tomorrow*/
	public static Date shiftDate(int dayNumber) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, dayNumber); // number represents number of days
		Date shiftedDate = cal.getTime();
		return shiftedDate;
	}

}



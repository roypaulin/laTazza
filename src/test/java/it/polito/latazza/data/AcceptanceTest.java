package it.polito.latazza.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.junit.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;





public class AcceptanceTest {

	public AcceptanceTest() {}

	Database database = new Database();
	DataImpl dataImpl = new DataImpl();
	 @Test
	    public void testBuyBoxes() throws Exception {
		 long begin,end;
	    	dataImpl.reset();
	    	System.gc();

	    	database.updateBalance(500);
	    	int id;

	    	System.gc();
begin=System.currentTimeMillis();
			id=dataImpl.createBeverage("coffee",10, 100);
			end=System.currentTimeMillis();
			assertTrue(end-begin < 500);

			System.gc();
begin=System.currentTimeMillis();
			dataImpl.buyBoxes(id,3);// so i will spend 3*100cent to buy 3*10 capsules
			end=System.currentTimeMillis();
			assertTrue(end-begin < 3000);

			// check laTazza balance have been updated
			double balance = database.getBalance();
			assertEquals(balance,500-300,0.0000000001);

			// check the Quantity available for this Beverage has been correctly updated
			Integer quantityAvailable = database.getBeverageData(id).getQuantityAvailable();
			assertEquals(quantityAvailable,0+30,0.0000000001);

			 //check the Transaction has been created
			 //Date date = new Date();
			   List<Transaction> transactionList = database.getReport(shiftDate(-1),new Date());
			   assertEquals(1,transactionList.size());
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
			assertEquals(100,bev.getBoxPrice(),0.0000000001);
		}

	 @Test
	    public void testSellCapsules()
				throws Exception{
		 long begin,end;
	    	dataImpl.reset();
	    	System.gc();
begin=System.currentTimeMillis();
	      int emp1=dataImpl.createEmployee("john","doe");
	      end=System.currentTimeMillis();
	      assertTrue(end-begin < 500);
	      int emp2=dataImpl.createEmployee("jane","roberts");
	      int bev1=dataImpl.createBeverage("tea", 10, 10);
	      //int bev2=dataImpl.createBeverage("lemon", 20, 15);

	  	  database.updateBalance(400);
	  	System.gc();
begin=System.currentTimeMillis();
	      dataImpl.rechargeAccount(emp1, 10);
	      end=System.currentTimeMillis();
	      assertTrue(end-begin < 3000);

	      dataImpl.buyBoxes(bev1, 1);

	      System.gc();
begin=System.currentTimeMillis();
	      dataImpl.sellCapsules(emp1, bev1, 1, true);
	      end=System.currentTimeMillis();
	      assertTrue(end-begin < 3000);
	      // check LaTazza balance
	      double balance=database.getBalance();
	      assertEquals(balance,400 -10 +10,0.0000000001);
	      dataImpl.sellCapsules(emp2, bev1, 1, false);
	      balance=database.getBalance();
	      assertEquals(balance,400 -10 +10 +1,0.0000000001);

	      // check the beverage available quantity
	      Beverage bev=database.getBeverageData(bev1);
	      int q=bev.getQuantityAvailable();
	      assertEquals(q,10-1-1);

	      //check the employee account
	      Employee emp=database.getEmployeeData(emp1);
	      double d=emp.getCredit();
	      assertEquals(d,10-1,0.0000000001);

	      //check that a transaction has been created for the employee
	      List<Transaction> transactionList=database.getReport(shiftDate(-1), shiftDate(1));
	      assertEquals(transactionList.size(),4);

	      //dataImpl.rechargeAccount(emp2, 20);
	      //check that the employee account has not been updated given that he pays by cash
	      dataImpl.sellCapsules(emp1, bev1, 1, false);
	      emp=database.getEmployeeData(emp1);
	      d=emp.getCredit();
	      assertEquals(d,10-1,0.0000000001);
	      bev=database.getBeverageData(bev1);
	      q=bev.getQuantityAvailable();
	      assertEquals(q,10-1-1-1);

	    }

	 @Test
     public void testSellCapsulesToVisitor() throws Exception {
    	dataImpl.reset();
    	long begin,end;
    	int bev1=dataImpl.createBeverage("tea", 20, 40);
    	database.updateBalance(100);
    	dataImpl.buyBoxes(bev1, 2);

    	System.gc();
begin=System.currentTimeMillis();
    	dataImpl.sellCapsulesToVisitor(bev1, 2);
    	end=System.currentTimeMillis();
    	assertTrue(end-begin < 3000);
    	//check available quantity
    	Beverage bev=database.getBeverageData(bev1);
    	assertEquals(bev.getQuantityAvailable(),38);

    	// check the LaTazza Account
    	assertEquals(database.getBalance(),100-80+4,0.0000000001);

    	//check transactions have been created
    	 List<Transaction> transactionList=database.getReport(shiftDate(-1), shiftDate(1));
         assertEquals(transactionList.size(),2);


    }

	   @Test
	    public void testRechargeAccount() throws Exception{
	    	dataImpl.reset();
	    	int emp1=dataImpl.createEmployee("john","doe");

	    	int credit=dataImpl.rechargeAccount(emp1, 10);

	    	//check employee credit
	    	Employee emp=database.getEmployeeData(emp1);
	    	assertEquals(emp.getCredit(),10,0.0000000001);
	    	assertEquals(credit,10);
	    	//check that transactions have been created
	        List<Transaction> transactionList=database.getReport(shiftDate(-1), shiftDate(1));
	        assertEquals(transactionList.size(),1);


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
			assertEquals(0,emp.getCredit(),0.0000000001);

		}

	   @Test
	    public void TestGetReport() throws Exception {
	    	dataImpl.reset();
	    	long begin,end;
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
	        System.gc();
begin=System.currentTimeMillis();
	    	returnReport = dataImpl.getReport(shiftDate(-1), shiftDate(+1));
	    	end=System.currentTimeMillis();
	    	assertTrue(end-begin < 500);
	    	//System.out.println("returnreport "+returnReport+"  excpected report"+excpectedReport);
	    	assertEquals(excpectedReport,returnReport);
	    }

	   @Test
	    public void TestGetReportEmployee() throws Exception {
		   long begin,end;
	    	dataImpl.reset();
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
	        System.gc();
begin=System.currentTimeMillis();
	    	returnReport = dataImpl.getEmployeeReport(empId,shiftDate(-1),shiftDate(1));
	    	end=System.currentTimeMillis();
	    	assertTrue(end-begin < 500);
	    	//System.out.println("returnreport "+returnReport+"  excpected report"+excpectedReport);
	    	assertEquals(excpectedReport,returnReport);
	    }

	   @Test
	    public void testGetBalance() throws Exception {
	      long begin,end;
	    	database.updateBalance(100.50);
	    	  System.gc();
begin=System.currentTimeMillis();
	    	dataImpl.getBalance();
	    	  end=System.currentTimeMillis();
	    	  assertTrue(end-begin < 500);
	    }

	   @Test
	    public void testGetEmployees() throws EmployeeException {
		   long begin,end;
	    	dataImpl.reset();
	    	dataImpl.createEmployee("john","doe");
			dataImpl.createEmployee("chris","paul");
			dataImpl.createEmployee("steven","adams");
			System.gc();
begin=System.currentTimeMillis();
		   dataImpl.getEmployees();
			end=System.currentTimeMillis();
			assertTrue(end-begin <500);

	    }

	   @Test
	    public void testGetEmployeesId() throws EmployeeException {
	    	dataImpl.reset();
			long begin,end;
			dataImpl.createEmployee("john","doe");
			dataImpl.createEmployee("chris","paul");
			dataImpl.createEmployee("steven","adams");
			System.gc();
begin=System.currentTimeMillis();
			dataImpl.getEmployeesId();
			end=System.currentTimeMillis();
	    	assertTrue(end-begin < 500);
	    }


	   @Test
	    public void testGetEmployeeBalance() throws EmployeeException {
	    	dataImpl.reset();// used to clear everything before starting the Test
			int id;
			long begin,end;


			id=dataImpl.createEmployee("john","doe");

			// check employee balance
			dataImpl.rechargeAccount(id, 10);
			System.gc();
begin=System.currentTimeMillis();
			dataImpl.getEmployeeBalance(id);
			end=System.currentTimeMillis();
		    assertTrue(end-begin < 500);
	    }

	   @Test
		public void testGetEmployeeSurName() throws EmployeeException {
		   long begin,end;
			dataImpl.reset();// used to clear everything before starting the Test
			int id;
			id=dataImpl.createEmployee("john","doe");
			//i get the Surname of the created string
			System.gc();
begin=System.currentTimeMillis();
			 dataImpl.getEmployeeSurname(id);
			end=System.currentTimeMillis();

			assertTrue(end-begin < 500);


		}


	   @Test
		public void testGetEmployeeName() throws EmployeeException {
		   long begin,end;
			dataImpl.reset();// used to clear everything before starting the Test
			int id;
			id=dataImpl.createEmployee("john","doe");
			//i get the Surname of the created string
			System.gc();
begin=System.currentTimeMillis();
			 dataImpl.getEmployeeName(id);
			end=System.currentTimeMillis();

			assertTrue(end-begin < 500);


		}

	   @Test
		public void testUpdateEmployee() throws Exception {
		   long begin,end;
			dataImpl.reset();
			int id ;
			id=dataImpl.createEmployee("john","doe");

			System.gc();
begin=System.currentTimeMillis();
		    dataImpl.updateEmployee(id, "jane", "mary");
		    end=System.currentTimeMillis();
             assertTrue(end-begin < 500 );

		}


	   @Test
	    public void testGetBeverageCapsulesSuccess() throws Exception {
		   long begin,end;
	    	dataImpl.reset();
	    	int id;
			id=dataImpl.createBeverage("coffee",10, 100);
			Beverage bev = database.getBeverageData(id);
			bev.setQuantityAvailable(10);
			database.updateBeverage(bev);
			System.gc();
begin=System.currentTimeMillis();
			 dataImpl.getBeverageCapsules(id);
			end=System.currentTimeMillis();
			assertTrue(end-begin < 500);
	    }


	   @Test
	    public void testGetBeverages() throws BeverageException {
	    	dataImpl.reset();
	    	long end,begin;

			dataImpl.createBeverage("coffee",10, 100);
			dataImpl.createBeverage("Tea",10, 100);
			dataImpl.createBeverage("Lemon", 20,150);

			System.gc();
begin=System.currentTimeMillis();
			dataImpl.getBeverages();
			end=System.currentTimeMillis();
			assertTrue(end-begin <500);
	    }


	   @Test
		public void testGetBeveragesId() throws BeverageException {
			dataImpl.reset();
			long begin,end;
			dataImpl.createBeverage("coffee",10, 100);
			dataImpl.createBeverage("Tea",10, 100);
			dataImpl.createBeverage("Lemon", 20,150);
			begin=System.currentTimeMillis();
			 dataImpl.getBeveragesId();
			 end=System.currentTimeMillis();
			assertTrue(end-begin < 500);
		}
	   
	   
	   
	   @Test
		public void getBeverageBoxPrice() throws BeverageException {
			dataImpl.reset();
			int id=-1;
			long begin,end;
			id=dataImpl.createBeverage("coffee",10, 100);
			begin=System.currentTimeMillis();
			dataImpl.getBeverageBoxPrice(id);
			end=System.currentTimeMillis();
			assertTrue(end-begin <500);
		}  
	  
	   
	   @Test
		public void testGetBeverageCapsulesPerBox() throws BeverageException {
			dataImpl.reset();
			int id=-1;
			long begin,end;
			
			id=dataImpl.createBeverage("coffee",10, 100);
			begin=System.currentTimeMillis();
			 dataImpl.getBeverageCapsulesPerBox(id);
			 end=System.currentTimeMillis();
			assertTrue(end-begin < 500);
		}
		
	   
	   @Test
		public void testGetBeverageName() throws BeverageException {
			dataImpl.reset();// used to clear everything before starting the Test
			int id=-1;
			long begin,end;
			
			id=dataImpl.createBeverage("coffee",10, 100);
			
			begin=System.currentTimeMillis();
			dataImpl.getBeverageName(id);
			end=System.currentTimeMillis();
			assertTrue(end-begin < 500);
		}
		 
	   
	   
	   @Test
		public void testUpdateBeverage() throws Exception {
			dataImpl.reset();
			int id =-1;
			long begin,end;
			id=dataImpl.createBeverage("Tea",10, 100);
		    Beverage bev = database.getBeverageData(id);
		    bev.setBoxPrice(200);
		    begin=System.currentTimeMillis();
		    dataImpl.updateBeverage(id, bev.getName(),bev.getCapsulePerBox() , (int)Math.round(bev.getBoxPrice()));
		    end=System.currentTimeMillis();
		    assertTrue(end-begin < 500);
		    //everything is correct so the object should be updated
		    assertEquals(200,database.getBeverageData(id).getBoxPrice(),0.0000000001);
		}
	   
	   
	   
	   
	   private class sortById implements Comparator<Transaction>{

			@Override
			public int compare(Transaction t1, Transaction t2) {
				// TODO Auto-generated method stub
				return t1.getId() -t2.getId();
			}

		}

	   public static Date shiftDate(int dayNumber) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, dayNumber); // number represents number of days
			Date shiftedDate = cal.getTime();
			return shiftedDate;
		}
}

package it.polito.latazza.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import it.polito.latazza.LaTazza;
import it.polito.latazza.data.Beverage;
import it.polito.latazza.data.Database;
import it.polito.latazza.data.Transaction;

public class DataImpl implements DataInterface {
     Database d = new Database();
	@Override
	/* @author roy paulin */
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		
		/*
		 * getEmployeeData()
		 * updatecredit() // same as updateBeveragequantity()
		 * getBevarageData()
		 * getQauntityavailable()
		 * getBevaragePrice()
		 * updateCapsuleQuantity() // try to increment or decrement th capsules's quantity and throws exeptionin case of error
		 * updateBeverageQauntity()// done on the database
		 * if fromAccount==true => updateBalance() from LaTazza on the database
		 * updateEmployeeCredit() // done on the data base
		 * create a new object transaction of type consumption and insert it in the databas
		 * */
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/* @author roy paulin */
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		// TODO Auto-generated method stub
		/*Same as before with the only difference that the transaction that will be created will have some 
		 * attributes set to NULL*/
	}

	@Override
	/* @author roy paulin */
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		// TODO Auto-generated method stub
		return 0;
		/*
		 * getEmployeeData()
		 * updateEmployeeCredit()
		 * create a new object transaction of type recharge and insert it in the database
		 * */
	}

	@Override
	/* @author jean thibaut */
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		// TODO Auto-generated method stub
		/*i first update by increasing the total number of capsules available*/
		Beverage bev ;
		float amount;
		try {
			 bev = d.getBeverageData(beverageId);
			bev.updateCapsuleQuantity(boxQuantity);
			
		}catch(Exception be) {
			
			throw new BeverageException();
		}
		//then i update latazza account
			float boxPrice = (float)bev.getBoxPrice();
			 amount = boxPrice * boxQuantity;
			 float balance;
			 try {
				 balance = (float)d.getBalance();
			 }catch(Exception e){
				 throw new NotEnoughBalance();
			 }
			 //float balance = (float)d.getBalance();
			 if((balance-amount)< 0) {
				 throw new NotEnoughBalance();
			 }
			 
			 try {
					d.updateBeverage(bev);
			 }catch(Exception e) {
				 throw new BeverageException();
			 }
	
       //d.updateBalance(amount);
		//i create the object transaction
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");  
		System.out.println(formatter.format(date));
		Transaction transaction = new Transaction(-1,date,'P',boxQuantity,-1,beverageId,amount,false);
		try {
		d.registerTransaction(transaction);
		}catch(Exception e) {
			System.out.println("Unable to regsiter the transaction");
		}
		return ;
	}

	@Override
	/* @author jean thibaut */
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException {
		// TODO Auto-generated method stub
		/*getEmployeeReport() //which returns a list of transactions to be formated
		 */
		return new ArrayList<String>();
	}

	@Override
	/* @author jean thibaut */
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		// TODO Auto-generated method stub

		/*getReport() //which returns a list of transactions to be formated
		 */
		return new ArrayList<String>();
	}

	@Override
	/* @author jean thibaut */
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		// TODO Auto-generated method stub
		/*call constructor to create a beverage object
		  addBeverage()
		 */
		Integer id=0;
		Beverage b= new Beverage(-1,0,boxPrice,capsulesPerBox,name);
		try{
			id=d.addBeverage(b);
			}catch( Exception e) {
				throw new BeverageException();
			}
		return id;
	}

	@Override
	/* @author jean thibaut */
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		try {
		 Beverage bev = d.getBeverageData(id);
         bev.setName(name);
		 bev.setCapsulePerBox(capsulesPerBox);
		 bev.setBoxPrice(boxPrice);
		 d.updateBeverage(bev);
		}catch(Exception be) {
			throw new BeverageException() ;
			
		}
		// TODO Auto-generated method stub
		/* updateBeverageAttributes
		 */
		
	}

	@Override
	/* @author jean thibaut */
	public String getBeverageName(Integer id) throws BeverageException {
		// TODO Auto-generated method stub
		/*getBeverageData().getName()
		 */
		String name;
		try {
		    name = d.getBeverageData(id).getName();
		}
		catch(Exception be) {
			
			throw new BeverageException() ;
		}
		
		return name;
	}

	@Override
	/* @author jean thibaut */
	public Integer getBeverageCapsulesPerBox(Integer id) throws BeverageException {
		// TODO Auto-generated method stub
		/*getBeverageData().getCapsulesPerBox()
		 */
		Integer capsulesPerBox;
		try {
		   capsulesPerBox = d.getBeverageData(id).getCapsulePerBox();
		}
		catch(Exception be) {
			
			throw new BeverageException() ;
		}
		return capsulesPerBox;
	}

	@Override
	/* @author jean thibaut */
	public Integer getBeverageBoxPrice(Integer id) throws BeverageException {
		// TODO Auto-generated method stub
		/*getBeverageData().getBeverageBoxPrice()
		 */
		float price;
		try {
		    price = (float)d.getBeverageData(id).getBoxPrice();
		}
		catch(Exception be) {
			
			throw new BeverageException() ;
		}
		return Math.round(price);
	}

	@Override
	/* @author jean thibaut */
	public List<Integer> getBeveragesId() {
		// TODO Auto-generated method stub
		/*getBeverageData().getId()
		 */
		List<Integer> beveragesId = new ArrayList<>();
		List<Beverage> beverages = new ArrayList<>();
		try{
			beverages = d.getListOfBeverages();
		}
		catch(Exception e){
			System.out.println("canno get the list of beverages");
		}
		for(Beverage b : beverages) {
			beveragesId.add(b.getId());
		}
		return beveragesId;
	}

	@Override
	/* @author jean thibaut */
	public Map<Integer, String> getBeverages() {
		// TODO Auto-generated method stub
		/*getListOfBeverage() //then transforms in Map
		 */
		Map<Integer, String> mapBeverages = new HashMap<>();
		List<Beverage> beverages = new ArrayList<>();
		try{
			beverages = d.getListOfBeverages();
		}catch(Exception e) {
			
		}
		for(Beverage b : beverages) {
			mapBeverages.put(b.getId(),b.getName());
		}
		return mapBeverages;
	}

	@Override
	/* @author jean thibaut */
	public Integer getBeverageCapsules(Integer id) throws BeverageException {
		// TODO Auto-generated method stub
		/*getBeverageData().getCapsulesAvailable()
		 */
		 
		Integer quantityAvailable;	
		 try {
			   quantityAvailable = d.getBeverageData(id).getQuantityAvailable();
			}
			catch(Exception e) {
				
				throw new BeverageException() ;
			}
		return quantityAvailable;
	}

	@Override
	/* @author roy paulin */
	public Integer createEmployee(String name, String surname) throws EmployeeException {
		// TODO Auto-generated method stub
		/*call constructor to create a employee object
		addEmployee()
		 */
		return 0;
	}

	@Override
	/* @author roy paulin */
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		// TODO Auto-generated method stub
		/* updateEmployeeAttributes
		 */
	}

	@Override
	/* @author roy paulin */
	public String getEmployeeName(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
		/*getEmployeeData().getName()
		 */
		return "";
	}

	@Override
	/* @author roy paulin */
	public String getEmployeeSurname(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
		/*getEmployeeData().getSurname()
		 */
		return "";
	}

	@Override
	/* @author roy paulin */
	public Integer getEmployeeBalance(Integer id) throws EmployeeException {
		// TODO Auto-generated method stub
	   /*getEmployeeData().getEmployeecredit()
		 */
		return 0;
	}

	@Override
	/* @author roy paulin */
	public List<Integer> getEmployeesId() {
		// TODO Auto-generated method stub
		  /*getEmployeeData().getEmployeeID()
		 */
		return new ArrayList<Integer>();
	}

	@Override
	/* @author roy paulin */
	public Map<Integer, String> getEmployees() {
		// TODO Auto-generated method stub
		/*getListOfEmployee() //then transforms in Map
		 */
		return new HashMap<Integer, String>();
	}

	@Override
	/* @author roy paulin */
	public Integer getBalance() {
		// TODO Auto-generated method stub
		// getBalance()
		return 0;
	}

	@Override
	/* @author jean thibaut */
	public void reset() {
		// TODO Auto-generated method stub
		/*truncateTables()
		 */
		
	}

}

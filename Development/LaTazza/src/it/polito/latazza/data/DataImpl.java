package it.polito.latazza.data;

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
		/*
		  getBeverageData()
		  getBalance()
		  updateBalance()
		  updateBeverageQuantity()
		*/ 
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
		Beverage b= new Beverage(-1,name,0,capsulesPerBox,boxPrice);
		/*try{
			id=d.addBeverage(b);
			}catch(BeverageException be) {
				throw new BeverageException();
			}*/
		
		return id;
	}

	@Override
	/* @author jean thibaut */
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		/*try {
			d.updateBevarageAttributes(id,name,capsulesPerbox,boxPrice);
		}catch(BeverageException be) {
			throw new BeverageException() ;
			
		}*/
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
		
		/*try {
		   String name = d.getBeverageData(id).getName();
		}
		catch(BeverageException be) {
			
			throw new BeverageException() ;
		}*/
		
		return "";
	}

	@Override
	/* @author jean thibaut */
	public Integer getBeverageCapsulesPerBox(Integer id) throws BeverageException {
		// TODO Auto-generated method stub
		/*getBeverageData().getCapsulesPerBox()
		 */
		
		/*try {
		   Integer = d.getBeverageData(id).getCapsulePerBox();
		}
		catch(BeverageException be) {
			
			throw new BeverageException() ;
		}*/
		return 0;
	}

	@Override
	/* @author jean thibaut */
	public Integer getBeverageBoxPrice(Integer id) throws BeverageException {
		// TODO Auto-generated method stub
		/*getBeverageData().getBeverageBoxPrice()
		 */
		/*try {
		   Integerm price = d.getBeverageData(id).getBoxPrice();
		}
		catch(BeverageException be) {
			
			throw new BeverageException() ;
		}*/
		return 0;
	}

	@Override
	/* @author jean thibaut */
	public List<Integer> getBeveragesId() {
		// TODO Auto-generated method stub
		/*getBeverageData().getId()
		 */
		List<Integer> beveragesId = new ArrayList<>();
		List<Beverage> beverages = new ArrayList<>();
		//beverages = d.getListOfBeverages();
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
		//beverages = d.getListOfBeverages();
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
		 
			
		 /*try {
			   Integer quantityAvailable = d.getBeverageData(id).getQuantityAvailable();
			}
			catch(BeverageException be) {
				
				throw new BeverageException() ;
			}*/
		return 0;
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

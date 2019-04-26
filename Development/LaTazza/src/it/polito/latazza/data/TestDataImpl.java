package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TestDataImpl {

	@Test
	public void testExample() {
		assertEquals(2, 1 + 1);

	}
	
	@Test
	public void testDatabase() throws ClassNotFoundException, SQLException, Exception {
		
		Database database = null;
		
		database = new Database();
		database.truncateTables();
		
		database.addEmployee(new Employee(-1,"Morisio","Maurizio",1000000.99));
		
		assertNotEquals(null, database);
		
		List<Employee> list = null;

		try {
			list = database.getListEmployee();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		database.getEmployeeData(1);
		database.updateCredit(1,1.1);
		database.getListOfBeverage();
		database.getBeverageData();
		database.getBalance();
		database.getBalance();
		database.getEmployeeReport(1,new Date(12, 10, 2010),new Date(12, 10, 2011));
		database.getReport(new Date(12, 10, 2010),new Date(12, 10, 2011));
		database.addBeverage(new Beverage(-1,10,10.1,50,"do you wanna a Kaffè"));
		database.registerTransaction(new Transaction(1, new Date(12, 10, 2011),'P',1,1,1, 1.0,true));
		database.truncateTables();
		database.updateBeverageAttributes(new Beverage(-1,0,1.4,50,"do you wanna a Kaffè!?!!!"));
		database.updateEmployeeAttributes(new Employee(-1,"Morisio","Maurizio",1.99));
		

		assertNotEquals(null, list);

	}

}

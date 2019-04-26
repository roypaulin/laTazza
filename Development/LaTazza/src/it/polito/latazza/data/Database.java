/**
 * 
 */
package it.polito.latazza.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author elia
 *
 */

public class Database {
	
	Connection connection = null;
	
	public Database() {
	}
	
	public void connect() throws ClassNotFoundException, SQLException,Exception {
		if (connection != null) {
			String msg = "Exception, called connect on a non void connect object";
			throw new Exception(msg);
		}
			
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:./db/db_se");
		connection.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
		connection = null;
	}
	
	public List<Employee> getListEmployee() throws SQLException{
		List<Employee> returnList = new LinkedList<Employee>();
		
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM Employee";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			float credit = rs.getFloat("credit");
			
			returnList.add(new Employee(id,name,surname,credit));
			
		}
		
		rs.close();
		stmt.close();
		
		return returnList;
	}
	
	
	/* for security purpose */
	@Override
	protected void finalize() throws Throwable {
		connection.close();
		connection = null;
		super.finalize();
	}

	public void getEmployeeData(int i) {
		
	}

	public void updateCredit(int i, double d) {
		// TODO Auto-generated method stub
		
	}

	public void getListOfBeverage() {
		// TODO Auto-generated method stub
		
	}

	public void getBeverageData() {
		// TODO Auto-generated method stub
		
	}

	public void updateBeverageQuantity() {
		// TODO Auto-generated method stub
		
	}

	public void getBalance() {
		// TODO Auto-generated method stub
		
	}

	public void getEmployeeReport(int i, Date date, Date date2) {
		// TODO Auto-generated method stub
		
	}

	public void getReport(Date date, Date date2) {
		// TODO Auto-generated method stub
		
	}

	public void registerTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}

	public void addBeverage(Beverage beverage) {
		// TODO Auto-generated method stub
		
	}

	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	public void truncateTables() {
		// TODO Auto-generated method stub
		
	}

	public void updateBeverageAttributes(Beverage beverage) {
		// TODO Auto-generated method stub
		
	}

	public void updateEmployeeAttributes(Employee employee) {
		// TODO Auto-generated method stub
		
	}
	
}
/*
 * USED QUERY
 insert into Employee values (null,"Franco", "Ruggieriii", 0)
 
*/
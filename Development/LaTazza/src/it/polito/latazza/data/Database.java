/**
 * 
 */
package it.polito.latazza.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;

/**
 * @author elia
 *
 */

public class Database {
	
	Connection connection = null;
	
	private void connect() throws Exception {
		try {
			if (connection != null) {
				String msg = "Exception, called connect on a non void connect object";
				throw new Exception(msg);
			}
				
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:./db/db_se");
			connection.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch(SQLException | ClassNotFoundException e) {
			System.err.println("erroreeeeee");
			throw new Exception();
		}
	}
	
	private void closeConnection() throws Exception {
		connection.close();
		connection = null;
		System.out.println("Database connection closed.");
	}
	
	public List<Employee> getListEmployee() throws Exception{
		connect();
		
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
		
		closeConnection();
		
		return returnList;
	}
	
	
	/* for security purpose */
	@Override
	protected void finalize() throws Throwable {
		connection.close();
		connection = null;
		super.finalize();
	}

	public Employee getEmployeeData(int i) throws Exception {
		connect();
		
		String sql = "SELECT * FROM Employee WHERE id = ?";
		
		PreparedStatement prep = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, i);
		ResultSet rs = prep.executeQuery();
		
		int id = 0;
		String name = null,surname = null;
		float credit = 0;
		boolean result = false;
		
		while (rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name");
			surname = rs.getString("surname");
			credit = rs.getFloat("credit");
			result = true;
		}
		
		closeConnection();
		
		if (!result)
			throw new EmployeeException();
		
		return new Employee(id,name,surname,credit);
	}

	public void updateCredit(int i, double amount) {
		// TODO Auto-generated method stub
		
	}

	public List<Beverage> getListOfBeverages() {
		// TODO Auto-generated method stub
		return new ArrayList<Beverage>();
	}

	public Beverage getBeverageData(Integer id)throws BeverageException {
		// TODO Auto-generated method stub
		return null;
	}

	public double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void getEmployeeReport(int i, Date date, Date date2) {
		// TODO Auto-generated method stub
		
	}

	public void getReport(Date date, Date date2) {
		// TODO Auto-generated method stub
		
	}

	public int registerTransaction(Transaction transaction) throws Exception {
		// TODO Auto-generated method stub
		connect();
		
		int last_inserted_id = -1;
		
		String sql = "INSERT INTO Transactions VALUES (NULL,?,?,?,?,?,?,?);";
		
		PreparedStatement prep = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		String dat = new SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH).format(transaction.getTransactionDate());
		prep.setString(1, dat);
		prep.setString(2, String.valueOf(transaction.getType()));
		prep.setInt(3, transaction.getBoxQuantity());
		prep.setInt(4, transaction.getEmployeeID());
		prep.setInt(5, transaction.getBeverageID());
		prep.setDouble(6, transaction.getAmount());
		prep.setBoolean(7, transaction.isFromAccount());
		
		/**
		 * translated into:
		 *  
		int is_from_account = (transaction.isFromAccount())? 1 : 0;
		prep.setInt(7, is_from_account);
		 *
		 */
		
		prep.executeUpdate();
		
		ResultSet rs = prep.getGeneratedKeys();
        if(rs.next())
        {
            last_inserted_id = rs.getInt(1);
        }
		
		prep.close();
		
		closeConnection();
		
		return last_inserted_id;
	}

	public Integer addBeverage(Beverage beverage) throws Exception {
		connect();
		
		String sql = "INSERT INTO Beverage VALUES (NULL,?,?,?,?);";
		
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.setInt(1, beverage.getQuantityAvailable());
		prep.setDouble(2, beverage.getBoxPrice());
		prep.setInt(3, beverage.getCapsulePerBox());
		prep.setString(4, beverage.getName());
		prep.executeUpdate();
		
		prep.close();
		
		closeConnection();
		return 0;
	}

	public void addEmployee(Employee employee) throws Exception {
		connect();
		
		String sql = "INSERT INTO Employee VALUES (NULL,?,?,?);";
		
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.setString(1, employee.getName());
		prep.setString(2, employee.getSurname());
		prep.setDouble(3, employee.getCredit());
		prep.executeUpdate();
		
		prep.close();
		
		closeConnection();
	}

	public void truncateTables() throws Exception {
		// TODO Auto-generated method stub
		String sql_create_1 = "CREATE TABLE IF NOT EXISTS `Transactions` (\n" + 
				"	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" + 
				"	`transactionDate`	TEXT NOT NULL CHECK(date ( transactionDate ) IS NOT NULL),\n" + 
				"	`type`	CHAR NOT NULL CHECK(type = 'R' OR type = 'C' OR type = 'P'),\n" + 
				"	`boxQuantity`	INTEGER NOT NULL,\n" + 
				"	`employeeID`	INTEGER NOT NULL,\n" + 
				"	`beverageID`	INTEGER NOT NULL,\n" + 
				"	`amount`	REAL NOT NULL,\n" + 
				"	`fromAccount`	NUMERIC NOT NULL CHECK(fromAccount = 0 OR fromAccount = 1)\n" + 
				");";
				
		String sql_create_2 = "CREATE TABLE IF NOT EXISTS `Employee` (\n" + 
				"	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,\n" + 
				"	`name`	TEXT NOT NULL,\n" + 
				"	`surname`	TEXT NOT NULL,\n" + 
				"	`credit`	REAL NOT NULL DEFAULT 0 CHECK(credit >= 0)\n" + 
				");";
	    String sql_create_3 = "CREATE TABLE IF NOT EXISTS `Beverage` (\n" + 
				"	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" + 
				"	`quantityAvaiable`	INTEGER NOT NULL DEFAULT 0 CHECK(quantityAvaiable >= 0),\n" + 
				"	`price`	REAL NOT NULL CHECK(price > 0),\n" + 
				"	`capsulePerBox`	INTEGER NOT NULL CHECK(capsulePerBox > 0),\n" + 
				"	`name`	TEXT NOT NULL UNIQUE\n" + 
				");";
		
		String sqlDelete_1 = "drop table IF EXISTS `Transactions`;";
		String sqlDelete_2 = "drop table IF EXISTS `Employee`;";
		String sqlDelete_3 = "drop table IF EXISTS `Beverage`;";
		
		connect();

		Statement stmt_drop_tables = connection.createStatement();
		stmt_drop_tables.addBatch(sqlDelete_1);
		stmt_drop_tables.addBatch(sqlDelete_2);
		stmt_drop_tables.addBatch(sqlDelete_3);
		stmt_drop_tables.executeBatch();
		
		Statement stmt_create_tables = connection.createStatement();
		stmt_create_tables.addBatch(sql_create_1);
		stmt_create_tables.addBatch(sql_create_2);
		stmt_create_tables.addBatch(sql_create_3);
		stmt_create_tables.executeBatch();
		
		
		closeConnection();
		
	}

	public void updateBeverage(Beverage beverage)throws BeverageException {
		// TODO Auto-generated method stub
		
	}

	public void updateEmployee(Employee employee)throws EmployeeException {
		// TODO Auto-generated method stub
		
	}
	
	public void updateBalance(double amount) {
		
	}
}
/*
 * USED QUERY
 insert into Employee values (null,"Franco", "Ruggieriii", 0)
 
*/
package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
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
		
		assertNotEquals(null, database);

		database.connect();
		
		List<Employee> list = null;

		try {
			list = database.getListEmployee();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		assertNotEquals(null, list);

	}

}

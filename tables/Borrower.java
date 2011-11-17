package tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Borrower implements Table {

	private Integer bid;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String emailAddress;
	private Integer sinOrStNum;
	private Calendar expiryDate;
	private Integer bookTimeLimit;
	
	Connection con;
	Statement stmt;
	
	@Override
	public void display(String[][] arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert() {
		try {
			stmt.executeUpdate("INSERT INTO Borrower VALUES (" + bid + ", " + password + ", " +
			name + ", " + address + ", " + phone + ", " + emailAddress + ", " +	sinOrStNum + 
			", " +expiryDate + ", " +bookTimeLimit + ")");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Table getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table get() {
		// TODO Auto-generated method stub
		return null;
	}

}

package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Borrowing implements Table {

	private Integer borid;
	private Calendar outDate;
	private Calendar inDate;
	private BookCopy bc;
	private Borrower borr;
	
	Connection con;
	Statement stmt;
	
	public Calendar getDueDate() {
		return null;
	}
	
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
			stmt.executeUpdate("INSERT INTO Borrowing VALUES (" + borid + ", " + outDate + ", " +
			inDate + ", " + bc + ", " + borr + ")");
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

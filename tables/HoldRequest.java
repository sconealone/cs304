package tables;

import java.util.Calendar;

public class HoldRequest implements Table {

	private Integer hid;
	private Calendar issueDate;
	private Book b;
	private Borrower borr;
	
	@Override
	public void display(String[][] arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ndate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		return false;
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

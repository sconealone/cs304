package tables;

import java.util.Calendar;

public class Fine implements Table {

	private Integer fid;
	private Integer amount;
	private Calendar issueDate;
	private Calendar paidDate;
	private Borrowing b;
	
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

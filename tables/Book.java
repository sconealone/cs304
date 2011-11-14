package tables;

import java.util.Collection;

public class Book implements Table {

	private String callNumber;
	private String isbn;
	private String title;
	private String mainAuthor;
	private String publisher;
	private Integer year;
	private Collection<String> authors;
	private Collection<String> subjects;	
	
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

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
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
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
	public Collection<Table> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table get() {
		// TODO Auto-generated method stub
		return null;
	}

}

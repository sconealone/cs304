package tables;

import java.util.Collection;

import users.Conn;

public class Book implements Table {

	private String callNumber;
	private String isbn;
	private String title;
	private String mainAuthor;
	private String publisher;
	private Integer year;
	private Collection<String> authors;
	private Collection<String> subjects;	

	private Conn c = Conn.getInstance();

	//empty constructor for Book
	//should connection singleton be "referenced" as part of the class or after each new object is instantiated. It would 
	//refer to the same object right?
	public Book(){

	};


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
	/**
	 * PRE: Assume there is a table in the database called Book
	 */
	public boolean insert(String cN, String i, String t, String mA, String p, int year) throws SQLException{

		try{
			PreparedStatement ps = c.getConnection().prepareStatement ("INSERT INTO Book " +
					"(callNumber, isbn, title, mainAuthor,publisher,year) VALUES (?, ?, ?,?,?,?)"); 

			setString(1, cN);
			setString(2, i);
			setString(3, t);
			setString(4, mA);
			setString(5, p);
			setInt(6, year);
			ps.executeUpdate(); 
			return true;

		}
		catch SQLException e{
			//handle exception
			System.out.print(e);
			return false;
		}
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

	/**
	 * @return the callNumber
	 */
	public String getCallNumber() {
		return callNumber;
	}

	/**
	 * @param callNumber the callNumber to set
	 */
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the mainAuthor
	 */
	public String getMainAuthor() {
		return mainAuthor;
	}

	/**
	 * @param mainAuthor the mainAuthor to set
	 */
	public void setMainAuthor(String mainAuthor) {
		this.mainAuthor = mainAuthor;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the authors
	 */
	public Collection<String> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(Collection<String> authors) {
		this.authors = authors;
	}

	/**
	 * @return the subjects
	 */
	public Collection<String> getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(Collection<String> subjects) {
		this.subjects = subjects;
	}

}

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

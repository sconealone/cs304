package tables;

import java.util.Calendar;
import java.util.Collection;

public class HoldRequest implements Table {

	private Integer hid;
	private Calendar issueDate;
	private Book b;
	private Borrower borr;
	
	
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
   * @return the hid
   */
  public Integer getHid() {
    return hid;
  }

  /**
   * @param hid the hid to set
   */
  public void setHid(Integer hid) {
    this.hid = hid;
  }

  /**
   * @return the issueDate
   */
  public Calendar getIssueDate() {
    return issueDate;
  }

  /**
   * @param issueDate the issueDate to set
   */
  public void setIssueDate(Calendar issueDate) {
    this.issueDate = issueDate;
  }

  /**
   * @return the b
   */
  public Book getB() {
    return b;
  }

  /**
   * @param b the b to set
   */
  public void setB(Book b) {
    this.b = b;
  }

  /**
   * @return the borr
   */
  public Borrower getBorr() {
    return borr;
  }

  /**
   * @param borr the borr to set
   */
  public void setBorr(Borrower borr) {
    this.borr = borr;
  }

}

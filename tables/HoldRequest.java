package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;

public class HoldRequest implements Table {

	private Integer hid;
	private Calendar issueDate;
	private Book b;
	private Borrower borr;
	
	private Connection c;
	private Statement stmt;
	
	public HoldRequest() {
		c = Conn.getInstance().getConnection();
	}
	
	public HoldRequest(Integer hid, Calendar issueDate, Book b, Borrower borr) {
		this.hid = hid;
		this.issueDate = issueDate;
		this.b = b;
		this.borr = borr;
		
		c = Conn.getInstance().getConnection();
	}
	
	@Override
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public void update() throws SQLException {
		PreparedStatement  ps;
		
		ps = c.prepareStatement("UPDATE holdRequest SET bid = ?, issueDate = ?, callNo = ? WHERE hid = ?");
				  
		ps.setInt(4, hid);
		ps.setInt(1, borr.getBid());
		ps.setString(2, issueDate.toString());
		ps.setString(3, b.getCallNumber());
		
		int rowCount = ps.executeUpdate();
		if (rowCount == 0) 
			//Throw Exception
		
		c.commit();
		ps.close();
	}

	@Override
	public boolean delete() throws SQLException {
		PreparedStatement  ps;
		
		ps = c.prepareStatement("DELETE FROM bookCopy WHERE hid = ?");
		ps.setInt(1, hid);
		
		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
		    //throw exception
		}

		c.commit();
		ps.close();
	return false;
	}

	@Override
	public boolean insert() throws SQLException {
		PreparedStatement  ps;
		
		ps = c.prepareStatement("INSERT INTO holdRequest VALUES (?,?,?,?)");
	
		ps.setInt(1, hid);
		ps.setInt(2, borr.getBid());
		ps.setString(3, issueDate.toString());
		ps.setString(4, b.getCallNumber());
		c.commit();
		ps.close();
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

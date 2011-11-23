package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import users.Conn;

/**
 * This class represents the HoldRequest table in the database.
 * 
 * @author Christiaan Fernando
 * 
 */
public class HoldRequest implements Table {

	private Integer hid;
	private Calendar issueDate;
	private Book b;
	private Borrower borr;

	private Connection c;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Default Constructor.
	 */
	public HoldRequest() {
		c = Conn.getInstance().getConnection();
	}

	/**
	 * HoldRequest Constructor.
	 * 
	 * This constructor takes in a Calendar, Book, Borrower and creates a
	 * HoldRequest Object. This object has not been added to the SQL table yet,
	 * so insert() will need to be called on this object in the future if it
	 * needs to be added.
	 * 
	 * @param issueDate
	 *            Issue Date for the HoldRequest
	 * @param b
	 *            Book for the HoldRequest
	 * @param borr
	 *            Borrower for the HoldRequest
	 */
	public HoldRequest(Calendar issueDate, Book b, Borrower borr) {
		this.issueDate = issueDate;
		this.b = b;
		this.borr = borr;

		c = Conn.getInstance().getConnection();
	}

	/**
	 * HoldRequest Constructor.
	 * 
	 * This constructor takes in an Integer and find the HoldRequest entry in
	 * the SQL table with this hid. This assumes that the entry already exists.
	 * If it does not, this will call the default constructor.
	 * 
	 * @param hid
	 *            HoldRequest id in the SQL table.
	 * @throws SQLException
	 */
	public HoldRequest(Integer hid) throws SQLException {
		ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE hid = ?");
		ps.setInt(1, hid);

		rs = ps.executeQuery();

		if (rs.next()) {
			this.hid = hid;
			this.issueDate.setTime(rs.getDate(2));
			b.setCallNumber(rs.getString(3));
			b = (Book) b.get();
			borr.setBid(rs.getInt(4));
			borr = (Borrower) borr.get();
		}

		c = Conn.getInstance().getConnection();
	}

	/**
	 * HoldRequest Constructor.
	 * 
	 * This constructor takes in a Borrower and a Book and finds the HoldRequest
	 * entry in the SQL table that has these two values. This assumes the entry
	 * already exists. If it does not, this will call the default constructor.
	 * 
	 * @param borr
	 *            Borrower whose bid is shared with the HoldRequest
	 * @param b
	 *            Book whose callNo is shared with the HoldRequest
	 * @throws SQLException
	 */
	public HoldRequest(Borrower borr, Book b) throws SQLException {
		ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE bid = ?, callNo = ?");
		ps.setInt(1, borr.getBid());
		ps.setString(2, b.getCallNumber());

		rs = ps.executeQuery();

		if (rs.next()) {
			this.hid = rs.getInt(1);
			this.issueDate.setTime(rs.getDate(2));
			this.b = b;
			this.borr = borr;
		}

		c = Conn.getInstance().getConnection();
	}

	@Override
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Updates the SQL table.
	 * 
	 * This updates this HoldRequest object in the HoldRequest table. This
	 * assumes the item already exists.
	 */
	@Override
	public void update() throws SQLException {
		ps = c.prepareStatement("UPDATE holdRequest SET bid = ?, issueDate = ?, callNo = ? WHERE hid = ?");

		ps.setInt(4, hid);
		ps.setInt(1, borr.getBid());
		ps.setString(2, issueDate.toString());
		ps.setString(3, b.getCallNumber());

		int rowCount = ps.executeUpdate();
		if (rowCount == 0)
			// Throw Exception

			c.commit();
		ps.close();
	}

	/**
	 * Deletes from the SQL table.
	 * 
	 * This deletes the HoldRequest object from the HoldRequest table.
	 */
	@Override
	public boolean delete() throws SQLException {
		ps = c.prepareStatement("DELETE FROM bookCopy WHERE hid = ?");
		ps.setInt(1, hid);

		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
			// throw exception
		}

		c.commit();
		ps.close();
		return false;
	}

	/**
	 * Inserts into the SQL table.
	 * 
	 * This inserts the HoldRequest object into the HoldRequest table. This
	 * assumes the item doesn't already exist.
	 */
	@Override
	public boolean insert() throws SQLException {
		ps = c.prepareStatement("INSERT INTO HoldRequest VALUES (hidCounter.nextVal,?,?,?)");

		ps.setInt(1, borr.getBid());
		ps.setString(2, issueDate.toString());
		ps.setString(3, b.getCallNumber());

		int numRowsChanged = ps.executeUpdate();
		if (numRowsChanged == 1) {
			ps.close();
			ps = c.prepareStatement("SELECT hidCounter.currval FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				hid = rs.getInt(1);
				return true;
			}
			c.commit();
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public Collection<Table> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the HoldRequest object corresponding with the set id.
	 * 
	 * Given a HoldRequest object with an initialized id field, this returns the
	 * HoldRequest object with that id field that exists in the SQL database.
	 * This is used if either the default constructor was called and the
	 * parameters are required, or if some of the parameters are changed and the
	 * user wants the database object.
	 * 
	 * @throws SQLException
	 * 
	 */
	@Override
	public Table get() throws SQLException {
		if (hid != null)
			return (new HoldRequest((Integer) hid));
		else if (borr != null && b != null) {
			return (new HoldRequest(borr, b));
		}
		return null;
	}

	/**
	 * Return all HoldRequest objects from a given Borrower.
	 * 
	 * Given a borrower, this returns all the HoldRequests made by that
	 * borrower.
	 * 
	 * @param borr
	 *            Borrower whose bid is shared with the HoldRequest
	 * @return ArrayList of HoldRequests
	 * @throws SQLException
	 */
	public Collection<Table> getAll(Borrower borr) throws SQLException {
		Collection<Table> holdRequests = new ArrayList<Table>();
		ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE bid = ?");
		ps.setInt(1, borr.getBid());

		rs = ps.executeQuery();

		while (rs.next()) {
			HoldRequest hr = new HoldRequest();
			Book b = new Book();

			b.setCallNumber(rs.getString(3));
			b = (Book) b.get();

			hr.setHid(rs.getInt(1));
			hr.setB(b);
			hr.setBorr(borr);
			hr.getIssueDate().setTime(rs.getDate(4));
			holdRequests.add(hr);
		}

		return holdRequests;
	}

	/**
	 * Return all HoldRequest objects for a given Book.
	 * 
	 * Given a book, this returns all the HoldRequests for a particular book.
	 * 
	 * @param b
	 *            Book whose callNo is shared with the HoldRequest
	 * @return ArrayList of HoldRequests
	 * @throws SQLException
	 */
	public Collection<Table> getAll(Book b) throws SQLException {
		Collection<Table> holdRequests = new ArrayList<Table>();
		ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE callNo = ?");
		ps.setString(1, b.getCallNumber());

		rs = ps.executeQuery();

		while (rs.next()) {
			HoldRequest hr = new HoldRequest();
			Borrower borr = new Borrower();

			borr.setBid(rs.getInt(2));
			borr = (Borrower) borr.get();

			hr.setHid(rs.getInt(1));
			hr.setB(b);
			hr.setBorr(borr);
			hr.getIssueDate().setTime(rs.getDate(4));
			holdRequests.add(hr);
		}

		return holdRequests;
	}

	/**
	 * Return all HoldRequest objects made by a given Borrower for a given Book.
	 * 
	 * Given a borrower and a book, this returns all the HoldRequests for that
	 * borrower and that book. Technically, this is supposed to return only one
	 * HoldRequest.
	 * 
	 * @param borr
	 *            Borrower whose bid is shared with the HoldRequest
	 * @param b
	 *            Book whose callNo is shared with the HoldRequest
	 * @return ArrayList of HoldRequests
	 * @throws SQLException
	 */
	public Collection<Table> getAll(Borrower borr, Book b) throws SQLException {
		Collection<Table> holdRequests = new ArrayList<Table>();
		holdRequests.addAll(getAll(borr));
		holdRequests.retainAll(getAll(b));

		return holdRequests;
	}

	/**
	 * @return the hid
	 */
	public Integer getHid() {
		return hid;
	}

	/**
	 * @param hid
	 *            the hid to set
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
	 * @param issueDate
	 *            the issueDate to set
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
	 * @param b
	 *            the b to set
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
	 * @param borr
	 *            the borr to set
	 */
	public void setBorr(Borrower borr) {
		this.borr = borr;
	}

}

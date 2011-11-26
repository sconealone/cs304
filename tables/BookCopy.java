package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import users.Conn;

/**
 * This class represents the BookCopy table in the database.
 * 
 * @author Christiaan Fernando
 * 
 *         Changes 25 Nov
 * 
 *         1.Changed the string constants for in, out, hold, and overdue to
 *         match what's in the database.
 * 
 *         2. Changed all attribute references to callNumber to callNumber
 */

public class BookCopy implements Table {

	/*
	private final String IN = "in";
	private final String OUT = "out";
	private final String HOLD = "on-hold";
	private final String OVERDUE = "overdue";
	*/
	
	// These are never used.
	// TODO Ensure status is only of these types. Either create custom
	// exception, or try enum

	// The fields for BookCopy in the table are copyNo, callNumber, status in that
	// order.
	private String copyNo;
	private Book b;
	private String status;

	private Connection c;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Default Constructor
	 */
	public BookCopy() {
		c = Conn.getInstance().getConnection();
	}

	/**
	 * BookCopy Constructor
	 * 
	 * This constructor creates a BookCopy object with the given copyNo, status,
	 * and b.
	 * 
	 * @param copyNo
	 *            The copyNo of the BookCopy
	 * @param status
	 *            The status of the BookCopy
	 * @param b
	 *            The Book that shares the callNumber of the BookCopy object
	 */
	public BookCopy(String copyNo, Book b, String status) {
		this.copyNo = copyNo;
		this.status = status;
		this.b = b;

		c = Conn.getInstance().getConnection();
	}

	/**
	 * Returns a String representation of the table.
	 * 
	 * Returns a 2-D String representation of the BookCopy table.
	 */
	@Override
	public String[][] display() throws SQLException {
		String[][] result = null;
		Collection<Table> bct = getAll();

		ResultSetMetaData md = getMeta();

		if (bct.size() > 0) {
			result = new String[bct.size() + 1][md.getColumnCount()];
			int i = 0;

			Iterator<Table> bcItr = bct.iterator();
			while (bcItr.hasNext()) {
				for (int j = 0; j < result[i].length; j++) {
					if (i == 0)
						result[i][j] = md.getColumnName(j + 1);
					else {
						switch (j) {
						case 0: // copyNo
							result[i][j] = ((BookCopy) bcItr.next())
									.getCopyNo();
							break;
						case 1: // callNumber
							result[i][j] = ((BookCopy) bcItr.next()).getB()
									.getCallNumber();
							break;
						case 2: // status
							result[i][j] = ((BookCopy) bcItr.next())
									.getStatus();
							break;
						}
					}
				}
				i++;
			}
		}
		return result;
	}

	/**
	 * Updates the SQL table.
	 * 
	 * This updates this BookCopy item in the BookCopy table. This assumes the
	 * item already exists.
	 */
	@Override
	public void update() throws SQLException {
		ps = c.prepareStatement("UPDATE bookCopy SET status = '?' WHERE copyNo = '?', callNumber = ?");

		ps.setString(1, status);
		ps.setString(2, copyNo);
		ps.setString(3, b.getCallNumber());
		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
			// Throw Exception
		}
		c.commit();
		ps.close();
	}

	/**
	 * Deletes from the SQL table.
	 * 
	 * This deletes the BookCopy item from the BookCopy table.
	 */
	@Override
	public boolean delete() throws SQLException {
		ps = c.prepareStatement("DELETE FROM bookCopy WHERE callNumber = '?', copyNo = ?");

		ps.setString(1, b.getCallNumber());
		ps.setString(2, copyNo);

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
	 * This inserts the HoldRequest item into the HoldRequest table. This
	 * assumes the item doesn't already exist.
	 */
	@Override
	public boolean insert() throws SQLException {
		ps = c.prepareStatement("INSERT INTO BookCopy VALUES (?,?,?)");

		ps.setString(1, copyNo);
		ps.setString(2, b.getCallNumber());
		ps.setString(3, status);

		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
			// Throw Exception
		}

		c.commit();
		ps.close();

		return false;
	}

	/**
	 * Get all BookCopy objects in the BookCopy table.
	 * 
	 * @throws SQLException
	 */
	@Override
	public Collection<Table> getAll() throws SQLException {
		Collection<Table> bc = new ArrayList<Table>();
		ps = c.prepareStatement("SELECT * FROM BookCopy");

		rs = ps.executeQuery();

		while (rs.next()) {
			Book b = new Book();
			b.setCallNumber(rs.getString(2));
			b = (Book) b.get();
			bc.add(new BookCopy(rs.getString(1), b, rs.getString(3)));
		}

		return bc;
	}

	/**
	 * Returns the ResultSetMetaData object for the BookCopy table.
	 * 
	 * Returns an object that contains the meta data for the BookCopy table.
	 * This is an internal helper method to be used by the display method.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResultSetMetaData getMeta() throws SQLException {
		ps = c.prepareStatement("SELECT * FROM BookCopy");

		rs = ps.executeQuery();
		return rs.getMetaData();
	}

	/**
	 * Get all BookCopy objects in the BookCopy table that match the given Book.
	 * 
	 * Given a Book, find all the BookCopy objects in the BookCopy table that
	 * have the Book's callNumber.
	 * 
	 * @param b
	 *            The Book that shares the callNumber of the BookCopy object
	 * @return
	 * @throws SQLException
	 */
	public Collection<Table> getAll(Book b) throws SQLException {
		Collection<Table> bc = new ArrayList<Table>();
		ps = c.prepareStatement("SELECT * FROM BookCopy WHERE callNumber = ?");
		ps.setString(1, b.getCallNumber());

		rs = ps.executeQuery();

		while (rs.next()) {
			bc.add(new BookCopy(rs.getString(1), b, rs.getString(3)));
		}

		return bc;
	}

	/**
	 * Return the BookCopy object corresponding with the set Book and copyNo.
	 * 
	 * Given a BookCopy object with an initialized Book and copyNo field, this
	 * returns the BookCopy object with those id fields that exists in the SQL
	 * database. This is used if either the default constructor was called and
	 * the parameters are required, or if some of the parameters are changed and
	 * the user wants the database object.
	 * 
	 * @throws SQLException
	 * 
	 */
	@Override
	public Table get() throws SQLException {
		ps = c.prepareStatement("SELECT * FROM BookCopy WHERE copyNo = ?, callNumber = ?");
		ps.setString(1, this.b.getCallNumber());
		ps.setString(2, this.copyNo);

		rs = ps.executeQuery();

		while (rs.next()) {
			BookCopy bc = new BookCopy();
			bc.setCopyNo(copyNo);
			bc.setB(b);
			bc.setStatus(rs.getString(3));
			return bc;
		}
		return null;
	}

	// DEPENDING ON CONVENTION, THIS MIGHT BE DEFUNCT
	/**
	 * Returns the BookCopy object corresponding with the given Book and copyNo.
	 * 
	 * Given a Book and a copyNo, this returns the BookCopy object in the SQL
	 * database that match the given fields.
	 * 
	 * @param copyNo
	 *            The copy number of the BookCopy object
	 * @param b
	 *            The Book that shares the callNumber of the BookCopy object
	 * @return
	 * @throws SQLException
	 */
	public Table get(String copyNo, Book b) throws SQLException {
		ps = c.prepareStatement("SELECT * FROM BookCopy WHERE copyNo = ?, callNumber = ?");
		ps.setString(1, b.getCallNumber());
		ps.setString(2, copyNo);

		rs = ps.executeQuery();

		while (rs.next()) {
			BookCopy bc = new BookCopy();
			bc.setCopyNo(copyNo);
			bc.setB(b);
			bc.setStatus(rs.getString(3));
			return bc;
		}
		return null;
	}

	/**
	 * @return the copyNo
	 */
	public String getCopyNo() {
		return copyNo;
	}

	/**
	 * @param copyNo
	 *            the copyNo to set
	 */
	public void setCopyNo(String copyNo) {
		this.copyNo = copyNo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * Gets the latest copy number for the book whose callNumber matches the one
	 * passed in.
	 * 
	 * @param callNumber
	 *            the unique call number of the book whose copy we are
	 *            interested in
	 * @return null if there are no copies, otherwise the most recently added
	 *         call number
	 * @throws SQLException
	 *             if the database cannot be reached
	 */
	public String getLatestCopyNo() throws SQLException {
		// move this block to BookCopy class and call that method
		String latestCopyNumber;
		String sql = "SELECT MAX(copyNo) " + "FROM BookCopy "
				+ "WHERE callNumber = ?" + "GROUP BY callNumber";
		Connection con = Conn.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, b.getCallNumber());
		ResultSet rs = ps.executeQuery();
		latestCopyNumber = (rs.next()) ? rs.getString(1) : null;
		return latestCopyNumber;
		// end move this block to BookCopy class and call that method
	}

}

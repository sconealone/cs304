package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import users.Conn;

/**
 * This class represents a Borrowing for a BookCopy by a Borrower.
 * 
 * @author Mitch
 */
public class Borrowing implements Table {

	private Integer borid;
	private Calendar outDate;
	private Calendar inDate;
	private BookCopy bc;
	private Borrower borr;

	Connection con;
	Statement stmt;

	/**
	 * Creates an empty Borrowing
	 */
	public Borrowing() {
		con = Conn.getInstance().getConnection();
	}

	/**
	 * Creates a Borrowing with user specified fields
	 * 
	 * @param borid
	 *            the unique borrowing ID of this Borrowing
	 * @param outDate
	 *            the date that BookCopy was loaned out; the date that this
	 *            Borrowing was created
	 * @param inDate
	 *            the date that the BookCopy was returned to the library
	 * @param bc
	 *            the book copy that this Borrowing is for
	 * @param borr
	 *            the Borrower who borrowed this Borrowing
	 * @throws IllegalArgumentException
	 *             if the future comes before the past
	 */
	public Borrowing(Integer borid, Calendar outDate, Calendar inDate,
			BookCopy bc, Borrower borr) {
		con = Conn.getInstance().getConnection();
		this.borid = borid;
		this.outDate = outDate;
		verifyDateOrder(outDate, inDate);
		this.inDate = inDate;
		this.bc = bc;
		this.borr = borr;
	}

	/**
	 * Builds a Borrowing object from an open result set only for use within
	 * this object! Call next() before you pass the ResultSet
	 * 
	 * @param rs
	 */
	private Borrowing(ResultSet rs) throws SQLException {
		con = Conn.getInstance().getConnection();
		int fieldIndex = 1;
		borid = rs.getInt(fieldIndex++);

		borr = new Borrower();
		borr.setBid(rs.getInt(fieldIndex++));
		borr = (Borrower) borr.get();

		// initializing book copy
		String callNumber = rs.getString(fieldIndex++);
		String copyNo = rs.getString(fieldIndex++);
		Book book = new Book();
		book.setCallNumber(callNumber);
		book = (Book) book.get();
		BookCopy copy = new BookCopy();
		copy.setB(book);
		copy.setCopyNo(copyNo);
		copy = (BookCopy) copy.get();
		bc = copy;
		Date sqlOutDate = rs.getDate(fieldIndex++);
		outDate = (rs.wasNull()) ? null : new GregorianCalendar();
		if (outDate != null) {
			outDate.setTime(sqlOutDate);
		}
		Date sqlInDate = rs.getDate(fieldIndex++);
		inDate = (rs.wasNull()) ? null : new GregorianCalendar();
		if (inDate != null) {
			inDate.setTime(sqlInDate);
		}
	}

	/**
	 * Calculates the due date of the Borrowing.
	 * 
	 * @pre borr is initialized
	 * @pre outDate is initialized
	 * @return the date that the Borrowing is due back to the library
	 * @throws NullPointerException
	 *             if outDate or borr is not initialized
	 */
	public Calendar getDueDate() {
		int timeLimitWeeks = borr.getBookTimeLimit();
		final int DAYS_IN_WEEK = 7;
		Calendar dueDate = (Calendar) outDate.clone();
		dueDate.add(Calendar.DATE, timeLimitWeeks * DAYS_IN_WEEK);
		return dueDate;
	}

	/**
	 * Returns a matrix representation of the Borrowing table with every field
	 * shown as a String.
	 * 
	 * @return a 2D array of strings where each row represents a tuple of the
	 *         Borrowing table and every column represents a field
	 */
	@Override
	public String[][] display() throws SQLException {
		ArrayList<String[]> borrowingGrowable = new ArrayList<String[]>();

		PreparedStatement ps = con.prepareStatement("SELECT * FROM Borrowing");
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData md = rs.getMetaData();

		int numFields = md.getColumnCount();
		String[] columnNames = new String[numFields];
		for (int i = 0; i < numFields; i++) {
			columnNames[i] = md.getColumnName(i + 1);
		}
		borrowingGrowable.add(columnNames);

		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

		while (rs.next()) {
			String[] row = new String[numFields];
			// row will contain a tuple from the database
			int fieldIndex = 0;

			// these fields are marked not null in database
			row[fieldIndex] = "" + rs.getInt(fieldIndex + 1);
			fieldIndex++;
			row[fieldIndex] = "" + rs.getInt(fieldIndex + 1);
			fieldIndex++;
			row[fieldIndex] = rs.getString(fieldIndex + 1);
			fieldIndex++;
			row[fieldIndex] = rs.getString(fieldIndex + 1);
			fieldIndex++;

			// these fields might be null
			Date anOutDate = rs.getDate(fieldIndex + 1);
			row[fieldIndex] = (!rs.wasNull()) ? df.format(anOutDate) : "null";
			fieldIndex++;

			Date anInDate = rs.getDate(fieldIndex + 1);
			row[fieldIndex] = (!rs.wasNull()) ? df.format(anInDate) : "null";
			fieldIndex++;

			borrowingGrowable.add(row);
		} // end while

		rs.close();

		int numRows = borrowingGrowable.size();
		String[][] borrowing = new String[numRows][];
		for (int i = 0; i < numRows; i++) {
			borrowing[i] = borrowingGrowable.get(i);
		}
		return borrowing;
	}

	/**
	 * Updates the tuple in the Borrowing table whose primary key corresponds to
	 * this Borrowing's borid. Please make sure there are no null fields unless
	 * you want the field to appear as null in the database.
	 */
	@Override
	public void update() throws SQLException {
		String sql = "UPDATE Borrowing " + "SET bid=" + borr.getBid()
				+ ", callNumber=?," + "copyNo=?, outDate=?, inDate=?"
				+ "WHERE borid=" + borid;
		PreparedStatement ps = con.prepareStatement(sql);
		int paramIndex = 1;
		ps.setString(paramIndex++, bc.getB().getCallNumber());
		ps.setString(paramIndex++, bc.getCopyNo());

		java.sql.Date sqlOutDate = (outDate == null) ? null
				: new java.sql.Date(outDate.getTime().getTime());
		ps.setDate(paramIndex++, sqlOutDate, outDate);

		java.sql.Date sqlInDate = (inDate == null) ? null : new java.sql.Date(
				inDate.getTime().getTime());
		ps.setDate(paramIndex++, sqlInDate, inDate);

		ps.executeUpdate();
		ps.close();

	}

	/**
	 * Deletes the tuple in the Borrowing table whose primary key corresponds to
	 * this Borrowing's borid. All other attributes are ignored.
	 * 
	 * @return true if the tuple was successfully deleted, otherwise false
	 */
	@Override
	public boolean delete() throws SQLException {
		String sql = "DELETE FROM Borrowing WHERE borid = " + borid;

		PreparedStatement ps = con.prepareStatement(sql);
		int numRowsDeleted = ps.executeUpdate();
		ps.close();
		return numRowsDeleted == 1;

	}

	/**
	 * Gets every Borrowing from the database and makes them into Borrowing
	 * objects. Stores them in a collection and returns them. If there are no
	 * Borrowings in the database the collection will be empty.
	 * 
	 * @return a collection containing all the Borrowing objects in the database
	 */
	@Override
	public Collection<Table> getAll() throws SQLException {
		ArrayList<Table> borrowings = new ArrayList<Table>();

		PreparedStatement ps = con.prepareStatement("SELECT * FROM Borrowing");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			borrowings.add(new Borrowing(rs));

		} // end while(rs.next())
		rs.close();

		return borrowings;
	}

	/**
	 * Return all overdue Borrowing objects
	 * 
	 * Returns all Borrowing objects from the CheckedOutBookCopy View that have a status of 'overdue'
	 * 
	 * @return Table of overdue Borrowing items
	 * @throws SQLException
	 */
	public Collection<Table> getOverdue() throws SQLException {
		ArrayList<Table> borrowings = new ArrayList<Table>();
                Borrowing bwing = new Borrowing();

                (new CheckedOutBookCopy()).flagOverdue();
		PreparedStatement ps = con
				.prepareStatement("SELECT DISTINCT borid FROM CheckedOutBookCopy WHERE status = 'overdue'");
                
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
                    bwing.setBorid(rs.getInt(1));
			borrowings.add(bwing.get());

		} // end while(rs.next())
		rs.close();

		return borrowings;
	}

	/**
	 * Gets the tuple from the Borrowing table that corresponds to this
	 * Borrowings's borid and returns it. No attributes in the Borrowing object
	 * that calls this method are modified.
	 * 
	 * @return a new Borrowing object whose attributes are set based on the
	 *         tuple in the database that shares the calling object's borid.
	 *         Returns null if the Borrowing does not exist.
	 */
	@Override
	public Table get() throws SQLException {
		String sql = "SELECT * FROM Borrowing WHERE borid = " + borid;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Borrowing borrowing = new Borrowing(rs);
			if (borrowing.getBorid() > 0) {
				return borrowing;
			}
		}

		return null;
	}

        
        /**
         * Gets the borrowing for the book copy that is out.
         * Semantically there can only ever be one borrowing for any given
         * book copy that is out of the library, since the copy's status is 
         * changed to in upon return.
         * @param callNumber
         * @param copyNo
         * @return 
         * @throws SQLException
         * @throws BookCopyEvilTwinException if more than one borrowing registers
         * this copy as not having an in date
         */
        public Borrowing getOutBorrowing(BookCopy outCopy) 
                throws SQLException, BookCopyEvilTwinException, NoSuchCopyException
        {
          String sql = "SELECT DISTINCT R.borid "
                  + "FROM Borrowing R, CheckedOutBookCopy C "
                  + "WHERE C.callNumber = ? AND C.copyNo = ? AND R.borid = C.borid";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, outCopy.getB().getCallNumber());
          ps.setString(2, outCopy.getCopyNo());
          ResultSet rs = ps.executeQuery();
          final int BORID = 1;
          
          Borrowing borrowing = null;
          
          if (rs.next())
          {
            // borid
            int dbBorid = rs.getInt(BORID);
            
            
            borrowing = new Borrowing();
            borrowing.setBorid(dbBorid);
            borrowing = (Borrowing) borrowing.get();
          }
          else
          {
            String msg = "There is no copy of that book that has not been returned.";
            throw new NoSuchCopyException(msg);
          }
          if (!rs.next())
          {
            return borrowing;
          }
          else
          {
            String msg = "That book is flagged as being borrowed by two borrowers at the same time.";
            throw new BookCopyEvilTwinException(msg);
          }
        }
        
	/**
	 * Creates a new entry in the database for this Borrowing object. A new
	 * borid will be generated, and the calling object's borid attribute will be
	 * mutated to the new borid.
	 * 
	 * @return true if the Borrowing was successfully inserted otherwise false
	 * @post this object's borid will be the newest borid in the database
	 */
	@Override
	public boolean insert() throws SQLException {
		String sql = "INSERT INTO Borrowing " + "VALUES (boridCounter.nextval,"
				+ borr.getBid() + ",?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		int paramIndex = 1;
		ps.setString(paramIndex++, bc.getB().getCallNumber());
		ps.setString(paramIndex++, bc.getCopyNo());

		java.sql.Date sqlOutDate = (outDate == null) ? null
				: new java.sql.Date(outDate.getTime().getTime());
		ps.setDate(paramIndex++, sqlOutDate, outDate);

		java.sql.Date sqlInDate = (inDate == null) ? null : new java.sql.Date(
				outDate.getTime().getTime());
		ps.setDate(paramIndex++, sqlInDate, inDate);

		int numRowsChanged = ps.executeUpdate();
		if (numRowsChanged == 1) {
			ps.close();
			ps = con.prepareStatement("SELECT boridCounter.currval FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				borid = rs.getInt(1);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the borid
	 */
	public Integer getBorid() {
		return borid;
	}

	/**
	 * @param borid
	 *            the borid to set
	 */
	public void setBorid(Integer borid) {
		this.borid = borid;
	}

	/**
	 * @return the outDate
	 */
	public Calendar getOutDate() {
		return outDate;
	}

	/**
	 * @param outDate
	 *            the outDate to set
	 */
	public void setOutDate(Calendar outDate) {
		this.outDate = outDate;
	}

	/**
	 * @return the inDate
	 */
	public Calendar getInDate() {
		return inDate;
	}

	/**
	 * @param inDate
	 *            the inDate to set
	 * @pre inDate must be after outDate
	 * @throws IllegalArgumentException
	 *             if the inDate comes before the outDate
	 */
	public void setInDate(Calendar inDate) {
		verifyDateOrder(outDate, inDate);
		this.inDate = inDate;
	}

	/**
	 * @return the bc
	 */
	public BookCopy getBookCopy() {
		return bc;
	}

	/**
	 * @param bc
	 *            the bc to set
	 */
	public void setBookCopy(BookCopy bc) {
		this.bc = bc;
	}

	/**
	 * @return the borr
	 */
	public Borrower getBorrower() {
		return borr;
	}

	/**
	 * @param borr
	 *            the borr to set
	 */
	public void setBorrower(Borrower borr) {
		this.borr = borr;
	}

	/**
	 * Throws an exception if the future date comes before the past date
	 * 
	 * @param past
	 * @param future
	 * @throws IllegalArgumentException
	 *             if the future comes before the past
	 */
	private static void verifyDateOrder(Calendar past, Calendar future) {
		if (future == null) {
			return;
		}
		boolean futureWithoutAPast = past == null/* && future != null */;
		if (futureWithoutAPast || future.before(past)) {
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			throw new IllegalArgumentException(
					"Time paradox! Cannot return borrowing"
							+ "before it is borrowed. (out date: "
							+ ((past == null) ? "null" : df.format(past
									.getTime())) + "; in date: "
							+ df.format(future.getTime()) + ")");
		}

	}

	/**
	 * returns a string representation of all the attributes of this Borrowing
	 * object.
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		return "borid = " + borid + "\nbid = " + borr.getBid()
				+ "\ncallNumber = " + bc.getB().getCallNumber() + "\ncopyNo = "
				+ bc.getCopyNo() + "\noutDate = "
				+ ((outDate == null) ? null : df.format(outDate.getTime()))
				+ "\ninDate = "
				+ ((inDate == null) ? null : df.format(inDate.getTime()));

	}

}

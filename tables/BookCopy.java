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
		c = Conn.getInstance().getConnection();
		this.copyNo = copyNo;
		this.status = status;
		this.b = b;

	}
        
        /**
         * Constructs a book copy from a result set.
         * Call result set :: next before calling this constructor
         * @param rs 
         */
        private BookCopy(ResultSet rs) throws SQLException
        {
          c = Conn.getInstance().getConnection();
          int paramIndex = 1;
          
          // call number
          Book book = new Book();
          book.setCallNumber(rs.getString(paramIndex++));
          b = book.get();
          
          // copy number
          copyNo = rs.getString(paramIndex++);
          
          // status
          status = rs.getString(paramIndex++);
        }

	/**
	 * Returns a String representation of the table.
	 * 
	 * Returns a 2-D String representation of the BookCopy table.
	 */
	@Override
	public String[][] display() throws SQLException {
          
          String sql = "SELECT * FROM BookCopy";
          ps = c.prepareStatement(sql);
          rs = ps.executeQuery();
          ResultSetMetaData md = rs.getMetaData();
          int numCols = md.getColumnCount();
          ArrayList<String[]> copiesGrowable = new ArrayList<String[]>();
          String[] header = new String[numCols];
          for (int i = 0; i < numCols; i++)
          {
            header[i] = md.getColumnName(i+1);
          }
          copiesGrowable.add(header);

          int colIndex, paramIndex;
          while (rs.next())
          {
            String[] row = new String[numCols];
            colIndex = 0;
            paramIndex = 1;
            // callNumber
            row[colIndex++] = rs.getString(paramIndex++);

            // copyNo
            row[colIndex++] = rs.getString(paramIndex++);

            // status
            // empty string handles null case
            row[colIndex++] = ""+rs.getString(paramIndex++);

            copiesGrowable.add(row);
          }

          int numRows = copiesGrowable.size();
          String[][] copies = new String[numRows][];
          for (int i = 0; i < numRows; i++)
          {
            copies[i] = copiesGrowable.get(i);
          }
          return copies;
	}

	/**
	 * Updates the SQL table.
	 * 
	 * This updates this BookCopy item in the BookCopy table. This assumes the
	 * item already exists.
	 */
	@Override
	public void update() throws SQLException {
          ps = c.prepareStatement("UPDATE bookCopy SET status = ? WHERE copyNo = ? AND callNumber = ?");

          ps.setString(1, status);
          ps.setString(2, copyNo);
          ps.setString(3, b.getCallNumber());
          ps.executeUpdate();
		
	}

	/**
	 * Deletes from the SQL table.
	 * 
	 * This deletes the BookCopy item from the BookCopy table.
	 */
	@Override
	public boolean delete() throws SQLException {
          ps = c.prepareStatement("DELETE FROM bookCopy WHERE callNumber = ?"
                  + " AND copyNo = ?");

          ps.setString(1, b.getCallNumber());
          ps.setString(2, copyNo);

          int rowCount = ps.executeUpdate();
          return rowCount == 1;
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
          if (copyNo == null)
          {
            String latestCopyNo = getLatestCopyNo();
            int unusedCopyNoInt = Integer.parseInt(latestCopyNo.substring(1));
            unusedCopyNoInt++;
            copyNo = "C" + unusedCopyNoInt;
          }
          ps.setString(2, copyNo);
          ps.setString(1, b.getCallNumber());
          ps.setString(3, "in");

          int rowCount = ps.executeUpdate();
          return rowCount == 1;
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
	public Table get() throws SQLException 
        {
          String sql = "SELECT * "
                  + "FROM BookCopy "
                  + "WHERE callNumber=? AND copyNo=?";
          PreparedStatement stat = c.prepareStatement(sql);
          stat.setString(1, b.getCallNumber());
          stat.setString(2, copyNo);
          

          ResultSet result = stat.executeQuery();
          
          if (result.next()) 
          {
            return new BookCopy(result);
          }
          else
          {
            return null;
          }
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
		ps = c.prepareStatement("SELECT * FROM BookCopy WHERE copyNo = ? AND callNumber = ?");
		ps.setString(2, b.getCallNumber());
		ps.setString(1, copyNo);

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
	 * Gets the latest copy number for the book whose callNumber matches 
         * this book copy's book's call number
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
                
                String sql = 
                        "SELECT MAX(mCopyNo) "
                        + "FROM ( SELECT callNumber, MAX(copyNo) AS mCopyNo, length(copyNo) AS lCopyNo"
                        + "       FROM BookCopy "
                        + "       WHERE callNumber = ? "
                        + "       GROUP BY callNumber, length(copyNo))"
                        + "WHERE lCopyNo IN "
                        + "     ( SELECT max(length(copyNo))"
                        + "       FROM BookCopy "
                        + "       WHERE callNumber = ?)";
                
		/*String sql = "SELECT MAX(copyNo) " + "FROM BookCopy "
				+ "WHERE callNumber = ?" + "GROUP BY callNumber";*/
		Connection con = Conn.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, b.getCallNumber());
                ps.setString(2, b.getCallNumber());
		ResultSet rs = ps.executeQuery();
		latestCopyNumber = (rs.next()) ? rs.getString(1) : null;
		return latestCopyNumber;
		// end move this block to BookCopy class and call that method
	}
        
        @Override
        public String toString()
        {
          return null;
        }

        
  public static void main(String[] args) throws Exception {
    /*
    BookCopy bcget = new BookCopy();
    Book bcgetbook = new Book();
    bcgetbook.setCallNumber("GV345 R202 1997");
    bcget.setB(bcgetbook);
    bcget.setCopyNo("C1");
    bcget = (BookCopy) bcget.get();
    
    System.out.println(bcget.getB().getCallNumber());
    System.out.println(bcget.getB().getTitle());
    System.out.println(bcget.status);
    System.out.println(bcget.copyNo);
     * 
     
    
    BookCopy bcupdate = new BookCopy();
    Book bcupdatebook = new Book();
    bcupdatebook.setCallNumber("AK315 X383 1999");
    bcupdate.setB(bcupdatebook);
    bcupdate.setCopyNo("C2");
    bcupdate.setStatus("out");
    bcupdate.update();
    
    System.out.println(bcupdate);
    
    BookCopy bcinsert = new BookCopy();
    Book bcinsertbook = new Book();
    bcinsertbook.setCallNumber("WY273 P213 1986");
    bcinsert.setB(bcinsertbook);
    bcinsert.insert();
    int count = 40;
    bcinsert.setCopyNo("C"+count);
    bcinsert.insert();
    bcinsert.setCopyNo(null);
    bcinsert.insert();
    
    */
    
    BookCopy bcdel = new BookCopy();
    Book bcdelbook = new Book();
    bcdelbook.setCallNumber("WY273 P213 1986");
    bcdel.setB(bcdelbook);
    bcdel.setCopyNo("C40");
    System.out.println(bcdel.delete());
    bcdel.setCopyNo("C41");
    
    System.out.println(bcdel.delete());
    
    System.out.println(bcdel.delete());
    
    
  }
}

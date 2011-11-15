package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import users.Conn;

/**
 * This class represents a Borrowing for a BookCopy by a Borrower.
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
        public Borrowing()
        {
          con = Conn.getInstance().getConnection();
        }
        
        /**
         * Creates a Borrowing with user specified fields
         * @param borid the unique borrowing ID of this Borrowing
         * @param outDate the date that BookCopy was loaned out; the date that
         *  this Borrowing was created
         * @param inDate the date that the BookCopy was returned to the library
         * @param bc the book copy that this Borrowing is for
         * @param borr the Borrower who borrowed this Borrowing
         * @throws IllegalArgumentException if the future comes before the past
         */
        public Borrowing( Integer borid, 
                          Calendar outDate, 
                          Calendar inDate, 
                          BookCopy bc,
                          Borrower borr)
        {
          con = Conn.getInstance().getConnection();
          this.borid = borid;
          this.outDate = outDate;
          verifyDateOrder(outDate, inDate);
          this.inDate = inDate;
          this.bc = bc;
          this.borr = borr;
        }
	
        /**
         * Calculates the due date of the Borrowing.
         * @pre borr is initialized
         * @pre outDate is initialized
         * @return the date that the Borrowing is due back to the library
         * @throws NullPointerException if outDate or borr is not initialized
         */
	public Calendar getDueDate() {
		return null;
	}
	
	/**
         * Returns a matrix representation of the Borrowing table with
         * every field shown as a String.
         * @return a 2D array of strings where each row represents a tuple
         * of the Borrowing table and every column represents a field
         */
	@Override
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
	}

        /**
         * Updates the tuple in the Borrowing table whose primary key corresponds to
         * this Borrowing's borid.  Please make sure there are no null fields
         * unless you want the field to appear as null in the database.
         */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

        /**
         * Deletes the tuple in the Borrowing table whose primary key
         * corresponds to this Borrowing's borid.  All other attributes are
         * ignored.
         * @return true if the tuple was successfully deleted, otherwise false
         */
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

        /**
         * Gets every Borrowing from the database and makes them into 
         * Borrowing objects.  Stores them in a collection and returns them.
         * If there are no Borrowings in the database the collection will be
         * empty.
         * @return a collection containing all the Borrowing objects in the
         * database
         */
	@Override
	public Collection<Table> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

        /**
         * Gets the tuple from the Borrowing table that corresponds to this
         * Borrowings's borid and returns it.  No attributes in the Borrowing
         * object that calls this method are modified.
         * @return a new Borrowing object whose attributes are set based on
         * the tuple in the database that shares the calling object's borid
         */
	@Override
	public Table get() {
		// TODO Auto-generated method stub
		return null;
	}

        /**
         * Creates a new entry in the database for this Borrowing object.
         * A new borid will be generated, and the calling object's borid
         * attribute will be mutated to the new borid.
         * @return true if the Borrowing was successfully inserted
         * otherwise false
         * @post this object's borid will be the newest borid in the database
         */
	@Override
	public boolean insert() {
		try {
			stmt.executeUpdate("INSERT INTO Borrowing VALUES (" + getBorid() + ", " + getOutDate() + ", " +
			getInDate() + ", " + getBc() + ", " + getBorr() + ")");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

  /**
   * @return the borid
   */
  public Integer getBorid() {
    return borid;
  }

  /**
   * @param borid the borid to set
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
   * @param outDate the outDate to set
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
   * @param inDate the inDate to set
   * @pre inDate must be after outDate
   * @throws IllegalArgumentException if the inDate comes before the outDate
   */
  public void setInDate(Calendar inDate) {
    verifyDateOrder(outDate, inDate);
    this.inDate = inDate;
  }

  /**
   * @return the bc
   */
  public BookCopy getBc() {
    return bc;
  }

  /**
   * @param bc the bc to set
   */
  public void setBc(BookCopy bc) {
    this.bc = bc;
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

  /**
   * Throws an exception if the future date comes before the past date
   * @param past
   * @param future 
   * @throws IllegalArgumentException if the future comes before the past
   */
  private static void verifyDateOrder(Calendar past, Calendar future)
  {
    if (future == null)
    {
      return;
    }
    boolean futureWithoutAPast = past == null/* && future != null*/;
    if (futureWithoutAPast || future.before(past))
    {
      DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
      throw new IllegalArgumentException("Time paradox! Cannot return borrowing"
              + "before it is borrowed. (out date: "
              +((past == null) ? "null" : df.format(past.getTime()) )
              +"; in date: "+df.format(future.getTime())+")");
    }
  
  }
  
  public static void main(String[] args) {
    Calendar past = new GregorianCalendar(1983,11,27);
    Calendar future = new GregorianCalendar(2004, 12, 14);
    verifyDateOrder(past, future);
    try
    {
      verifyDateOrder(future, past);
    }
    catch (Exception e)
    {
      System.out.println("successfully caught exception");
    }
  }
}

package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import users.Conn;

/**
 * This class represents a Fine for a borrowing.  A Fine is assessed
 * when a borrowing is returned.  Several fines may be issued for the same
 * borrowing; for example one might be an overdue fine, and one might be
 * fine for a damaged book.  The Fine's amount will always be in cents.
 * 
 * @author Mitch
 */
public class Fine implements Table {

        /**
         * The unique identifier for the fine
         */
	private Integer fid;
        
        /**
         * The amount that needs to be paid.
         * Its unit is cents.
         */
	private Integer amount;
        
        /**
         * The date the fine was issued.  In general this will be
         * the date that the book was returned.
         */
	private Calendar issueDate;
        
        /**
         * The date that the fine was paid.  Each fine must be paid in full.
         */
	private Calendar paidDate;
        
        /**
         * The borrowing that the fine is for.  Contains information about
         * which book copy the fine is for, and the borrower who needs
         * to pay the fine.
         */
	private Borrowing b;
        
        /**
         * A database connection initialized in the constructor
         */
        private Connection con;
        
        /**
         * Creates an empty Fine object
         */
        public Fine()
        {
          con = Conn.getInstance().getConnection();
        }
        
        /**
         * Creates a new Fine object
         * @param fid
         * @param amount
         * @param issueDate
         * @param paidDate
         * @param borrowing 
         * @throws IllegalArgumentException if the paidDate comes before
         *  the issueDate
         */
        public Fine(  Integer fid,
                      Integer amount,
                      Calendar issueDate,
                      Calendar paidDate,
                      Borrowing borrowing)
        {
          this.fid = fid;
          this.amount = amount;
          this.issueDate = issueDate;
          verifyDateOrder(issueDate, paidDate);
          this.paidDate = paidDate;
          this.b = borrowing;
        }
        
        /**
         * Creates a new Fine object based on a result set.  Only for internal
         * use.  Call next() on the result set before using it to create
         * and object.
         * @throws SQLException if the object cannot be created.  The ResultSet
         * might be closed.
         */
        private Fine(ResultSet rs) throws SQLException
        {
          con = Conn.getInstance().getConnection();
          int colIndex = 1;
          fid = rs.getInt(colIndex++);
          
          amount = rs.getInt(colIndex++);
          
          java.sql.Date sqlIssuedDate = rs.getDate(colIndex++);
          issueDate = (rs.wasNull()) ?
                  null : new GregorianCalendar();
          if (issueDate != null)
          {
            issueDate.setTime(sqlIssuedDate);
          }
          
          java.sql.Date sqlpaidDate = rs.getDate(colIndex++);
          paidDate = (rs.wasNull()) ?
                  null : new GregorianCalendar();
          if (paidDate != null)
          {
            paidDate.setTime(sqlpaidDate);
          }
          
          int borid = rs.getInt(colIndex++);
          b = new Borrowing();
          b.setBorid(borid);
          b = (Borrowing) b.get();          
          
        }
        
        /**
         * String representation of this object and all its attributes
         * @return 
         */
        @Override
        public String toString()
        {
          DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
          NumberFormat nf = new DecimalFormat("$0.00");
          return "fid = " + fid + '\n'
                  + "amount = " + nf.format(amount / 100.0) + '\n'
                  + "issuedDate = " 
                  + ((issueDate == null) ?
                    "null" : df.format(issueDate.getTime())) 
                  + '\n'
                  + "paidDate = "
                  + ((paidDate == null) ?
                    "null" : df.format(paidDate.getTime()))+ '\n'
                  + "borid = " + b.getBorrower().getBid();
        }
	
	/**
         * Generates a matrix of strings that represents the database's
         * table structure for the Fine table.
         * Each row represents a tuple in the Fine table and each column
         * represents a field in the tuple table.
         * @return a matrix of strings representing the structure of the Fine
         *  table
         */
	@Override
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
	}

        /**
         * Updates the Fine tuple whose fid matches the fid of this Fine object.
         * All fields will be updated so please do not leave any attributes
         * of this object null unless you want nulls to appear in the database.
         * @pre the fid of this object must match an fid already in the database
         * @post the Fine table in the database is updated
         */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

        /**
         * Deletes the Fine tuple whose fid matches the fid of this Fine object.
         * @return true if the delete was successful otherwise false
         * @pre fid must already exist in the database
         */
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

        /**
         * Creates a new entry in the Fine table, where each column matches
         * the corresponding attribute of this Fine object.  The fid will be
         * generated in the database, and the fid of this object will be mutated
         * to hold the new fid.
         * @return true if the entry was successfully created otherwise false.
         */
	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
        /**
         * Gets every entry of the Fine table and creates a Fine object for it.
         * All objects are stored in a collection and returned.
         * @return a collection of all Fines.
         */
	public Collection<Table> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

        /**
         * Gets the Fine tuple whose fid matches this object's fid and creates
         * a new Fine object that represents the tuple.
         * @return a Fine object representing the Fine tuple in the database
         *  with this object's fid.
         */
	@Override
	public Table get() {
          try
          {
            String sql = "SELECT * FROM Fine WHERE fid = "+fid;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
              return new Fine(rs);
            }
          }
          catch(SQLException e)
          {
            // TODO handle exception
            e.printStackTrace();
          }        
          return null;
	}

  /**
   * @return the fid
   */
  public Integer getFid() {
    return fid;
  }

  /**
   * @param fid the fid to set
   */
  public void setFid(Integer fid) {
    this.fid = fid;
  }

  /**
   * The amount is in cents.
   * @return the amount
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * The amount is in cents.
   * @param amount the amount to set
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  /**
   * @return the issueDate
   */
  public Calendar getIssuedDate() {
    return issueDate;
  }

  /**
   * @param issueDate the issueDate to set
   */
  public void setIssuedDate(Calendar issueDate) {
    this.issueDate = issueDate;
  }

  /**
   * @return the paidDate
   */
  public Calendar getPaidDate() {
    return paidDate;
  }

  /**
   * @param paidDate the paidDate to set
   * @pre the paidDate must come after the issueDate 
   * @throws IllegalArgumentException if the paidDate comes before
   *  the issueDate
   */
  public void setPaidDate(Calendar paidDate) {
    verifyDateOrder(issueDate, paidDate);
    this.paidDate = paidDate;
  }

  /**
   * @return the borrowing
   */
  public Borrowing getBorrowing() {
    return b;
  }

  /**
   * @param b the borrowing to set
   */
  public void setBorrowing(Borrowing b) {
    this.b = b;
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
      throw new IllegalArgumentException("Time paradox! Fine's paid date cannot come"
              + " before the fine's issue date. Issue date: "
              +((past == null) ? "null" : df.format(past.getTime()) )
              +"; paid date: "+df.format(future.getTime())+")");
    }
  
  }
  
  /**
   * For testing only
   * @param args 
   */
  public static void main(String[] args) {
    // get
    Fine f = new Fine();
    f.setFid(2);
    f = (Fine) f.get();
    System.out.println("fine:\n"+f);
    
  }

}

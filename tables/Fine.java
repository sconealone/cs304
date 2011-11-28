package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
         * It must always be a non-negative number
         */
	private Integer amount;
        
        /**
         * The date the fine was issued.  In general this will be
         * the date that the book was returned.
         */
	private Calendar issuedDate;
        
        /**
         * The date that the fine was paid.  Each fine must be paid in full.
         */
	private Calendar paidDate;
        
        /**
         * The borrowing that the fine is for.  Contains information about
         * which book copy the fine is for, and the borrower who needs
         * to pay the fine.
         */
	private Borrowing borrowing;
        
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
         *  the issueDate or if the amount is negative
         */
        public Fine(  Integer fid,
                      Integer amount,
                      Calendar issueDate,
                      Calendar paidDate,
                      Borrowing borrowing)
        {
          con = Conn.getInstance().getConnection();
          this.fid = fid;
          if (amount != null && amount < 0)
          {
            String msg = "Fine amounts must be non-negative";
            throw new IllegalArgumentException(msg);
          }
          this.amount = amount;
          this.issuedDate = issueDate;
          verifyDateOrder(issueDate, paidDate);
          this.paidDate = paidDate;
          this.borrowing = borrowing;
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
          issuedDate = (rs.wasNull()) ?
                  null : new GregorianCalendar();
          if (issuedDate != null)
          {
            issuedDate.setTime(sqlIssuedDate);
          }
          
          java.sql.Date sqlpaidDate = rs.getDate(colIndex++);
          paidDate = (rs.wasNull()) ?
                  null : new GregorianCalendar();
          if (paidDate != null)
          {
            paidDate.setTime(sqlpaidDate);
          }
          
          int borid = rs.getInt(colIndex++);
          borrowing = new Borrowing();
          borrowing.setBorid(borid);
          borrowing = (Borrowing) borrowing.get();   
          
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
                  + ((issuedDate == null) ?
                    "null" : df.format(issuedDate.getTime())) 
                  + '\n'
                  + "paidDate = "
                  + ((paidDate == null) ?
                    "null" : df.format(paidDate.getTime()))+ '\n'
                  + "borid = " + borrowing.getBorid();
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
	public String[][] display() throws SQLException 
        {
          ArrayList<String[]> finesGrowable = new ArrayList<String[]>();
          PreparedStatement ps = con.prepareStatement("SELECT * FROM Fine");
          ResultSet rs = ps.executeQuery();
          ResultSetMetaData md = rs.getMetaData();
          int numFields = md.getColumnCount();
          String[] colNames = new String[numFields];
          for (int i = 0; i < numFields; i++)
          {
            colNames[i] = md.getColumnName(i+1);
          }
          finesGrowable.add(colNames);

          NumberFormat nf = new DecimalFormat("$0.00");
          DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
          while(rs.next())
          {
            String[] tuple = new String[numFields];
            int fieldIndex = 0;
            Fine f = new Fine(rs);
            tuple[fieldIndex++] = ""+f.fid;
            tuple[fieldIndex++] = nf.format(f.amount / 100.0);
            tuple[fieldIndex++] = (f.issuedDate == null) ?
                    "null" : df.format(f.issuedDate.getTime());
            tuple[fieldIndex++] = (f.paidDate == null) ?
                    "null" : df.format(f.paidDate.getTime());
            tuple[fieldIndex++] = "" + f.borrowing.getBorid();
            finesGrowable.add(tuple);
          }
          
          int numRows = finesGrowable.size();
          String[][] fines = new String[numRows][];
          for (int i = 0; i < numRows; i++)
          {
            fines[i] = finesGrowable.get(i);
          }
          return fines;
	}

        /**
         * Updates the Fine tuple whose fid matches the fid of this Fine object.
         * All fields will be updated so please do not leave any attributes
         * of this object null unless you want nulls to appear in the database.
         * @pre the fid of this object must match an fid already in the database
         * @post the Fine table in the database is updated
         * @throws NullPointerException if borrowing is not initialized
         */
	@Override

	public void update()  throws SQLException
        {
          String sql = "UPDATE Fine SET "
                  + "amount = "+amount+','
                  + "issuedDate = ?,"
                  + "paidDate = ?,"
                  + "borid = "+borrowing.getBorid()+' '
                  + "WHERE fid = "+fid;
          
          
          PreparedStatement ps = con.prepareStatement(sql);
          int paramIndex = 1;
          java.sql.Date sqlIssuedDate = (issuedDate == null) ?
                  null : new java.sql.Date(issuedDate.getTime().getTime());
          ps.setDate(paramIndex++, sqlIssuedDate);
          java.sql.Date sqlPaidDate = (paidDate == null) ?
                  null : new java.sql.Date(paidDate.getTime().getTime());
          ps.setDate(paramIndex++, sqlPaidDate);

          ps.executeUpdate();
          
	}

        /**
         * Deletes the Fine tuple whose fid matches the fid of this Fine object.
         * @return true if the delete was successful otherwise false
         * @pre fid must already exist in the database
         */
	@Override
	public boolean delete()  throws SQLException
        {
          String sql = "DELETE FROM Fine WHERE fid = "+fid;
          
          PreparedStatement ps = con.prepareStatement(sql);
          int numLinesChanged = ps.executeUpdate();
          if (numLinesChanged == 1)
          {
            return true;
          }
          
          return false;
	}

        /**
         * Creates a new entry in the Fine table, where each column matches
         * the corresponding attribute of this Fine object.  The fid will be
         * generated in the database, and the fid of this object will be mutated
         * to hold the new fid.
         * @return true if the entry was successfully created otherwise false.
         * @throws NullPointerException if Borrowing is not initialized
         */
	@Override
	public boolean insert()  throws SQLException
        {
          String sql = "INSERT INTO Fine VALUES("
                  + "fidCounter.nextval,"
                  + amount+','
                  + "?,"
                  + "?,"
                  + borrowing.getBorid()+')';
          
          PreparedStatement ps = con.prepareStatement(sql);
          int paramIndex = 1;
          java.sql.Date sqlIssuedDate = (issuedDate == null) ?
                  null : new java.sql.Date(issuedDate.getTime().getTime());
          ps.setDate(paramIndex++, sqlIssuedDate);
          java.sql.Date sqlPaidDate = (paidDate == null) ?
                  null : new java.sql.Date(paidDate.getTime().getTime());
          ps.setDate(paramIndex++, sqlPaidDate);
          int numLinesInserted = ps.executeUpdate();
          if (numLinesInserted == 1)
          {
            ps.close();
            ps = con.prepareStatement("SELECT fidCounter.currval FROM DUAL");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
              fid = rs.getInt(1);
              return true;
            }
          }
          
          return false;
	}

	@Override
        /**
         * Gets every entry of the Fine table and creates a Fine object for it.
         * All objects are stored in a collection and returned.
         * @return a collection of all Fines.
         */
	public Collection<Table> getAll()  throws SQLException
        {
          ArrayList<Table> allFines = new ArrayList<Table>();
          PreparedStatement ps = con.prepareStatement("SELECT * FROM Fine");
          ResultSet rs = ps.executeQuery();
          while (rs.next())
          {
            allFines.add((Table) new Fine(rs));
          }
          
          return allFines;
	}

        /**
         * Gets the Fine tuple whose fid matches this object's fid and creates
         * a new Fine object that represents the tuple.
         * @return a Fine object representing the Fine tuple in the database
         *  with this object's fid.
         */
	@Override
	public Table get() throws SQLException
        {
//            Statement stmt1 = Conn.getInstance().getConnection().createStatement();
//		ResultSet rs = stmt1.executeQuery("SELECT * FROM Fine WHERE fid ='"+this.getFid() + "'");

          String sql = "SELECT * FROM Fine WHERE fid = '"+ fid + "'";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          if (rs.next())
          {
            return new Fine(rs);
          }
          
          return null;
	}
        
        /**
         * Gets all fines for the provided borrower
         * @param borrower
         * @return
         * @throws SQLException 
         */
        public Collection<Table> get(Borrower borrower) throws SQLException
        {
          String sql =
                  "SELECT fid, amount, issuedDate, paidDate, F.borid "
                  + "FROM Fine F, Borrowing R "
                  + "WHERE bid = "+borrower.getBid()+" AND F.borid=R.borid";
          ArrayList<Table> fines = new ArrayList<Table>();
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          final int FID = 1;
          final int AMOUNT = 2;
          final int ISSUEDDATE = 3;
          final int PAIDDATE = 4;
          final int BORID = 5;
          while (rs.next())
          {
            int dbFid = rs.getInt(FID);
            int dbAmount = rs.getInt(AMOUNT);
            java.sql.Date sqlIssuedDate = rs.getDate(ISSUEDDATE);
            Calendar dbIssuedDate = (sqlIssuedDate == null) ?
                    null : new GregorianCalendar();
            if (dbIssuedDate != null)
            {
              dbIssuedDate.setTime(sqlIssuedDate);
            }
            java.sql.Date sqlPaidDate = rs.getDate(PAIDDATE);
            Calendar dbPaidDate = (sqlPaidDate == null) ?
                    null : new GregorianCalendar();
            if (dbPaidDate != null)
            {
              dbPaidDate.setTime(sqlPaidDate);
            }
            int dbBorid = rs.getInt(BORID);
            Borrowing dbBorrowing = new Borrowing();
            dbBorrowing.setBorid(dbBorid);
            dbBorrowing = (Borrowing) dbBorrowing.get();
            fines.add(new Fine(dbFid, dbAmount, dbIssuedDate, dbPaidDate, dbBorrowing));
          } // end while
          return fines;
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
   * @param fid the fid to set
   */
  public void setFid(int fid) {
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
   * @throws IllegalArgumentException if the amount is negative
   */
  public void setAmount(Integer amount) {
    if (amount != null && amount < 0)
    {
      String msg = "Fine amounts must be non-negative";
      throw new IllegalArgumentException(msg);
    }
    this.amount = amount;
  }

  /**
   * @return the issueDate
   */
  public Calendar getIssuedDate() {
    return issuedDate;
  }

  /**
   * @param issueDate the issueDate to set
   */
  public void setIssuedDate(Calendar issueDate) {
    this.issuedDate = issueDate;
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
    verifyDateOrder(issuedDate, paidDate);
    this.paidDate = paidDate;
  }

  /**
   * @return the borrowing
   */
  public Borrowing getBorrowing() {
    return borrowing;
  }

  /**
   * @param b the borrowing to set
   */
  public void setBorrowing(Borrowing b) {
    this.borrowing = b;
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
  public static void main(String[] args) throws SQLException {
    // get
   
    Fine f = new Fine();
    /*
     * f.setFid(2);
    f = (Fine) f.get();
    System.out.println("fine:\n"+f);
    
    f.setFid(4);
    f.setPaidDate(new GregorianCalendar());
    f.update();
    
    f.setFid(3);
    if (f.delete())
    {
      // do nothing
    }
    else
    {
      System.out.println("Failed to delete fine 3");
    }
    
    System.out.println((f.delete()) ? "error in delete" : "successfully failed to delete");
    
    System.out.println("Should see fine 4 with today's' date for paiddate"
            + " and should see fine 3 deleted");
     * 
     
    
    f.fid = -1;
    f.amount = 1337;
    f.issuedDate = new GregorianCalendar();
    f.paidDate = null;
    f.borrowing = new Borrowing();
    f.borrowing.setBorid(12);
    f.insert();
    System.out.println("expect new fid to be 6"
            + "\nactual value is: "+f.fid);
    */
    
    for (Table ff : f.getAll())
    {
      System.out.println(ff);
      System.out.println("\n\n");
    }
    
    String[][] fineTable = f.display();
    for (int i = 0; i < fineTable.length; i++)
    {
      for (int j = 0; j < fineTable[i].length; j++)
      {
        System.out.print(fineTable[i][j] + '\t');
      }
      System.out.println();
    }
  }

}

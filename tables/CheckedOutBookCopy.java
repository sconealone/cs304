/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import users.Conn;

/**
 * This class represents the CheckedOutBookCopy view in the database.
 * Its flagOverdue method checks all the checked out book copies and flags
 * the overdue ones by changing their statuses to 'overdue'
 * The addition of the 'overdue' status is an assumption on my part that
 * this is the simplest way to flag an overdue item.
 * TODO uncomment the inheritance of bookcopy when bookcopy is done
 * 
 * @author Mitch
 */
public class CheckedOutBookCopy //extends BookCopy
{
  private String subjectToFilterBy;
  
  /**
   * Creates a new CheckedOutBookCopy object
   */
  public CheckedOutBookCopy()
  {
    //super();
  }
  
  /**
   * Constructs a new CheckedOutBookCopy
   * @param copyNo
   * @param status
   * @param book
   * @param subjectToFilterBy 
   */
  public CheckedOutBookCopy(  String copyNo,
                              String status,
                              Book book,
                              String subjectToFilterBy)
  {
    //super(copyNo, status, book);
    this.subjectToFilterBy = subjectToFilterBy;
  }
  
  /**
   * Goes through the database and flags all book copies that are out and 
   * overdue as overdue
   * @return true if any tuples were flagged overdue, and false if none were
   * overdue
   * @throws SQLException if the database cannot be reached
   */
  public boolean flagOverdue() throws SQLException
  {
    String sql = "UPDATE BookCopy C \n"
            + "SET status = 'overdue' \n"
            + "WHERE EXISTS("
              +"SELECT * FROM Borrowing R, Borrower B, BorrowerType T WHERE \n"
              + "C.callNumber = R.callNumber AND "
              + "C.copyNo = R.copyNo AND "
              + "R.bid = B.bid AND "
              + "B.type = T.type AND "
              + "C.status = 'out' AND "
              + "(R.outDate + T.bookTimeLimit) < SYSDATE)";
    Connection con = Conn.getInstance().getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
    int copiesUpdated = ps.executeUpdate();
    return copiesUpdated > 0;
  }
  
  /**
   * Returns a representation of the CheckedOutBookCopy view as a 2D array
   * of strings where the first row is the header of the view, with each
   * element representing a column name.
   * @return
   * @throws SQLException 
   */
  //@Override
  public String[][] display() throws SQLException 
  {
    boolean shouldTheResultBeFiltered = subjectToFilterBy != null;
    String sql = "SELECT DISTINCT callNumber, copyNo, outDate, dueDate,"
            + ((shouldTheResultBeFiltered) ? "subject," : "")
            + " status";
    sql += "\nFROM CheckedOutBookCopy";
    if (shouldTheResultBeFiltered)
    {
      sql += "\nWHERE subject = ?";
    }
    Connection con = Conn.getInstance().getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
    if (shouldTheResultBeFiltered)
    {
      ps.setString(1, subjectToFilterBy);
    }
    ResultSet rs = ps.executeQuery();
    ResultSetMetaData md = rs.getMetaData();
    
    int numCols = md.getColumnCount();
    ArrayList<String[]> checkedOutBookCopiesGrowable
            = new ArrayList<String[]>();
    String[] header = new String[numCols];
    
    for (int i = 0; i < numCols; i++)
    {
      header[i] = md.getColumnName(i+1);
    }
    checkedOutBookCopiesGrowable.add(header);
    
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    
    
    while (rs.next())
    {
      String[] tuple = new String[numCols];
      int paramIndex = 1;
      
      // call number
      tuple[paramIndex-1] = rs.getString(paramIndex);
      paramIndex++;
      
      // copy number
      tuple[paramIndex-1] = rs.getString(paramIndex);
      paramIndex++;
      
      // out date
      java.sql.Date sqlDate = rs.getDate(paramIndex);
      tuple[paramIndex-1] = ((sqlDate==null) ? 
              "null" : df.format(new java.util.Date(sqlDate.getTime())));
      paramIndex++;
      
      // due date
      sqlDate = rs.getDate(paramIndex);
      tuple[paramIndex-1] = ((sqlDate==null) ? 
              "null" : df.format(new java.util.Date(sqlDate.getTime())));
      paramIndex++;
      
      // subject
      if (shouldTheResultBeFiltered)
      {
        tuple[paramIndex-1] = rs.getString(paramIndex);
        paramIndex++;
      }
      
      // status
      tuple[paramIndex-1] = rs.getString(paramIndex);
      paramIndex++;
      
      checkedOutBookCopiesGrowable.add(tuple);
    } // end while
    
    int numRows = checkedOutBookCopiesGrowable.size();
    String[][] checkedOutBookCopies = new String[numRows][];
    for (int i = 0; i < numRows; i++)
    {
      checkedOutBookCopies[i] = checkedOutBookCopiesGrowable.get(i);
    }
    return checkedOutBookCopies;
    
  }

  /**
   * @return the subjectToFilterBy
   */
  public String getSubjectToFilterBy() {
    return subjectToFilterBy;
  }

  /**
   * @param subjectToFilterBy the subjectToFilterBy to set
   */
  public void setSubjectToFilterBy(String subjectToFilterBy) {
    this.subjectToFilterBy = subjectToFilterBy;
  }
  
  public static void main(String[] args) throws Exception {
    CheckedOutBookCopy bc = new CheckedOutBookCopy();
    String[][] table = bc.display();
    for (int i = 0; i < table.length; i++)
    {
      for (int j = 0; j < table[0].length; j++)
      {
        System.out.print(table[i][j] + '\t');
      }
      System.out.println();
    }
    System.out.println();
    bc.flagOverdue();
    
    table = bc.display();
    for (int i = 0; i < table.length; i++)
    {
      for (int j = 0; j < table[0].length; j++)
      {
        System.out.print(table[i][j] + '\t');
      }
      System.out.println();
    }
    System.out.println();
    bc.setSubjectToFilterBy("Science");
    table = bc.display();
    for (int i = 0; i < table.length; i++)
    {
      for (int j = 0; j < table[0].length; j++)
      {
        System.out.print(table[i][j] + '\t');
      }
      System.out.println();
    }
    
    
  }
  
}

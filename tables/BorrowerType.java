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
import java.util.ArrayList;
import users.Conn;

/**
 * Represents the BorrowerType table in the database
 * @author Mitch
 */
public class BorrowerType implements Displayable
{
  /**
   * The BorrowerType type
   */
  private String type;
  
  /**
   * The number of weeks a borrower of this type can borrow the book for
   */
  private int bookTimeLimit;
  
  private Connection con;
  
  
  public BorrowerType()
  {
    con = Conn.getInstance().getConnection();
  }
  
  /**
   * Creates a new HasAuthor from a result set. internal use only.
   * Call next() on rs before passing
   * @param rs 
   */
  private BorrowerType(ResultSet rs) throws SQLException
  {
    con = Conn.getInstance().getConnection();
    int paramIndex = 1;
    type = rs.getString(paramIndex++);
    bookTimeLimit = rs.getInt(paramIndex++);
    
  }
  
  /**
   * Creates a new entry in the database for this BorrowerType table.
   * A new borrower type will be added to the table along with its time
   * limit.
   * @return true if the BorrowerType was successfully inserted
   * otherwise false
   */
  public boolean insert(String type, int limit) throws SQLException {
	  try {
			PreparedStatement ps = con.prepareStatement ("INSERT INTO BorrowerType " +
			"(type,bookTimeLimit) VALUES (?,?)"); 
			
			ps.setString(1, type);
			ps.setInt(2, limit);
			ps.executeUpdate();
			return true;
	  } catch (SQLException e) {
		  System.out.println(e.getMessage());
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
   * Deletes the tuple in the BorrowerType table whose primary key
   * corresponds to this BorrowerType's type. All other attributes are
   * ignored.
   * @return true if the tuple was successfully deleted, otherwise false
   */
	public boolean delete(String type) throws SQLException {
      String sql = "DELETE FROM BorrowerType WHERE type = " + type;
      
      PreparedStatement ps = con.prepareStatement(sql);
      int numRowsDeleted = ps.executeUpdate();
      ps.close();
      return numRowsDeleted == 1;
      
	}
  
  /**
   * displays the table as a 2D array of Strings. The first row is the 
   * column bookTimeLimits
   * @return
   * @throws SQLException 
   */
  @Override
  public String[][] display() throws SQLException {
    String sql = "SELECT * FROM BorrowerType";
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    
    ResultSetMetaData md = rs.getMetaData();
    int numCol = md.getColumnCount();
    String[] colNames = new String[numCol];
    colNames[0] = md.getColumnName(1);
    colNames[1] = md.getColumnName(2);
    
    ArrayList<String[]> tableGrowable = new ArrayList<String[]>();
    tableGrowable.add(colNames);
    
    while (rs.next())
    {
      BorrowerType h = new BorrowerType(rs);
      String[] tuple = new String[numCol];
      tuple[0] = h.type;
      tuple[1] = ""+h.bookTimeLimit;
      tableGrowable.add(tuple);
    }
    int numRow = tableGrowable.size();
    String[][] table = new String[numRow][];
    for (int i = 0; i < numRow; i++)
    {
      table[i] = tableGrowable.get(i);
    }
    return table;
  }
  
  
}

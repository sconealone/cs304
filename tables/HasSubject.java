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
import java.sql.Statement;
import java.util.ArrayList;
import users.Conn;

/**
 * Represents the HasSubject table in the database
 * @subject Mitch
 */
public class HasSubject implements Displayable
{
  /**
   * The subject subject
   */
  private String subject;
  
  /**
   * The call number
   */
  private String callNumber;
  
  private Connection con;
  
  
  public HasSubject()
  {
    con = Conn.getInstance().getConnection();
  }
  
  /**
   * Creates a new HasSubject from a result set. internal use only.
   * Call next() on rs before passing
   * @param rs 
   */
  private HasSubject(ResultSet rs) throws SQLException
  {
    con = Conn.getInstance().getConnection();
    int paramIndex = 1;
    callNumber = rs.getString(paramIndex++);
    subject = rs.getString(paramIndex++);
    
  }
  
  /**
   * create a HasSubject object and add it to the database
   * 
   * @param subArg
   * @param callNumArg
   * @throws SQLException
   */
  public HasSubject(String subArg, String callNumArg) throws SQLException{
	  con = Conn.getInstance().getConnection();
	  String sql = "INSERT INTO HasSubject VALUES('" +callNumArg + "','" +subArg+"')";
	  Statement stmt = con.createStatement();
	  stmt.execute(sql);
	  callNumber = callNumArg;
	  subject = subArg;
  }
  
  
  /**
   * displays the table as a 2D array of Strings. The first row is the 
   * column subjects
   * @return
   * @throws SQLException 
   */
  @Override
  public String[][] display() throws SQLException {
    String sql = "SELECT * FROM HasSubject";
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
      HasSubject h = new HasSubject(rs);
      String[] tuple = new String[numCol];
      tuple[0] = h.callNumber;
      tuple[1] = h.subject;
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
  
  //testing
  public static void main(String[] args) throws SQLException{
    HasSubject h = new HasSubject();
    String[][] d = h.display();
    for (int i = 0; i < d.length; i++)
    {
      for (int j = 0; j < d[0].length; j++)
      {
        System.out.print(d[i][j] + '\t');
      }
      System.out.println();
    }
  }
  
}

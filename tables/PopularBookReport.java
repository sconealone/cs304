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
 * This class represents a report of popular books.
 * It is used to generate a report with the most popular books
 * in a given year.  The report takes the form of a table and 
 * lists the top n books that were borrowed the most times during the
 * year.  The books are ordered by the number of times they were borrowed.
 * @author Mitch
 */
public class PopularBookReport implements Displayable
{
  private int year;
  private int n;
  
  public PopularBookReport(int year, int n)
  {
    this.year = year;
    this.n = n;
  }

  /**
   * Returns a 2D array of strings representing a table of
   * books.  These are the top n books that were borrowed the most times
   * during the year.  The books are ordered by the number of times
   * they were borrowed.
   * @return
   * @throws SQLException 
   */
  @Override
  public String[][] display() throws SQLException 
  {
    String sql = "SELECT callNumber, COUNT(borid) AS timesBorrowed, outDate AS year \n"
            + "FROM Borrowing\n"
            + "WHERE to_char(outDate,'YYYY') = '"+year+"'\n"
            + "GROUP BY callNumber, outDate\n"
            + "ORDER BY timesBorrowed DESC";
    Connection con = Conn.getInstance().getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    ResultSetMetaData md = rs.getMetaData();
    
    ArrayList<String[]> reportGrowable = new ArrayList<String[]>();
    int numCols = md.getColumnCount()+1;
    String[] header = new String[numCols];
    int i = 0;
    header[i++] = "RANK";
    header[i] = md.getColumnName(i++);
    header[i] = md.getColumnName(i++);
    header[i] = md.getColumnName(i++);
    reportGrowable.add(header);
    
    int j= 1;
    DateFormat df = new SimpleDateFormat("yyyy");
    while (j <= n && rs.next())
    {
      int paramIndex = 0;
      String[] tuple = new String[numCols];
      // rank
      tuple[paramIndex++]  =""+j;
      
      // call number
      tuple[paramIndex] = ""+rs.getString(paramIndex++);
      
      // times borrowed
      tuple[paramIndex] = ""+rs.getString(paramIndex++);
      
      // year
      tuple[paramIndex] = 
              df.format(new java.util.Date(rs.getDate(paramIndex++).getTime()));
      reportGrowable.add(tuple);
      j++;
    }
    
    while (j <= n)
    {
      String[] tuple = new String[numCols];
      tuple[0] = ""+j;
      for (int ii = 1; ii < numCols; ii++)
      {
        tuple[ii] = "null";
      }
      j++;
      reportGrowable.add(tuple);
    }
    
    int numRows = reportGrowable.size();
    String[][] report = new String[numRows][];
    for (int k = 0; k < numRows; k++)
    {
      report[k] = reportGrowable.get(k);
    }
    return report;
  }

  /**
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * @param year the year to set
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * @return the n
   */
  public int getN() {
    return n;
  }

  /**
   * @param n the n to set
   */
  public void setN(int n) {
    this.n = n;
  }
  
  public static void main(String[] args) throws Exception
  {
    PopularBookReport br = new PopularBookReport(2001, 10);
    display(br.display());
  }
  
  private static void display(String[][] displayTable)
  {
    for (String[] a : displayTable)
    {
      for (String b : a)
      {
        System.out.print(b + '\t');
      }
      System.out.println();
    }
  }
  
}

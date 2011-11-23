/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import users.Conn;

/**
 * This class will parse a .sql file, separating each query
 * and executing it.
 * @author Mitch
 */
public class InsertParser {
  
  private String filename;
  private final String DEFAULT_FILE = "insert_teset_vlues.sql";
  
  public InsertParser()
  {
    filename = DEFAULT_FILE;
  }
  
  public InsertParser(String filename)
  {
    this.filename = filename;
  }
  
  public String getFilename()
  {
    return filename;
  }
  
  public void setFilename(String filename)
  {
    this.filename = filename;
  }
  
  /**
   * Opens a file and parses it
   */
  public void parse()
  {
    String allQueriesInOneString = read();
    String[] allQueriesInOneArray = allQueriesInOneString.split(";");
    execute(allQueriesInOneArray);
  }
  
  private String read()
  {
    FileReader fr = null;
    try
    {
      fr = new FileReader(filename);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found."
              + " Using default file 'insert_teset_values.sql'");
      try
      {
        fr = new FileReader(DEFAULT_FILE);
      }
      catch (FileNotFoundException fnf)
      {
        System.out.println("Default file not found. "
                + "Exiting.");
        System.exit(-1);
      }
    } // end init FileREader
    
    Scanner fin = new Scanner(fr);
    
    String queries="";
    while (fin.hasNext())
    {
      queries += fin.nextLine();
    }
    return queries;
  }
  
  /**
   * Executes every statement in an array.
   */
  private void execute(String[] queries)
  {
    Connection c = Conn.getInstance().getConnection();
    try
    {
      Statement s = c.createStatement();
      for (int i = 0; i < queries.length; i++)
      {
        s.execute(queries[i]);
      }
    }
    catch (SQLException e)
    {
      
    }
  }
}

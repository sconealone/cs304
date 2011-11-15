/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton connection
 * usage:
 * java.sql.Connection conn = Conn.getInstance().getConnection();
 * 
 * @author Mitch
 */
public class Conn {
  private static Conn instance;
  private Connection conn;
  
  /**
   * Accessor for the Conn
   * @return 
   */
  public static Conn getInstance()
  {
    return instance;
  }
  
  static
  {
    instance = new Conn();
  }
  
  /**
   * Constructor
   */
  private Conn()
  {
      try 
      {
	// Load the Oracle JDBC driver
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      }
      catch (SQLException e)
      {
        // handle couldn't find driver
      }
      // somehow get the username and password
      // get rid of the hard code
      String username = "ora_c7e8";
      String password = "a84148014";
      connect(username, password);
  }
  
  private void connect(String username, String password)
  {
      String connectURL = "jdbc:oracle:thin:@localhost:1521:ug";
    try 
    {
      conn = DriverManager.getConnection(connectURL, username, password);
    } 
    catch (SQLException ex) 
    {
      // handle couldn't connect
    }
  }
}

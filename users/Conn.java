
/*
a * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Singleton connection
 * usage:
 * java.sql.Connection conn = Conn.getInstance().getConnection();
 * 
 * Need to add actual exception handling and a way to get the username
 * and password instead of hardcoding it.
 * 
 * Note: when you are implementing user functionality, you will need to
 * disable auto-commit for java.sql.Connection conn.
 * eg
 * 
 * java.sql.Connection mycon = Conn.getInstance().getConnection();
 * mycon.setAutoCommit(false);
 * // your transaction code here
 * mycon.setAutoCommit(true);
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
  
  /**
   * Accessor for the jdbc connection
   * @return 
   */
  public Connection getConnection()
  {
    return conn;
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
        JOptionPane.showMessageDialog(null, "Can't find driver.\nInstall the driver.", "Error", JOptionPane.ERROR_MESSAGE);
      }
      
      //other db
      connect(Secret.getUsername(), Secret.getPassword());
 }
  
  private void connect(String username, String password)
  {
      int attemptCount = 0;
      while (attemptCount < 1)
      {
        try 
        {
          if (attemptCount > 0)
          {
            JOptionPane.showMessageDialog(null, "Set up SSH tunnel and click OK when ready", "", JOptionPane.INFORMATION_MESSAGE);
          }
          attemptCount++;
          conn = DriverManager.getConnection(Secret.getURL(), username, password);
          return;
        } 
        catch (SQLException ex) 
        {
          // handle couldn't connect
          JOptionPane.showMessageDialog(null, "Can't connect to Oracle.\nSet SSH tunnel and reconnect from File Menu.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
  }
  
  public void reconnect()
  {
    try 
    {
      conn = DriverManager.getConnection(Secret.getURL(), Secret.getUsername(), Secret.getPassword());
      JOptionPane.showMessageDialog(null, "Successfully reconnected!\nEnjoy.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } 
    catch (SQLException ex) 
    {
      // handle couldn't connect
      JOptionPane.showMessageDialog(null, "Could not connect to database.\nPlease try again later.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
}

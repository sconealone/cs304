package tables;

import java.sql.SQLException;

/**
 * This interface is for a database table that can be displayed
 * Used by tables and views that shouldn't be updated or inserted or deleted
 * @author Mitch
 */
public interface Displayable 
{
  public String[][] display() throws SQLException;
}

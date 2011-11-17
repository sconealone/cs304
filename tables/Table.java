package tables;

import java.sql.SQLException;
import java.util.Collection;

public interface Table {
	
	public String[][] display() throws SQLException;
	
	public void update() throws SQLException;
	
	public boolean delete() throws SQLException;
	
	public boolean insert() throws SQLException;
	
	public Collection<Table> getAll() throws SQLException;
	
	public Table get() throws SQLException;
	
}

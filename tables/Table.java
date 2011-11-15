package tables;

import java.util.Collection;

public interface Table {
	
	public String[][] display();
	
	public void update();
	
	public boolean delete();
	
	public boolean insert();
	
	public Collection<Table> getAll();
	
	public Table get();
	
}

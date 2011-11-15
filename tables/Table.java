package tables;

import java.util.Collection;

public interface Table {
	
	public void display(String[][] arg);
	
	public void update();
	
	public boolean delete();
	
	public boolean insert();
	
	public Collection<Table> getAll();
	
	public Table get();
	
}

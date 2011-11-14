package tables;

public interface Table {
	
	public void display(String[][] arg);
	
	public void ndate();
	
	public boolean delete();
	
	public boolean insert();
	
	public Table getAll();
	
	public Table get();
	
}

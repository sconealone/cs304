package tables;

public interface Table {
	
	public void display(String[][] arg);
	
	public void update();
	
	public boolean delete();
	
	public boolean insert();
	
	public Table getAll();
	
	public Table get();
	
}

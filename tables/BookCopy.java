package tables;

import java.util.Collection;

public class BookCopy implements Table {

	private String copyNo;
	private String status;
	private Book b;
	
	@Override
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Table> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table get() {
		// TODO Auto-generated method stub
		return null;
	}

  /**
   * @return the copyNo
   */
  public String getCopyNo() {
    return copyNo;
  }

  /**
   * @param copyNo the copyNo to set
   */
  public void setCopyNo(String copyNo) {
    this.copyNo = copyNo;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the b
   */
  public Book getB() {
    return b;
  }

  /**
   * @param b the b to set
   */
  public void setB(Book b) {
    this.b = b;
  }

}

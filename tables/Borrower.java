package tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;

public class Borrower implements Table {

	private Integer bid;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String emailAddress;
	private Integer sinOrStNum;
	private Calendar expiryDate;
	private Integer bookTimeLimit;
	
	Connection con;
	Statement stmt;
	
	
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
	public Collection<Table> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert() {
		try {
			stmt.executeUpdate("INSERT INTO Borrower VALUES (" + getBid() + ", " + getPassword() + ", " +
			getName() + ", " + getAddress() + ", " + getPhone() + ", " + getEmailAddress() + ", " +	getSinOrStNum() + 
			", " +getExpiryDate() + ", " +getBookTimeLimit() + ")");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

  /**
   * @return the bid
   */
  public Integer getBid() {
    return bid;
  }

  /**
   * @param bid the bid to set
   */
  public void setBid(Integer bid) {
    this.bid = bid;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone the phone to set
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return the emailAddress
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * @param emailAddress the emailAddress to set
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * @return the sinOrStNum
   */
  public Integer getSinOrStNum() {
    return sinOrStNum;
  }

  /**
   * @param sinOrStNum the sinOrStNum to set
   */
  public void setSinOrStNum(Integer sinOrStNum) {
    this.sinOrStNum = sinOrStNum;
  }

  /**
   * @return the expiryDate
   */
  public Calendar getExpiryDate() {
    return expiryDate;
  }

  /**
   * @param expiryDate the expiryDate to set
   */
  public void setExpiryDate(Calendar expiryDate) {
    this.expiryDate = expiryDate;
  }

  /**
   * @return the bookTimeLimit
   */
  public Integer getBookTimeLimit() {
    return bookTimeLimit;
  }

  /**
   * @param bookTimeLimit the bookTimeLimit to set
   */
  public void setBookTimeLimit(Integer bookTimeLimit) {
    this.bookTimeLimit = bookTimeLimit;
  }


}

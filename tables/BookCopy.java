package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import users.Conn;

public class BookCopy implements Table {

	private String copyNo;
	private String status;
	private Book b;

	private Connection c;
	private Statement stmt;

	public BookCopy() {
		c = Conn.getInstance().getConnection();
	}

	public BookCopy(String copyNo, String status, Book b) {
		this.copyNo = copyNo;
		this.status = status;
		this.b = b;

		c = Conn.getInstance().getConnection();
	}

	@Override
	public String[][] display() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() throws SQLException {
		PreparedStatement ps;

		ps = c.prepareStatement("UPDATE bookCopy SET copyNo = ?, status = ? WHERE callNo = ?");

		ps.setString(3, b.getCallNumber());
		ps.setString(1, copyNo);
		ps.setString(2, status);
		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
			// Throw Exception
		}
		c.commit();
		ps.close();
	}

	@Override
	public boolean delete() throws SQLException {
		PreparedStatement ps;

		ps = c.prepareStatement("DELETE FROM bookCopy WHERE callNo = ?");

		ps.setString(1, b.getCallNumber());

		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
			// throw exception
		}

		c.commit();
		ps.close();

		return false;
	}

	@Override
	public boolean insert() throws SQLException {
		PreparedStatement ps;

		ps = c.prepareStatement("UPDATE bookCopy SET copyNo = ?, status = ? WHERE callNo = ?");

		ps.setString(3, b.getCallNumber());
		ps.setString(1, copyNo);
		ps.setString(2, status);
		
		int rowCount = ps.executeUpdate();
		if (rowCount == 0) {
			// Throw Exception
		}
		
		c.commit();
		ps.close();

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
	 * @param copyNo
	 *            the copyNo to set
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
	 * @param status
	 *            the status to set
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
	 * @param b
	 *            the b to set
	 */
	public void setB(Book b) {
		this.b = b;
	}

}

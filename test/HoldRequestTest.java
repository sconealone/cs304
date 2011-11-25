package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tables.*;

public class HoldRequestTest {
	private HoldRequest testhr;
	private HoldRequest testhr1 = new HoldRequest();
	private HoldRequest testhr2 = new HoldRequest();
	private HoldRequest testhr3 = new HoldRequest();
	private HoldRequest testhr4 = new HoldRequest();
	private HoldRequest testhr5 = new HoldRequest();
	private Book b1 = new Book();
	private Book b2 = new Book();
	private Borrower borr1 = new Borrower();
	private Borrower borr2 = new Borrower();
	private Borrower borr3 = new Borrower();
	private Calendar cal1 = Calendar.getInstance();
	private Calendar cal2 = Calendar.getInstance();
	private Calendar cal3 = Calendar.getInstance();
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

	@Before
	public void setUp() throws Exception {
		populateDB();
	}

	private void populateDB() {	
		b1.setCallNumber("CallNo1");
		b2.setCallNumber("CallNo2");

		borr1.setBid(1);
		borr2.setBid(2);
		borr3.setBid(3);
	
		cal1.set(Calendar.YEAR, 2011);
		cal1.set(Calendar.MONTH, 2);
		cal1.set(Calendar.DATE, 10);
		
		cal2.set(Calendar.YEAR, 2011);
		cal2.set(Calendar.MONTH, 2);
		cal2.set(Calendar.DATE, 20);
		
		cal3.set(Calendar.YEAR, 2011);
		cal3.set(Calendar.MONTH, 4);
		cal3.set(Calendar.DATE, 20);
		
		testhr1.setBorr(borr1);
		testhr1.setB(b1);
		testhr1.setIssueDate(cal1);
		
		testhr2.setBorr(borr2);
		testhr2.setB(b1);
		testhr2.setIssueDate(cal2);
		
		testhr3.setBorr(borr1);
		testhr3.setB(b2);
		testhr3.setIssueDate(cal3);
		
		try {
			b1.insert();
			b2.insert();
			borr1.insert();
			borr2.insert();
			borr3.insert();
			
			testhr1.insert();
			testhr2.insert();
			testhr3.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@After
	public void tearDown() throws Exception {
		clearDB();
	}

	private void clearDB() throws SQLException {
		testhr1.delete();
		testhr2.delete();
		testhr3.delete();
		testhr4.delete();
		testhr5.delete();
		b1.delete();
		b2.delete();
		borr1.delete();
		borr2.delete();
		borr3.delete();
	}

	@Test
	public final void testHoldRequest() {
		testhr = new HoldRequest();
		
		assertNotNull((Object) testhr);
	}

	@Test
	public final void testHoldRequestCalendarBookBorrower() {
		testhr = new HoldRequest(new Borrower(), new Book(), Calendar.getInstance());
		
		assertNotNull((Object) testhr);
		assertTrue(testhr.getB() instanceof Book);
		assertTrue(testhr.getBorr() instanceof Borrower);
	}

	@Test
	public final void testDisplay() throws SQLException {
		String[][] result = testhr.display();
		
		assertEquals(result[1][0], borr1.getBid());
		assertEquals(result[1][1], b1.getCallNumber());
		assertEquals(result[1][2], sdf.format(cal1.getTime()));
		
		assertEquals(result[2][0], borr2.getBid());
		assertEquals(result[2][1], b1.getCallNumber());
		assertEquals(result[2][2], sdf.format(cal2.getTime()));	
	}

	@Test
	public final void testUpdate() throws SQLException {
		testhr2.setBorr(borr3);
		testhr2.update();
		testhr2.get();
		assertEquals(testhr2.getBorr(), borr3);
	}

	@Test
	public final void testDelete() throws SQLException {
		testhr.setHid(testhr2.getHid());
		testhr.get();
		testhr.delete();
		//testhr2 should not exist
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInsert() throws SQLException {
		testhr2.setBorr(borr2);
		testhr2.setB(b1);
		testhr2.setIssueDate(cal2);
		
		testhr2.insert();
		testhr.setHid(testhr2.getHid());
		
		assertEquals(testhr, testhr2);
	}

	@Test
	public final void testGetAll() throws SQLException {
		Collection<Table> hr = testhr1.getAll();
		assertTrue(hr.contains(testhr1));
		assertTrue(hr.contains(testhr2));
	}

	@Test
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllBorrower() throws SQLException {	
		Collection<Table> hr = testhr1.getAll(borr1);
		assertTrue(hr.contains(testhr1));
		assertFalse(hr.contains(testhr2));
		assertTrue(hr.contains(testhr3));
	}

	@Test
	public final void testGetAllBook() throws SQLException {
		Collection<Table> hr = testhr1.getAll(borr1);
		assertTrue(hr.contains(testhr1));
		assertTrue(hr.contains(testhr2));
		assertFalse(hr.contains(testhr3));
	}

	@Test
	public final void testGetAllBorrowerBook() throws SQLException {
		Collection<Table> hr = testhr1.getAll(borr1, b1);
		assertTrue(hr.contains(testhr1));
		assertFalse(hr.contains(testhr2));
		assertFalse(hr.contains(testhr3));
	}
}

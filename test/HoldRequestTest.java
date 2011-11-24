package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tables.*;

public class HoldRequestTest {
	HoldRequest testhr;

	@Before
	public void setUp() throws Exception {
		populateDB();
	}

	private void populateDB() {
		HoldRequest testhr1;
		HoldRequest testhr2;
		HoldRequest testhr3;
		HoldRequest testhr4;
		HoldRequest testhr5;
	}

	@After
	public void tearDown() throws Exception {
		clearDB();
	}

	private void clearDB() {
		// TODO Auto-generated method stub
		
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
	public final void testDisplay() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllBorrower() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllBook() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllBorrowerBook() {
		fail("Not yet implemented"); // TODO
	}
}

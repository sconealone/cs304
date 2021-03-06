/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.GregorianCalendar;
import tables.Book;
import java.util.Calendar;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tables.BookCopy;
import tables.Borrower;
import tables.Borrowing;
import tables.Table;
import static org.junit.Assert.*;

/**
 *
 * @author Mitch
 */
public class BorrowingTest {
  
  public BorrowingTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }
  
  @Before
  public void setUp() {
    (new tablesTest()).setup();
  }
  
  @After
  public void tearDown() {
    (new tablesTest()).clearDataBaseAfterTests();
  }

  

  /**
   * Test of get method, of class Borrowing.
   */
  @Test
  public void testGet() throws Exception {
    System.out.println("get");
    Borrowing instance = new Borrowing();
    instance.setBorid(1);
    BookCopy bc = new BookCopy();
    bc.setCopyNo("C1");
    Book b = new Book();
    b.setCallNumber("HI108 P73 1998");
    bc.setB(b);
    Borrower bor = new Borrower();
    bor.setBid(2);
    Borrowing result = (Borrowing) instance.get();
    assertEquals((int)result.getBorrower().getBid(),2);
    assertEquals((int)result.getBorid(),1);
    assertEquals(result.getBookCopy().getB().getCallNumber(), "HI108 P73 1998");
    assertEquals(result.getBookCopy().getCopyNo(), "C1");
    assertEquals(result.getOutDate(), new GregorianCalendar(1990,6,8));
    assertEquals(result.getInDate(), new GregorianCalendar(2003,3,24));
    
    Borrowing nonsenseBorid = new Borrowing();
    nonsenseBorid.setBorid(1000000);
    assertEquals(nonsenseBorid.get(),null);
  }
  
  /**
   * Test of update method, of class Borrowing.
   */
  @Test
  public void testUpdate() throws Exception {
    System.out.println("update");
    Borrowing instance = new Borrowing();
    instance.setBookCopy(new BookCopy("C1",new Book("HI108 P73 1998",null,null,null,null,0,null,null), null));
    Borrower borrower = new Borrower();
    borrower.setBid(1);
    instance.setBorid(1);
    instance.update();
    Borrowing gotBorrowing = (Borrowing) instance.get();
    assertEquals((int) gotBorrowing.getBorid(), 1);
    assertEquals(gotBorrowing.getBookCopy().getCopyNo(), "C1");
    assertEquals(gotBorrowing.getBookCopy().getB().getCallNumber(), "HI108 P73 1998");
    assertEquals((int) gotBorrowing.getBorrower().getBid(), 1);
    assertEquals(gotBorrowing.getOutDate(), null);
    assertEquals(gotBorrowing.getInDate(), null);
    
  }

  /**
   * Test of delete method, of class Borrowing.
   */
  @Test
  public void testDelete() throws Exception {
    System.out.println("delete");
    Borrowing instance = new Borrowing();
    boolean expResult = false;
    boolean result = instance.delete();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAll method, of class Borrowing.
   */
  @Test
  public void testGetAll() throws Exception {
    System.out.println("getAll");
    Borrowing instance = new Borrowing();
    Collection expResult = null;
    Collection result = instance.getAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of insert method, of class Borrowing.
   */
  @Test
  public void testInsert() throws Exception {
    System.out.println("insert");
    Borrowing instance = new Borrowing();
    boolean expResult = false;
    boolean result = instance.insert();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of display method, of class Borrowing.
   */
  @Test
  public void testDisplay() throws Exception {
    System.out.println("display");
    Borrowing instance = new Borrowing();
    String[][] expResult = null;
    String[][] result = instance.display();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  
  }
  
  @Test
  public void testGetDueDate() throws Exception
  {
    fail("Not implemented yet");
  }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
    Table expResult = null;
    Table result = instance.get();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  /**
   * Test of update method, of class Borrowing.
   */
  @Test
  public void testUpdate() throws Exception {
    System.out.println("update");
    Borrowing instance = new Borrowing();
    instance.setBookCopy(new BookCopy("C1",new Book("UL355 D360 1997",null,null,null,null,0,null,null), null));
    Borrower borrower = new Borrower();
    borrower.setBid(1);
    instance.setBorid(1);
    instance.update();
    Borrowing gotBorrowing = (Borrowing) instance.get();
    assertEquals((int) gotBorrowing.getBorid(), 1);
    assertEquals(gotBorrowing.getBookCopy().getCopyNo(), "C1");
    assertEquals(gotBorrowing.getBookCopy().getB().getCallNumber(), "UL355 D360 1997");
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
}

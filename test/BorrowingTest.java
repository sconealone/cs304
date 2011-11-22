/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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

  /**
   * Test of update method, of class Borrowing.
   */
  @Test
  public void testUpdate() throws Exception {
    System.out.println("update");
    Borrowing instance = new Borrowing();
    instance.update();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
   * Test of getBorid method, of class Borrowing.
   */
  @Test
  public void testGetBorid() {
    System.out.println("getBorid");
    Borrowing instance = new Borrowing();
    Integer expResult = null;
    Integer result = instance.getBorid();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBorid method, of class Borrowing.
   */
  @Test
  public void testSetBorid() {
    System.out.println("setBorid");
    Integer borid = null;
    Borrowing instance = new Borrowing();
    instance.setBorid(borid);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getOutDate method, of class Borrowing.
   */
  @Test
  public void testGetOutDate() {
    System.out.println("getOutDate");
    Borrowing instance = new Borrowing();
    Calendar expResult = null;
    Calendar result = instance.getOutDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setOutDate method, of class Borrowing.
   */
  @Test
  public void testSetOutDate() {
    System.out.println("setOutDate");
    Calendar outDate = null;
    Borrowing instance = new Borrowing();
    instance.setOutDate(outDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getInDate method, of class Borrowing.
   */
  @Test
  public void testGetInDate() {
    System.out.println("getInDate");
    Borrowing instance = new Borrowing();
    Calendar expResult = null;
    Calendar result = instance.getInDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setInDate method, of class Borrowing.
   */
  @Test
  public void testSetInDate() {
    System.out.println("setInDate");
    Calendar inDate = null;
    Borrowing instance = new Borrowing();
    instance.setInDate(inDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBookCopy method, of class Borrowing.
   */
  @Test
  public void testGetBookCopy() {
    System.out.println("getBookCopy");
    Borrowing instance = new Borrowing();
    BookCopy expResult = null;
    BookCopy result = instance.getBookCopy();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBookCopy method, of class Borrowing.
   */
  @Test
  public void testSetBookCopy() {
    System.out.println("setBookCopy");
    BookCopy bc = null;
    Borrowing instance = new Borrowing();
    instance.setBookCopy(bc);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBorrower method, of class Borrowing.
   */
  @Test
  public void testGetBorrower() {
    System.out.println("getBorrower");
    Borrowing instance = new Borrowing();
    Borrower expResult = null;
    Borrower result = instance.getBorrower();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBorrower method, of class Borrowing.
   */
  @Test
  public void testSetBorrower() {
    System.out.println("setBorrower");
    Borrower borr = null;
    Borrowing instance = new Borrowing();
    instance.setBorrower(borr);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class Borrowing.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    Borrowing instance = new Borrowing();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of main method, of class Borrowing.
   */
  @Test
  public void testMain() throws Exception {
    System.out.println("main");
    String[] args = null;
    Borrowing.main(args);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
}

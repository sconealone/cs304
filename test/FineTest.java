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
import tables.Borrowing;
import tables.Fine;
import tables.Table;
import static org.junit.Assert.*;

/**
 *
 * @author Mitch
 */
public class FineTest {
  
  public FineTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of toString method, of class Fine.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    Fine instance = new Fine();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of display method, of class Fine.
   */
  @Test
  public void testDisplay() throws Exception {
    System.out.println("display");
    Fine instance = new Fine();
    String[][] expResult = null;
    String[][] result = instance.display();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of update method, of class Fine.
   */
  @Test
  public void testUpdate() throws Exception {
    System.out.println("update");
    Fine instance = new Fine();
    instance.update();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of delete method, of class Fine.
   */
  @Test
  public void testDelete() throws Exception {
    System.out.println("delete");
    Fine instance = new Fine();
    boolean expResult = false;
    boolean result = instance.delete();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of insert method, of class Fine.
   */
  @Test
  public void testInsert() throws Exception {
    System.out.println("insert");
    Fine instance = new Fine();
    boolean expResult = false;
    boolean result = instance.insert();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAll method, of class Fine.
   */
  @Test
  public void testGetAll() throws Exception {
    System.out.println("getAll");
    Fine instance = new Fine();
    Collection expResult = null;
    Collection result = instance.getAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of get method, of class Fine.
   */
  @Test
  public void testGet() throws Exception {
    System.out.println("get");
    Fine instance = new Fine();
    Table expResult = null;
    Table result = instance.get();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFid method, of class Fine.
   */
  @Test
  public void testGetFid() {
    System.out.println("getFid");
    Fine instance = new Fine();
    Integer expResult = null;
    Integer result = instance.getFid();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setFid method, of class Fine.
   */
  @Test
  public void testSetFid() {
    System.out.println("setFid");
    Integer fid = null;
    Fine instance = new Fine();
    instance.setFid(fid);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAmount method, of class Fine.
   */
  @Test
  public void testGetAmount() {
    System.out.println("getAmount");
    Fine instance = new Fine();
    Integer expResult = null;
    Integer result = instance.getAmount();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAmount method, of class Fine.
   */
  @Test
  public void testSetAmount() {
    System.out.println("setAmount");
    Integer amount = null;
    Fine instance = new Fine();
    instance.setAmount(amount);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getIssuedDate method, of class Fine.
   */
  @Test
  public void testGetIssuedDate() {
    System.out.println("getIssuedDate");
    Fine instance = new Fine();
    Calendar expResult = null;
    Calendar result = instance.getIssuedDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setIssuedDate method, of class Fine.
   */
  @Test
  public void testSetIssuedDate() {
    System.out.println("setIssuedDate");
    Calendar issueDate = null;
    Fine instance = new Fine();
    instance.setIssuedDate(issueDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPaidDate method, of class Fine.
   */
  @Test
  public void testGetPaidDate() {
    System.out.println("getPaidDate");
    Fine instance = new Fine();
    Calendar expResult = null;
    Calendar result = instance.getPaidDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPaidDate method, of class Fine.
   */
  @Test
  public void testSetPaidDate() {
    System.out.println("setPaidDate");
    Calendar paidDate = null;
    Fine instance = new Fine();
    instance.setPaidDate(paidDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBorrowing method, of class Fine.
   */
  @Test
  public void testGetBorrowing() {
    System.out.println("getBorrowing");
    Fine instance = new Fine();
    Borrowing expResult = null;
    Borrowing result = instance.getBorrowing();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBorrowing method, of class Fine.
   */
  @Test
  public void testSetBorrowing() {
    System.out.println("setBorrowing");
    Borrowing b = null;
    Fine instance = new Fine();
    instance.setBorrowing(b);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
}

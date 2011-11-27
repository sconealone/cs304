/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tables.*;
import java.sql.ResultSetMetaData;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * insert_test_values_medium.sql
 * @author Mitch
 */
public class BookCopyTest {
  
  public BookCopyTest() {
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
   * Test of update method, of class BookCopy.
   */
  @Test
  public void testUpdate() throws Exception {
    System.out.println("update");
    BookCopy bcupdate = new BookCopy();
    Book bcupdatebook = new Book();
    bcupdatebook.setCallNumber("AK315 X383 1999");
    bcupdate.setB(bcupdatebook);
    bcupdate.setCopyNo("C2");
    bcupdate.setStatus("out");
    bcupdate.update();
    
    bcupdate.setStatus(null);
    bcupdate = (BookCopy) bcupdate.get();
    assertEquals(bcupdate.getStatus(), "out");
  }


  /**
   * Test of insert method, of class BookCopy.
   */
  @Test
  public void testInsert() throws Exception {
    System.out.println("insert");
    BookCopy bcinsert = new BookCopy();
    Book bcinsertbook = new Book();
    bcinsertbook.setCallNumber("WY273 P213 1986");
    bcinsert.setB(bcinsertbook);
    bcinsert.insert();
    bcinsert.setCopyNo("C"+200);
    bcinsert.insert();
    bcinsert.setCopyNo(null);
    bcinsert.insert();
    
    bcinsert.setCopyNo("C200");
    BookCopy c100 = (BookCopy)bcinsert.get();
    assertEquals(c100.getB().getCallNumber(), "WY273 P213 1986");
    bcinsert.setCopyNo("C201");
    BookCopy c101 = (BookCopy) bcinsert.get();
    System.out.println(c101.getB());
    System.out.println(c101.getB().getCallNumber());
    assertEquals(c101.getB().getCallNumber(), "WY273 P213 1986");
  }
  
  /**
   * Test of delete method, of class BookCopy.
   */
  @Test
  public void testDelete() throws Exception {
    System.out.println("delete");
    
    BookCopy bcdel = new BookCopy();
    Book bcdelbook = new Book();
    bcdelbook.setCallNumber("WY273 P213 1986");
    bcdel.setB(bcdelbook);
    bcdel.setCopyNo("C200");
    assertEquals(bcdel.delete(), true);
    bcdel.setCopyNo("C201");
    
    
    assertEquals(bcdel.delete(), true);
    assertEquals(bcdel.delete(),false);
  }




  /**
   * Test of get method, of class BookCopy.
   */
  @Test
  public void testGet_0args() throws Exception {
    System.out.println("get");
    
    BookCopy bcget = new BookCopy();
    Book bcgetbook = new Book();
    bcgetbook.setCallNumber("GV345 R202 1997");
    bcget.setB(bcgetbook);
    bcget.setCopyNo("C1");
    bcget = (BookCopy) bcget.get();
    
    assertEquals("GV345 R202 1997",bcget.getB().getCallNumber());
    assertEquals("C1", bcget.getCopyNo());
    assertEquals("in", bcget.getStatus());
  }
  
  public static void main(String[] args) throws Exception {
    (new BookCopyTest()).testInsert();
    (new BookCopyTest()).testDelete();
  }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tables.*;
import tables.HoldRequest;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mitch
 */
public class HoldRequestTest {
  
  public HoldRequestTest() {
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

  
  @Test
  public void testConstructor() throws Exception
  {
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    System.out.println("constructor");
    HoldRequest hr = new HoldRequest(100);
    assertEquals((int)hr.getHid(),100);
    assertEquals((int)hr.getBorr().getBid(),221);
    assertEquals(hr.getB().getCallNumber(),"NK279 E3 1988");
  }
  
 

  /**
   * Test of update method, of class HoldRequest.
   */
  @Test
  public void testUpdate() throws Exception {
    System.out.println("update");
    
          Book b = new Book();
          b.setCallNumber("ZI372 C30 1984");
          Borrower borr = new Borrower();
          borr.setBid(1);
          HoldRequest hr = new HoldRequest();
          hr.setHid(97);
          hr.setB(b);
          hr.setBorr(borr);
          hr.setIssueDate(new GregorianCalendar());
          hr.update();
          
          
          HoldRequest hr2 = new HoldRequest();
          hr2.setHid(97);
          hr2 =(HoldRequest) hr2.get();
          
          assertEquals(hr, hr2);
  }


  /**
   * Test of insert method, of class HoldRequest.
   */
  @Test
  public void testInsert() throws Exception {
    System.out.println("insert");
    Book book = new Book();
    book.setCallNumber("KH344 L18 2004");
    Borrower borrower = new Borrower();
    borrower.setBid(2);
    HoldRequest hr = new HoldRequest();
    hr.setB(book);
    hr.setBorr(borrower);
    hr.setIssueDate(new GregorianCalendar());
    hr.insert();
    int hid = hr.getHid();

    HoldRequest hr2 = new HoldRequest(101);
    assertEquals(hr2, hr);
  }
  /**
   * Test of delete method, of class HoldRequest.
   */
  @Test
  public void testDelete() throws Exception {
    
          HoldRequest deleteHR = new HoldRequest();
          for (int i = 101; i <= 103; i++)
          {
            deleteHR.setHid(i);
            System.out.println(deleteHR.delete());
          }
          for (int i = 101; i <= 103; i++)
          {
            deleteHR = new HoldRequest();
            deleteHR.setHid(i);
            deleteHR = (HoldRequest)deleteHR.get();
            assertEquals(deleteHR, null);
          }
          
  }



  /**
   * Test of get method, of class HoldRequest.
   */
  @Test
  public void testGet() throws Exception {
    HoldRequest foo = new HoldRequest();
    foo.setHid(98);
    foo = (HoldRequest) foo.get();
    assertEquals(foo.getB().getCallNumber(),"RF262 I354 1990");
    assertEquals((int)foo.getBorr().getBid(),13);
    Calendar dbdate = foo.getIssueDate();
    Calendar testdate = new GregorianCalendar(2000, 8, 10);
    assertEquals(dbdate.get(Calendar.YEAR), testdate.get(Calendar.YEAR));
    assertEquals(dbdate.get(Calendar.MONTH), testdate.get(Calendar.MONTH));
    assertEquals(dbdate.get(Calendar.DATE), testdate.get(Calendar.DATE));
  }

  /**
   * Test of getAll method, of class HoldRequest.
   */
  @Test
  public void testGetAll_Borrower() throws Exception {
    System.out.println("getAll");
    Borrower getAllBorrower = new Borrower();
    getAllBorrower.setBid(142);
    Collection<Table> hrs2 = (new HoldRequest()).getAll(getAllBorrower);
    for (Table t : hrs2)
    {
      assertEquals((int)((HoldRequest) t).getHid(),8);
    }
  }

  /**
   * Test of getAll method, of class HoldRequest.
   */
  @Test
  public void testGetAll_Book() throws Exception {
    HoldRequest hr = new HoldRequest();
    System.out.println("getAll");
    Book getAllBook = new Book();
    getAllBook.setCallNumber("LP353 N145 1983");
    Collection<Table> hrs = hr.getAll(getAllBook);
    for (Table t : hrs)
    {
      assertEquals((int)((HoldRequest) t).getHid(),8);
    }
  }

}

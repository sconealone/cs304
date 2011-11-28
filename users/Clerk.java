package users;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultListModel;

import tables.Book;
import tables.BookCopy;
import tables.BookCopyEvilTwinException;
import tables.Borrower;
import tables.Borrowing;
import tables.Fine;
import tables.HoldRequest;
import tables.NoSuchCopyException;
import tables.Table;

/**
 * This class encapsulates the functionality of the Clerk user. This class is
 * not complete yet.
 * 
 * @author Christiaan Fernando
 * 
 */
public class Clerk {

  /**
   * Adds a new borrower to the SQL table.
   * 
   * This function adds a borrower to the SQL table given the related
   * parameters. NOTE: Currently there is no way to get the BookTimeLimit
   * given a particular user.
   * 
   * 
   * @param password
   * @param name
   * @param address
   * @param phone
   * @param emailAddress
   * @param sinOrStNum
   * @param expiryDate
   * @param BookTimeLimit
   * @throws SQLException
   */
  public void addBorrower(String password, String name, String address,
                  String phone, String emailAddress, Integer sinOrStNum,
                  Calendar expiryDate, String type) throws SQLException {

          Borrower borr = new Borrower();
          borr.setAddress(address);
          borr.setEmailAddress(emailAddress);
          borr.setExpiryDate(expiryDate);
          borr.setName(name);
          borr.setPassword(password);
          borr.setPhone(phone);
          borr.setSinOrStNum(sinOrStNum);
          borr.setType(type);
          borr.insert();
  }

  /**
   * Checks out the given items for a given Borrower.
   * 
   * To borrow items, borrowers provide their id and an array of call numbers
   * of the items they want to check out. The system determines if the
   * borrower's account is valid and if the library material is available for
   * borrowing. Then it registers the item as "out", adding it to the list of
   * library materials that are on-loan by that borrower and prints a note
   * with the item's details and the due day.
   * 
   * @param bid
   * @param callNumbers the call numbers of the books to check out
   * @param copyNos the copy numbers of the books to check out
   * @return a 2d array of strings that can be printed as a receipt
   * @throws SQLException if the transaction cannot be completed in the database
   * @throws BookNotInException if the book is not in the library
   * @throws InvalidBorrowerException if the borrower has fines or overdue
   * books or has an expired account
   */
   public String[][] checkOutItems( int bid, 
                                    String[] callNumbers, 
                                    String[] copyNos) 
           throws SQLException, BookNotInException, InvalidBorrowerException
   {
      Borrower borrower = new Borrower();
      borrower.setBid(bid);
      borrower = (Borrower) borrower.get();

      ArrayList<Borrowing> borrowingGrowable = new ArrayList<Borrowing>();
      Connection connection = Conn.getInstance().getConnection();
      try
      {
        connection.setAutoCommit(false);
        if (borrower.isValid()) 
        {
          for (int i = 0; i < copyNos.length; i++) 
          {
            BookCopy bookCopy = new BookCopy();
            Book book = new Book();
            book.setCallNumber(callNumbers[i]);
            bookCopy.setB(book);
            bookCopy.setCopyNo(copyNos[i]);
            bookCopy = (BookCopy) bookCopy.get();
            if (bookCopy.getStatus().equals("in") || bookCopy.getStatus().equals("on-hold")) 
            {
              bookCopy.setStatus("out");
              bookCopy.update();

              // set the due date to just before midnight
              Calendar outDate = new GregorianCalendar();
              outDate.set(Calendar.HOUR_OF_DAY, 23);
              outDate.set(Calendar.MINUTE, 59);
              outDate.set(Calendar.SECOND, 59);

              Borrowing borrowing =
                      new Borrowing(-1, outDate, null, bookCopy, borrower);

              if (borrowing.insert()) 
              {
                borrowingGrowable.add(borrowing);
              }
              else
              {
                String msg = "Could not add this borrowing.\nTransaction "
                        + "aborted.";
                throw new SQLException(msg);
              }
            } 
            else 
            {
              String msg = "This book is not available to be borrowed.";
              throw new BookNotInException(msg);
            }
          } // end for loop
          int numRows = borrowingGrowable.size() + 1;
          String[][] borrowingReceipt = new String[numRows][];
          // call number, copy number, title, out date, due date
          int numCols = 6;
          String[] header = {"ITEM", "CALLNUMBER","COPYNO","TITLE","OUTDATE","DUEDATE"};
          borrowingReceipt[0] = header;
          int n = 0;
          final int ITEM = n++;
          final int CALLNUMBER = n++;
          final int COPYNO = n++;
          final int TITLE = n++;
          final int OUTDATE = n++;
          final int DUEDATE = n++;
          DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
          for (int i = 1; i < numRows; i++)
          {
            String[] row = new String[numCols];
            Borrowing borrowing = borrowingGrowable.get(i-1);
            row[ITEM] = ""+i;
            row[CALLNUMBER] = borrowing.getBookCopy().getB().getCallNumber();
            row[COPYNO] = borrowing.getBookCopy().getCopyNo();
            row[TITLE] = borrowing.getBookCopy().getB().getTitle();
            row[OUTDATE] = df.format(borrowing.getOutDate().getTime());
            row[DUEDATE] = df.format(borrowing.getDueDate().getTime());
            borrowingReceipt[i] = row;
          }
          connection.commit();
          return borrowingReceipt;
        }
        else
        {
          String msg = "This borrower is not valid.";
          throw new InvalidBorrowerException(msg);
        }
        
      } // end try
      finally
      {
        connection.rollback();
        connection.setAutoCommit(true);
      }
    }

   
   /**
   * Processes an item return.
   * 
   * Given a returned items' callNo and copyNo, the system determines the
   * borrower who had borrowed the item, registers the item as "in", and
   * removes it from the list of library materials on loan to that borrower.
   * If the item is overdue, a fine is assessed for the borrower and the
   * borrower is blocked (if he/she is not already blocked). If there is a
   * hold request for this item by another borrower, the item is registered as
   * "on hold" and a message is send to the borrower who made the hold
   * request.
   * 
   * @param callNo
   * @param copyNo
   * @throws SQLException
   * @throws BookCopyEvilTwinException if this copy has been borrowed twice at the same time
   * @throws FineRequiredException if this return cannot be processed because it requires a fine
   * @throws NoSuchCopyException if the copy does not exist
   */
   public void processReturn(String callNo, String copyNo)
          throws SQLException, BookCopyEvilTwinException, FineRequiredException, NoSuchCopyException 
   {
     processReturn(callNo, copyNo, null);
   }
           
   
  /**
   * Processes an item return.
   * 
   * Given a returned items' callNo and copyNo, the system determines the
   * borrower who had borrowed the item, registers the item as "in", and
   * removes it from the list of library materials on loan to that borrower.
   * If the item is overdue, a fine is assessed for the borrower and the
   * borrower is blocked (if he/she is not already blocked). If there is a
   * hold request for this item by another borrower, the item is registered as
   * "on hold" and a message is send to the borrower who made the hold
   * request.
   * 
   * @param callNo
   * @param copyNo
   * @param fine 
   * @throws SQLException
   * @throws BookCopyEvilTwinException if this copy has been borrowed twice at the same time
   * @throws FineRequiredException if this return cannot be processed because it requires a fine
   * @throws NoSuchCopyException if the copy does not exist
   */
  public void processReturn(String callNo, String copyNo, Integer fineAmountInCents) 
          throws SQLException, BookCopyEvilTwinException, FineRequiredException, NoSuchCopyException 
  {
    Connection conn = Conn.getInstance().getConnection();
    conn.setAutoCommit(false);
    try
    {
      Book book = new Book();
      book.setCallNumber(callNo);
      BookCopy bookCopy = new BookCopy();
      bookCopy.setB(book);
      bookCopy.setCopyNo(copyNo);
      Borrowing borrowing = new Borrowing();
      borrowing = borrowing.getOutBorrowing(bookCopy);
      Calendar dueDate = borrowing.getDueDate();
      Calendar today = new GregorianCalendar();
      boolean isOverdue = dueDate.before(today);
      boolean fineExists = fineAmountInCents != null;
      if (!fineExists && isOverdue)
      {
        throw new FineRequiredException();
      }
      // set borrowing in date
      borrowing.setInDate(today);
      borrowing.update();

      // set book copy as in
      bookCopy = (BookCopy) bookCopy.get();
      bookCopy.setStatus("in");
      bookCopy.update();

      // check for holds
      ArrayList<Table> holdRequestsForThisBook =(ArrayList<Table>) (new HoldRequest()).getAll(book);
      int numHoldRequests = holdRequestsForThisBook.size();
      if (numHoldRequests > 0)
      {
        Calendar[] issuedDates = new Calendar[numHoldRequests];
        for (int i = 0; i < numHoldRequests; i++)
        {
          HoldRequest holdRequest = (HoldRequest) holdRequestsForThisBook.get(i);
          issuedDates[i] = holdRequest.getIssueDate();
        }

        Arrays.sort(issuedDates); // in ascending order, so the first element is the earliest
        Calendar firstHoldRequestIssuedDate = issuedDates[0];

        HoldRequest earliestHoldRequest = null;
        int holdRequestIndex = 0;
        while (earliestHoldRequest == null && holdRequestIndex < numHoldRequests)
        {
          HoldRequest holdRequest = (HoldRequest) holdRequestsForThisBook.get(holdRequestIndex);
          if (holdRequest.getIssueDate().equals(firstHoldRequestIssuedDate))
          {
            earliestHoldRequest = holdRequest;
          }
          holdRequestIndex++;
        }
        // email user

        // delete the hold request
        earliestHoldRequest.delete();

        // set book copy's status to on hold
        bookCopy.setStatus("on-hold");
        bookCopy.update();
      } // end if hold requests exist
      
      // add fine
      if (fineExists && isOverdue)
      {
        Fine fine = new Fine(-1, fineAmountInCents,today,null, borrowing);
        fine.insert();
      }
      conn.commit();
    }
    finally
    {
      conn.rollback();
      conn.setAutoCommit(true);
    }
    
  }

  /**
   * Checks all overdue books.
   * 
   * The system displays a list of the items that are overdue and the
   * borrowers who have checked them out. The borrowers of these items are
   * blocked (if they are not already blocked) and the clerk may decide to
   * send messages to any of them.
   * 
   * @throws SQLException
   * 
   */
  public String[] checkOverdue() throws SQLException {
          Borrowing bwing = new Borrowing();
          BookCopy bc = new BookCopy();
          Collection<Table> lbw = bwing.getOverdue();
          HashMap<Borrower, BookCopy> overdue = new HashMap<Borrower, BookCopy>();
          
          String[] borrStr = new String[lbw.size()];
          String[] bcpyStr = new String[lbw.size()];
          int i = 0;

          if (lbw.size() > 0) {
                  Iterator<Table> bwItr = lbw.iterator();
                  while (bwItr.hasNext()) {
                          bwing = (Borrowing) bwItr.next();

                          Borrower borr = new Borrower();
                          borr.setBid(bwing.getBorid());
                          borr = (Borrower) borr.get();
                          
                          bc = bwing.getBookCopy();
                          bc.setStatus("overdue");
                          bc.update();
                          
                          overdue.put(borr, bc);
                          borrStr[i] = borr.getName();
                          bcpyStr[i] = bc.getCopyNo();
                          i++;
                  }
          }
          
          String[] result = new String[overdue.size()];
        		  
          for (i = 0; i < overdue.size(); i++) {
        	  result[i] = (borrStr[i] + ":" + bcpyStr[i]);
          }
          
          return result;
  }

  /**
   * Process a fine.
   * 
   * If the borrower has no other outstanding fines, the borrower is unblocked
   * and can borrow items again.
   * 
   * @param f
   *            The fine to be processed
   */
  /* Not a Clerk user story
  public void processFine(Fine f, int amt) {
          int newAmt = f.getAmount() - amt;
          if (newAmt < 0)
                  newAmt = 0;
          f.setAmount(newAmt);
  }*/

  /**
   * Testing
   * @param args 
   */
  public static void main(String[] args) throws Exception
  {
    Clerk systemClerk = new Clerk();
   /* 
    int bid = 1;
    
    String callNumbers[] = {"test", "111", "tete"};
    String copyNumbers[] = {"C1", "C2", "C3"};
    
    String[][] receipt = systemClerk.checkOutItems(bid, callNumbers, copyNumbers);
    
    for (String[] a : receipt)
    {
      for (String b : a)
      {
        System.out.print(b + '\t');
      }
      System.out.println();
    }
    */
    //systemClerk.processReturn("WN304 B143 2005", "C1", 2000);
    
    String[] callnos = {"WN304 B143 2005","TA342 F162 2002"};
    String[] copynos = {"C1","C1"};
    systemClerk.checkOutItems(105, callnos, copynos);
    
  }
}

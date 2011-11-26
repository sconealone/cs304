package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import tables.Book;
import tables.BookCopy;
import tables.Borrower;
import tables.CheckedOutBookCopy;
import tables.PopularBookReport;


/**
 * This class encapsulates the functionality for the Librarian user.
 * @author Mitch
 */
public class Librarian 
{
	
  /**
   * Adds a new book to the library catalogue.  
   * The librarian provides the information for the new
   * book and the system adds it to the library.
   * callNumber and isbn may not be null, and subjects may be
   * neither null nor empty
   * A new copy is not created for this book.
   * @param callNumber The unique call number of the book
   * @param isbn The unique ISBN of the book
   * @param title The book's title
   * @param mainAuthor The main author of the book
   * @param publisher The publisher of the book
   * @param year the year the book was published
   * @param additionalAuthors 0..n additional authors.  The main author
   * must not be repeated here, and there must not be any dupicate authors
   * @param subjects 1..n subjects that the book is about
   * @throws SQLException if the book could not be added to the database
   */
  public boolean addNewBook(  String callNumber,
                              String isbn,
                              String title,
                              String mainAuthor,
                              String publisher,
                              int year,
                              String[] additionalAuthors,
                              String[] subjects)
          throws SQLException
  {
    ArrayList<String> additionalAuthorsList = 
            new ArrayList<String>(Arrays.asList(additionalAuthors));
    ArrayList<String> subjectsList = 
            new ArrayList<String>(Arrays.asList(subjects));
    return (new Book( callNumber, 
                      isbn, 
                      title, 
                      mainAuthor, 
                      publisher,
                      year,
                      additionalAuthorsList,
                      subjectsList)).insert();
  }

  /**
   * Adds a new book to the library catalogue.  
   * The librarian provides the information for the new
   * book and the system adds it to the library.
   * callNumber and isbn may not be null, and subjects may be
   * neither null nor empty
   * Several new copies of this book may be created
   * @param callNumber The unique call number of the book
   * @param isbn The unique ISBN of the book
   * @param title The book's title
   * @param mainAuthor The main author of the book
   * @param publisher The publisher of the book
   * @param year the year the book was published
   * @param additionalAuthors 0..n additional authors.  The main author
   * must not be repeated here, and there must not be any dupicate authors
   * @param subjects 1..n subjects that the book is about
   * @param numCopies the number of copies to make for this book
   * @pre numCopies is a non-negative number
   * @return a list of the new copy numbers that were generated
   * @throws SQLException if the book could not be added to the database
   * or any copy could not be added to the database
   * 
   * assumes that Book::insert() also updates the hasauthor and hassubject
   * tables
   */
  public String[] addNewBook( String callNumber,
                              String isbn,
                              String title,
                              String mainAuthor,
                              String publisher,
                              int year,
                              String[] additionalAuthors,
                              String[] subjects,
                              int numCopies)
          throws SQLException
  {
    
    ArrayList<String> additionalAuthorsList = 
            new ArrayList<String>(Arrays.asList(additionalAuthors));
    ArrayList<String> subjectsList = 
            new ArrayList<String>(Arrays.asList(subjects));
    
    
    
    Connection con = Conn.getInstance().getConnection();
    con.setAutoCommit(false);
    try
    {
      Book newBook =  new Book( callNumber, 
                                isbn, 
                                title, 
                                mainAuthor, 
                                publisher,
                                year,
                                additionalAuthorsList,
                                subjectsList);
      newBook.insert();
      
      return addNewCopiesToBookWithLatestCopyNumber(numCopies, newBook, null);
    } // end try
    finally
    {
      con.setAutoCommit(true);
    }
    
  }
  
  /**
   * Adds new copies of an existing book to the library.  The librarian provides
   * the unique call number of the book, and the system generates new copy
   * numbers for each copy the librarian wants.
   * @pre numCopies is a positive number
   * @param callNumber the unique call number of the book to add copies of
   * @param numCopies the number of copies to add. must be a positive number
   * @return the copy numbers of the new copies added
   * @throws SQLException 
   * 
   * class is stable
   */
  public String[] addNewCopies(String callNumber, int numCopies)
          throws SQLException
  {
    Book book = new Book();
    book.setCallNumber(callNumber);
    BookCopy bc = new BookCopy();
    bc.setB(book);
    String latestCopyNumber = bc.getLatestCopyNo();
    
    return addNewCopiesToBookWithLatestCopyNumber(  numCopies, 
                                                    book, 
                                                    latestCopyNumber);
  }
  
  /**
   * Adds n new copies of and existing book that already has copies to the library.
   * @param numCopies number of copies to add
   * @param book the book to add copies of
   * @param latestCopyNo the copy number of the book's most recent copy
   * @return a list of the copy numbers that were added
   * @throws SQLException 
   */
  private String[] addNewCopiesToBookWithLatestCopyNumber ( int numCopies, 
                                                            Book book,
                                                            String latestCopyNo)
          throws SQLException
  {
    int copyNoAsInt = (latestCopyNo==null) ?
            0 : Integer.parseInt(latestCopyNo.substring(1));
    copyNoAsInt++;
    ArrayList<String> copyNumbersGrowable = new ArrayList<String>();
    Connection c = Conn.getInstance().getConnection();
    c.setAutoCommit(false);
    for (int i = 0; i < numCopies; i++)
    {
      String copyNoAsString = "C"+copyNoAsInt;
      BookCopy newBookCopy = new BookCopy(copyNoAsString,book,"in");
      if (newBookCopy.insert())
      {
        copyNumbersGrowable.add(copyNoAsString);
        copyNoAsInt++;
      }
      else
      {
        String msg = "Insertion of copyNo "+copyNoAsString+" failed.";
        throw new SQLException(msg);
      }
    }
    c.setAutoCommit(true);
    c.commit();
    return (String[]) copyNumbersGrowable.toArray();
  }
	
  /**
   * Removes a book from the catalogue.  The librarian provides the
   * catalogue number for the item and the system removes it from the
   * database
   * @param callNumber the unique call number of the book to remove
   * @return true if the remove succeeds, otherwise false
   * @throws SQLException if you can't connect to the database
   */
  public boolean removeBook(String callNumber) throws SQLException
  {
    Book book = new Book();
    book.setCallNumber(callNumber.toUpperCase());
    return book.delete();
  }

  /**
   * Remove a book copy from the catatlogue.  The librarian provides the
   * callNumber and copy number of the copy to remove.  The system removes
   * the copy from the database.
   * @param callNumber
   * @param copyNo
   * @return true if the remove succeeds, otherwise false
   * @throws SQLException if you can't connect to the database
   */
  public boolean removeBookCopy(String callNumber, String copyNo)
          throws SQLException
  {
    Book book = new Book();
    book.setCallNumber(callNumber);
    BookCopy bookCopy = new BookCopy();
    bookCopy.setB(book);
    bookCopy.setCopyNo(copyNo);
    return bookCopy.delete();
  }
  
  /**
   * Removes several book copies from the catalogue. The librarian provides
   * the callNumber and the copy numbers of the copies to remove.  The
   * system removes all the copies from the database
   * @param callNumber
   * @param copyNos
   * @return true if all the removes succeed, otherwise false.  If it returns
   * false there is no way to tell which remove failed other than checking
   * the list of all copies.
   * @throws SQLException if the logic cannot contact the database
   */
  public boolean removeBookCopy(String callNumber, String[] copyNos) 
          throws SQLException
  {
    Connection con = Conn.getInstance().getConnection();
    boolean wereAllDeletesSuccessful = true;
    con.setAutoCommit(false);
    try
    {
      int numCopiesToDelete = copyNos.length;
      int index = 0;
      while (wereAllDeletesSuccessful && index < numCopiesToDelete)
      {
        wereAllDeletesSuccessful =
                wereAllDeletesSuccessful && removeBookCopy(callNumber, copyNos[index]);
        index++;
      }
      return wereAllDeletesSuccessful;
    }
    finally
    {
      con.setAutoCommit(true);
      con.commit();
    }
  }

  /**
   * Removes a borrower from the library.  The borrower ID is provided.
   * @param bid the unique identifying account ID of the borrower
   * @return true if the borrower is removed, otherwise false
   * @throws SQLException if the database can't be reached
   */
  public boolean removeBorrower(int bid)
          throws SQLException
  {
    Borrower borrower = new Borrower();
    borrower.setBid(bid);
    return borrower.delete();
  }

  /**
   * Generates a report with all the books that have been checked out.
   * For each book the report shows the date it was checked out and the due
   * date.  The system flags the items that are overdue.  The items are
   * ordered by the book call number. 
   * @return a matrix of strings where the first row is column names and
   * the rest of the matrix represents the CheckedOutBookCopy view
   * @throws SQLException 
   */
  public String[][] getCheckedOutBooksReport() 
          throws SQLException
  {
    CheckedOutBookCopy co = new CheckedOutBookCopy();
    co.flagOverdue();
    return co.display();
  }

  /**
   * Generates a report with all the books that have been checked out.
   * For each book the report shows the date it was checked out and the due
   * date.  The system flags the items that are overdue.  The items are
   * ordered by the book call number. Only books with the provided subject are
   * listed.
   * @return a matrix of strings where the first row is column names and
   * the rest of the matrix represents the CheckedOutBookCopy view
   * @throws SQLException 
   */
  public String[][] getCheckedOutBooksReport(String subject)
          throws SQLException
  {
    CheckedOutBookCopy co = new CheckedOutBookCopy();
    co.setSubjectToFilterBy(subject);
    co.flagOverdue();
    return co.display();
  }
  
  /**
   * Generates a report with the most popular items in a given year.
   * The librarian provides a year and a number n.  The system lists the
   * top n books that were borrowed the most times during that year.  The books
   * are ordered by the number of times they were borrowed.
   * @param year the year to generate the report for
   * @param n the number of results to show
   * @return a matrix representing a table, including a header with the
   * column names as the first row.
   * @throws SQLException 
   */
  public String[][] getPopularBooks(int year, int n) 
          throws SQLException
  {
    PopularBookReport br = new PopularBookReport(year, n);
    return br.display();
  }
  
  /**
   * only for testing
   * @param args 
   */
  public static void main(String[] args) throws Exception {
    Librarian l = new Librarian();
    /* 
     * won't work until people finish implementing insert
     
    String[] additionalAuthors = {"Foo Bar", "Foo Baz"};
    String[] subjects = {"Science"};
    l.addNewBook("QA111 M100 2011","12345","The adventures of Mitch",
            "Foo Bat", "McGraw Mountain",2011,additionalAuthors,
            subjects);
    
    l.addNewBook("QA112 M100 2011","12346","The further adventures of Mitch",
            "Foo Bat", "McGraw Mountain",2011,additionalAuthors,
            subjects, 5);
    
    l.addNewCopies("QA111 M100 2011", 2);
    */
    String[][] displayTable;
    displayTable = l.getCheckedOutBooksReport();
    display(displayTable);
    /*
    displayTable = l.getCheckedOutBooksReport("Science");
    display(displayTable);
    display(l.getPopularBooks(2005, 5));
     * 
     */
    /*
     * won't work until people finish implementing delete
     
    Book b = new Book();
    BookCopy bc = new BookCopy();
    l.removeBook("QA111 M100 2011");
    display(b.display());
    display(bc.display());
    l.removeBookCopy("QA112 M100 2011", "C3");
    display(bc.display());
    String[] copyNos = {"C4","C5"};
    l.removeBookCopy("QA111 M100 2011", copyNos);
    display(bc.display());
    l.removeBook("QA111 M100 2011");
    display(bc.display());
     */
  }
  
  private static void display(String[][] displayTable)
  {
    for (String[] a : displayTable) 
    {
      for (String b : a) 
      {
        System.out.print(b + '\t');
      }
      System.out.println();
    }
  }
	
}

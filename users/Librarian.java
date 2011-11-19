package users;

import java.sql.SQLException;


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
                              String[] additionalAuthors,
                              String[] subjects)
          throws SQLException
  {
    return false;
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
   * @param additionalAuthors 0..n additional authors.  The main author
   * must not be repeated here, and there must not be any dupicate authors
   * @param subjects 1..n subjects that the book is about
   * @param numCopies the number of copies to make for this book
   * @pre numCopies is a non-negative number
   * @return a list of the new copy numbers that were generated
   * @throws SQLException if the book could not be added to the database
   */
  public String[] addNewBook( String callNumber,
                              String isbn,
                              String title,
                              String mainAuthor,
                              String publisher,
                              String[] additionalAuthors,
                              String[] subjects,
                              int numCopies)
          throws SQLException
  {
    return null;
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
   */
  public String[] addNewCopies(String callNumber, int numCopies)
          throws SQLException
  {
    return null;
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
    return false;
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
    return false;
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
    return false;
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
    return false;
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
    return null;
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
    return null;
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
  public String[][] getPopularBOoks(int year, int n) 
          throws SQLException
  {
    return null;
  }
	
}

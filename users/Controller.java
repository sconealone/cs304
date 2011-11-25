package users;

import GUI.ViewFrame;
import java.sql.Connection;
import java.util.Collection;


import java.sql.SQLException;
import java.util.ArrayList;
import tables.Book;
import tables.BookCopy;
import tables.Borrower;
import tables.BorrowerType;
import tables.Borrowing;
import tables.Fine;
import tables.HasAuthor;
import tables.HasSubject;
import tables.HoldRequest;

public class Controller {
	
  private Collection<HoldRequest> holdRequests;
  private Collection<Book> books;
  private Collection<BookCopy> bookCopies;
  private Collection<Fine> fines;
  private Collection<Borrowing> borrowings;
  private Collection<Borrower> borrowers;
  private Clerk systemClerk;
  private Librarian systemLibrarian;
  private Borrower systemBorrower;
  private ViewFrame view;
  
  public final void startup()
  {
    // necessary to initialize the connection to the database
    Conn.getInstance();
  }
  
  public void shutdown()
  {
    Connection c = Conn.getInstance().getConnection();
    try
    {
      if (c != null)
        c.close();
    }
    catch (SQLException e)
    {
      // do nothing since the System is going to shut down.
    }
  }

  public Controller(ViewFrame view)
  {
    startup();
    this.view = view;
    holdRequests = new ArrayList<HoldRequest>();
    books = new ArrayList<Book>();
    bookCopies = new ArrayList<BookCopy>();
    fines = new ArrayList<Fine>();
    borrowings = new ArrayList<Borrowing>();
    borrowers = new ArrayList<Borrower>();
    systemClerk = new Clerk();
    systemLibrarian = new Librarian();
    systemBorrower = new Borrower();
   


  }
  
  /**
   * Gets the 2D representation of a table from the database
   * @param tableName
   * @return
   * @throws SQLException 
   */
  public String[][] displayTable(String tableName) throws SQLException
  {
    String[][] table = null;
    if (tableName.equals("Borrower"))
    {
      table = (new Borrower()).display();
    }
    else if (tableName.equals("BorrowerType"))
    {
      table = (new BorrowerType()).display();
    }
    else if (tableName.equals("Book"))
    {
      table = (new Book()).display();
    }
    else if (tableName.equals("HasAuthor"))
    {
      table = (new HasAuthor()).display();
    }
    else if (tableName.equals("HasSubject"))
    {
      table = (new HasSubject()).display();
    }
    else if (tableName.equals("BookCopy"))
    {
      table = (new BookCopy()).display();
    }
    else if (tableName.equals("HoldRequest"))
    {
      table = (new HoldRequest()).display();
    }
    else if (tableName.equals("Borrowing"))
    {
      table = (new Borrowing()).display();
    }
    else if (tableName.equals("Fine"))
    {
      table = (new Fine()).display();
    }
    return table;
  }

  /**
   * @return the systemClerk
   */
  public Clerk getSystemClerk() {
    return systemClerk;
  }

  /**
   * @return the systemLibrarian
   */
  public Librarian getSystemLibrarian() {
    return systemLibrarian;
  }

  /**
   * @return the systemBorrower
   */
  public Borrower getSystemBorrower() {
    return systemBorrower;
  }

  public void reconnect() {
    Conn.getInstance().reconnect();
  }
}


package users;

import GUI.ViewFrame;
import java.util.Collection;


import java.sql.SQLException;
import java.util.ArrayList;
import tables.Book;
import tables.BookCopy;
import tables.Borrower;
import tables.Borrowing;
import tables.Fine;
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
    try
    {
      Conn.getInstance().getConnection().close();
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
}


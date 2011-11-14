package users;

import java.util.Collection;

import GUI.GUI;

import tables.Book;
import tables.BookCopy;
import tables.Borrower;
import tables.Borrowing;
import tables.Fine;
import tables.HoldRequest;

public class Controller {
	
	private Collection<HoldRequest> cHR;
	private Collection<Book> cB;
	private Collection<BookCopy> cBC;
	private Collection<Fine> cF;
	private Collection<Borrowing> cBorrowing;
	private Collection<Borrower> cBorrower;
	private Clerk SystemClerk;
	private Librarian SystemLibrarian;
	private GUI view;
	

}

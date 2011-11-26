package users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import tables.Book;
import tables.BookCopy;
import tables.Borrower;
import tables.BorrowerType;
import tables.Borrowing;
import tables.Fine;
import tables.HoldRequest;
import tables.Table;

/**
 * This class encapsulates the functionality of the Clerk user. This class is
 * not complete yet.
 * 
 * @author Masterfod
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
	 * @param borid
	 * @param copyNos
	 * @throws SQLException
	 */
	public void checkOutItems(int borid, String[] copyNos) throws SQLException {
		Borrower borr = new Borrower();
		Borrowing bwing = new Borrowing();
		borr.setBid(borid);
		borr = (Borrower) borr.get();

		ArrayList<Borrowing> lborr = new ArrayList<Borrowing>();

		if (borr.isValid()) {
			for (int i = 0; i < copyNos.length; i++) {
				BookCopy bc = new BookCopy();
				bc.setCopyNo(copyNos[i]);
				bc = (BookCopy) bc.get();
				if (bc.getStatus() == "IN") {
					bc.setStatus("OUT");
					bwing.setBookCopy(bc);
					bwing.setBorrower(borr);

					Calendar inDate = Calendar.getInstance();
					inDate.add(Calendar.DATE, borr.getBookTimeLimit() * 7);
					bwing.setInDate(inDate);
					if (bwing.insert())
						lborr.add(bwing);
				}
			}
		}

		// TODO print stuff
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
	 */
	public void processReturn(int callNo, int copyNo) throws SQLException {
		BookCopy bc = new BookCopy();
		Borrowing bwing = new Borrowing();
		// Borrower borr = new Borrower();

		// TODO get the correct Borrowing object

		bc.setStatus("IN");
		if (Calendar.getInstance().compareTo(bwing.getInDate()) == 1) {
			Fine f = new Fine();
			// TODO Set correct fine amount
			f.setAmount(0);
			f.setBorrowing(bwing);
			f.setIssuedDate(Calendar.getInstance());
			f.setPaidDate(null);
			f.insert();
		}

		HoldRequest hr = new HoldRequest();
		HoldRequest finalhr = new HoldRequest();
		finalhr.setIssueDate(Calendar.getInstance());
		Book b = new Book();

		Collection<Table> lhReq = hr.getAll(b);
		if (lhReq.size() > 0) {
			Iterator<Table> hrItr = lhReq.iterator();
			while (hrItr.hasNext()) {
				hr = (HoldRequest) hrItr.next();
				// This returned BookCopy goes to the earliest HoldRequest
				if ((hr.getIssueDate().compareTo(finalhr.getIssueDate())) == -1)
					finalhr = hr;
			}
			// TODO message(hr.getBorr());
			// TODO bc.setStatus("HOLD");
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
	public void checkOverdue() throws SQLException {
		Borrowing bwing = new Borrowing();
		Collection<Table> lbw = bwing.getOverdue();

		if (lbw.size() > 0) {
			Iterator<Table> bwItr = lbw.iterator();
			while (bwItr.hasNext()) {
				bwing = (Borrowing) bwItr.next();

				Borrower borr = new Borrower();
				borr.setBid(bwing.getBorid());
				borr = (Borrower) borr.get();
				// TODO bc.setStatus("OVERDUE");
				// TODO message(borr);
			}
		}
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
	public void processFine(Fine f, int amt) {
		int newAmt = f.getAmount() - amt;
		if (newAmt < 0)
			newAmt = 0;
		f.setAmount(newAmt);
	}
}

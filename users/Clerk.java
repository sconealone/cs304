package users;

import java.util.Calendar;

import tables.*;

/**
 * This class is not complete yet.
 * 
 * @author Masterfod
 *
 */
public class Clerk {

	private Integer newAttr;

	/**
	 * 
	 * @param password
	 * @param name
	 * @param address
	 * @param phone
	 * @param emailAddress
	 * @param sinOrStNum
	 * @param expiryDate
	 * @param BookTimeLimit
	 */
	public void addBorrower(String password, String name, String address,
			String phone, String emailAddress, Integer sinOrStNum,
			Calendar expiryDate, String type) {

		Borrower borr = new Borrower();
		
		//	TODO fix this
		//	borr.insert(password, name, address, phone, emailAddress, sinOrStNum, expiryDate, type)
	}

	/**
	 * 
	 * @param borid
	 * @param callNos
	 */
	public void checkOutItems(int borid, int[] callNos) {
		Borrower borr = new Borrower();
		borr.setBid(borid);
		borr = (Borrower) borr.get();

		if (borr.) {
			for (int i = 0; i < callNos.length; i++) {
				Book b = new Book(callNos[i]);
				if (b.isAvail()) {
					// create borrowing object with borid, callNo
				}
			}
		}

		// print stuff
	}

	/**
	 * 
	 * @param callNo
	 */
	public void processReturn(int callNo, int copyNo) {
		BookCopy bc = new BookCopy();
		bwing.
		Borrower borr = new Borrower(bwing.getBorid());

		if (bwing.isOverdue()) {
			borr.setValid(false);
		}

		// remove from borrowing
		// check holds, message user
	}

	/**
	 * 
	 */
	public void checkOverdue() {
		
	}

	/**
	 * 
	 * @param f
	 */
	public void processFine(Fine f) {
		
	}
}

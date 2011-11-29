package users;

/**
 * Indicates that a book is not in the library
 * @author Mitch
 */
public class BookNotInException extends Exception {

  public BookNotInException() { super(); }
  
  public BookNotInException(String msg) {
    super(msg);
  }
  
}

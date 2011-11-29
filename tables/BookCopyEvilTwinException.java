package tables;

/**
 * Indicates that there are two copies of the same book; the database
 * is corrupted
 * @author Mitch
 */
public class BookCopyEvilTwinException extends Exception {
  
  public BookCopyEvilTwinException(){super();}

  public BookCopyEvilTwinException(String msg) {
    super(msg);
  }
  
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author Mitch
 */
class BookNotInException extends Exception {

  public BookNotInException() { super(); }
  
  public BookNotInException(String msg) {
    super(msg);
  }
  
}

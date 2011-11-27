/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author Mitch
 */
class InvalidBorrowerException extends Exception {

  public InvalidBorrowerException() { super(); }
  
  public InvalidBorrowerException(String msg) {
    super(msg);
  }
  
}

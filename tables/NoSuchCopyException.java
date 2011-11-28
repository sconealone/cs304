/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

/**
 *
 * @author Mitch
 */
public class NoSuchCopyException extends Exception {

  public NoSuchCopyException() { super(); }
  public NoSuchCopyException(String msg) {
    super(msg);
  }
  
}

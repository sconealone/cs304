/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author Mitch
 */
public class FineRequiredException extends Exception {

  public FineRequiredException() {
    super();
  }
  
  public FineRequiredException(String msg){
    super(msg);
  }
  
}

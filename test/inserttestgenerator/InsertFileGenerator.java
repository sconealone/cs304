package test.inserttestgenerator;


import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Mitch
 */
public class InsertFileGenerator {
  public static void main(String[] args) {
    //(int numBorrower, int numBook, int numBorrowing, 
      //    int numFine, int numHoldRequest)
    final int  BORROWER = 0;
    final int BOOK = 1;
    final int BORROWING = 2;
    final int FINE = 3;
    final int HOLD_REQUEST = 4;
    String filename = "insert_test_values_medium.sql";
    try
    {
      int numBorrower, numBook, numBorrowing, numFine, numHoldRequest;
      if (args.length != 5)
      {
        numBorrower = 100;
        numBook = 50;
        numBorrowing = 50;
        numFine = 10;
        numHoldRequest = 10;
        System.out.println("Default number of tables to generate used.");
      }
      else
      {
        numBorrower = Integer.parseInt(args[BORROWER]);
        numBook = Integer.parseInt(args[BOOK]);
        numBorrowing = Integer.parseInt(args[BORROWING]);
        numFine = Integer.parseInt(args[FINE]);
        numHoldRequest = Integer.parseInt(args[HOLD_REQUEST]);
      }
    
    FileWriter fout;
    InsertGenerator ig = new InsertGenerator();
      fout = new FileWriter(filename);
      fout.write(ig.generate(numBorrower,numBook,numBorrowing,numFine,numHoldRequest));
      fout.close();
      
      System.out.println(filename);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

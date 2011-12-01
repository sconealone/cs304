package test.inserttestgenerator;

import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Generates random insert statements
 * into a .sql file
 * @author Mitch
 */
public class InsertGenerator {
  /**
   * The number of borrowings there are.
   * Can be used as the smallest
   * unused borid
   */
  private int numBorrowings;
  
  private int numBorrowers;
  
  /**
   * The number of fines.
   * Can be used as the smallest unused fid
   */
  private int numFines;
  
  private ArrayList<String> usedCallNumbers;
  
  private ArrayList<Integer> indeciesOfBoridsWhereAFineShouldExist;
  private ArrayList<Integer> boridsWhereAFineShouldExist;
  private ArrayList<Calendar> borrowingOutdates;
  
  /**
   * a list of lists or all the possible
   * values for each attribute in the database
   */
  private ArrayList<ArrayList<Object>> attr;
  
  public static final int BID = 0;
  public static final int CALLNUMBER = 1;
  public static final int COPYNO = 2;
  public static final int DATE = 3;
  public static final int AMOUNT = 4;
  public static final int BORID = 5;
  public static final int NAME = 6;
  public static final int TITLE = 7;
  public static final int YEAR = 8;
  public static final int ISBN = 9;
  public static final int PUBLISHER = 10;
  public static final int SUBJECT = 11;
  public static final int EXISTING_CALLNUMBER = 12;
  public static final int DUE_DATE = 13;
  public static final int BORROWING_OUTDATE = 14;
  
  private static String[] primaryNumbers;
  private static String[] secondaryNumbers;
  private static String[] subjects = {"General","Science","Arts","Agriculture",
    "Business","Education","Engineering"};
  private static String[] statuses = {"on-hold","in","out"};
  
  /**
   * populates the attr list with elements
   * from the database
   */
  public InsertGenerator()
  {
    String bids = "SELECT bid FROM Borrower";
    String callnumbers = "SELECT callnumber FROM BookCopy";
    String copynos = "SELECT copyno FROM BookCopy";
    indeciesOfBoridsWhereAFineShouldExist= new ArrayList<Integer>();
    boridsWhereAFineShouldExist = new ArrayList<Integer>();
    attr = new ArrayList<ArrayList<Object>>();
    usedCallNumbers = new ArrayList<String>();
    for (int i = 0; i < 10; i++)
    {
      attr.add(null);
    }
    int numCharInAlphabet = 26;
    int minNum = 100;
    int maxNum = 500;
    int numRange = range(minNum,maxNum);
    int numPrimaryNumbers = numCharInAlphabet * numCharInAlphabet * numRange;
    primaryNumbers = new String[numPrimaryNumbers];
    int asciiA = 65;
    int primaryNumbersIndex = 0;
    for (int i = 0; i < numCharInAlphabet; i++)
    {
      for (int j = 0; j < numCharInAlphabet; j++)
      {
        for (int k = 0; k < numRange; k++)
        {
          primaryNumbers[primaryNumbersIndex++] = "" + ((char) (asciiA + i) )
                  + ((char) (asciiA + j)) + k;
        }
      }
    }
    
    int numSecondaryNumbers = numCharInAlphabet * numRange;
    secondaryNumbers = new String[numSecondaryNumbers];
    int secondaryNumbersIndex = 0;
    for (int i = 0; i < numCharInAlphabet; i++)
    {
      for (int j = 0; j < numRange; j++)
      {
        secondaryNumbers[secondaryNumbersIndex++] = "" + ((char) (asciiA + i))
                + j;
      }
    }
    borrowingOutdates = new ArrayList<Calendar>();
    /*
    Conn mycon = new Conn();
    Connection con = mycon.getConn();
    try
    {
      PreparedStatement stat = con.prepareStatement(bids);
      ResultSet rs = stat.executeQuery();
      ArrayList<Object> bidlist = new ArrayList<Object>();
      //System.out.println("bid");
      while (rs.next())
      {
        //System.out.println(rs.getInt(1));
        bidlist.add(new Integer(rs.getInt(1)));
      }
      attr.add(BID, bidlist);
      stat.close();
      
      stat = con.prepareStatement(callnumbers);
      rs = stat.executeQuery();
      ArrayList<Object> callnumberlist = new ArrayList<Object>();
      //System.out.println("call numbers:");
      while (rs.next())
      {
        //System.out.println(rs.getString(1));
        callnumberlist.add(rs.getString(1));
      }
      attr.add(CALLNUMBER, callnumberlist);
      stat.close();
      
      
      stat = con.prepareStatement(copynos);
      rs = stat.executeQuery();
      ArrayList<Object> copynolist = new ArrayList<Object>();
      //System.out.println("copyno:");
      while (rs.next())
      {
        //System.out.println(rs.getString(1));
        copynolist.add(rs.getString(1));
      }
      attr.add(COPYNO, copynolist);
      stat.close();
      
      con.close();
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }*/
  }
  
  /**
   * Generates a random name
   * @return 
   */
  private String makeName()
  {
    String[] firstNames = 
      {
         "Bob","Amy","Sam","Todd","Myae","Jim","Sarah","Foo","Karen","Alex",
         "Judy","Roy","Hannah","Ed","Platypus"
      };
    
    String[] lastNames =
      {
        "Red","Green","Orange","Yellow","Blue","Indigo","Violet","White","Foo",
        "Bar","Baz","Bat","Zot"
      };
      
      int minIndex = 0;
      int maxFirstNameIndex = firstNames.length - 1;
      int maxLastNameIndex = lastNames.length - 1;
      int rangeFirstNames = range(minIndex, maxFirstNameIndex);
      int rangeLastNames = range(minIndex, maxLastNameIndex);
      
      int firstNameIndex = minIndex + (int) (rangeFirstNames * Math.random());
      int lastNameIndex = minIndex + (int) (rangeLastNames * Math.random());
      
      return firstNames[firstNameIndex] + " " + lastNames[lastNameIndex];
    
  }
  
  
  
  /**
   * Generates a string of insert statements
   * @param numBor
   * @param numFine
   * @return 
   */
  public String generate(int numBorrower, int numBook, int numBorrowing, 
          int numFine, int numHoldRequest)
  {
    return makeBorrowerType()
            + makeBorrower(numBorrower)
            + makeBook(numBook)
            + makeBorrowing(numBorrowing) 
            + makeFine(numFine)
            +makeHoldRequest(numHoldRequest)
            +"commit;";
  }
  
  /**
   * Makes code for a single book
   * book copy
   * HasSubject
   * HasAuthor
   * @return 
   */
  private String makeBook()
  {
    String callNumber;
    do
    {
      callNumber = (String) getRand(CALLNUMBER);
    } while (!usedCallNumbers.isEmpty() && usedCallNumbers.contains(callNumber));
    usedCallNumbers.add(callNumber);
    
    String insert = "INSERT INTO Book VALUES(";
    insert = insert + "'"+callNumber + "',";
    insert = insert + "'"+(String) getRand(ISBN) + "',";
    insert = insert + "'"+(String) getRand(TITLE) + "',";
    insert = insert + "'"+(String) getRand(NAME) + "',";
    insert = insert + "'"+(String) getRand(PUBLISHER) + "',";
    insert = insert + (Integer) getRand(YEAR);
    insert += ");\n\n";
    
    int minNumCopies = 1;
    int maxNumCopies = 4;
    int numCopiesRange = range(minNumCopies, maxNumCopies);
    for (int i = 1; i <= (minNumCopies + (int)(numCopiesRange*Math.random())); i++)
    {
      insert = insert + "INSERT INTO BookCopy VALUES("
               + "'"+callNumber + "', 'C" + i + "','"
              + (((int)(1 + 10*Math.random()) < 2) ? "on-hold" : "in")
              +"');\n\n";
    }
    
    int minNumSubjects = 1;
    int maxNumSubjects = 3;
    int numSubjectsRange = range(minNumSubjects, maxNumSubjects);
    for (int i = 1; i <= (minNumSubjects + (int)(numSubjectsRange*Math.random())); i++)
    {
      insert = insert + "INSERT INTO HasSubject VALUES("
               + "'"+callNumber + "', '"+getRand(SUBJECT)+"');\n\n";
    }
    
    int minNumAuthors = 1;
    int maxNumAuthors = 3;
    int numAuthorsRange = range(minNumAuthors, maxNumAuthors);
    for (int i = 1; i <= (minNumAuthors + (int)(numAuthorsRange*Math.random())); i++)
    {
      insert = insert + "INSERT INTO HasAuthor VALUES('"
               + callNumber + "', '"+getRand(NAME)+"');\n\n";
    }
    
    return insert;
  }
  
  /**
   * Makes code for n books
   * book copies
   * and subjects and authors
   * @param n
   * @return 
   */
  private String makeBook(int n)
  {
    String insert = "";
    for (int i = 0; i < n; i++)
    {
      insert += makeBook();
    }
    return insert;
  }
  
  /**
   * Makes an insert borrowing statement.
   * @post numBorrowings will be incremented
   * @return 
   */
  private String makeBorrowing()
  {
    String insert = "";
    
    Calendar outdate = (Calendar) getRand(DUE_DATE);
    Calendar indate = (Calendar) getRand(DUE_DATE);
    
    if (indate.before(outdate))
    {
      Calendar temp = indate;
      indate = outdate;
      outdate = temp;
    }
    
    Format f = new SimpleDateFormat("dd-MMM-yyyy");
    String outdatestr = f.format(outdate.getTime());
    String indatestr = f.format(indate.getTime());
    
    String updateStatusToOut = "";
    String callNumber = (String)getRand(EXISTING_CALLNUMBER);
    
    // sometimes randomly make the indate null
    if ((1 + (int)(Math.random()*10)) < 3)
    {
      indatestr = "NULL";
      updateStatusToOut = updateStatusToOut + "UPDATE BookCopy \nSET status = 'out'"
              + "\nWHERE callNumber = '"+callNumber+"' AND copyNo = 'C1';\n\n";
    }
    else
    {
      boridsWhereAFineShouldExist.add(new Integer(numBorrowings+1));
    }
    borrowingOutdates.add(outdate);
    //numBorrowings+1 == borid
    
    insert += "INSERT INTO Borrowing\n";
    insert += "VALUES (";
    insert += ("boridCounter.nextval,"+
              ((Integer)getRand(BID)).intValue()+","+
              "'"+callNumber+"',"+
              "'C1',"+
              "to_date('"+outdatestr+"','DD-MON-YYYY'),");
    
    if (indatestr.equals("NULL"))
    {
      insert += indatestr;
    }
    else
    {
      
      insert += "to_date('"+indatestr+"','DD-MON-YYYY')";
    }
    insert += ");\n";
    
    numBorrowings++;
    insert += updateStatusToOut;
    return insert;
  }
  
  /**
   * Generates n insert borrowing statements
   * @param n
   * @return 
   */
  private String makeBorrowing(int n)
  {
    String insert = "";
    for (int i = 0; i < n; i++)
    {
      insert += makeBorrowing();
    }
    return insert;
  }
  
  private String makeFine()
  {
    String insert = "";
    
    Calendar issuedDate = (Calendar) getRand(BORROWING_OUTDATE);
    Calendar paidDate = null;//(Calendar) getRand(DATE);
    /*
    if (paidDate.before(issuedDate))
    {
      Calendar temp = paidDate;
      paidDate = issuedDate;
      issuedDate = temp;
    }*/
    
    Format f = new SimpleDateFormat("dd-MMM-yyyy");
    String issuedDateStr = f.format(issuedDate.getTime());
    //String paidDateStr = f.format(paidDate.getTime());
    
    // sometimes randomly make the indate null
    // 25 Nov: No fines will have a paiddate
    /*if ((1 + (int)(Math.random()*10)) < 30)
    {
      paidDateStr = "NULL";
    }*/
    int borid = ((Integer) getRand(BORID)).intValue();
    if (borid == -1)
    {
      return "";
    }
    insert += "INSERT INTO Fine\n";
    insert += "VALUES (";
    insert += "fidCounter.nextval,";
    insert += ""+ ((Integer) getRand(AMOUNT)).intValue()+",";
    insert += "to_date('"+issuedDateStr+"','DD-MON-YYYY'),";
    insert += "null,";
    insert += ""+ borid;
    insert += ");\n";
    
    numFines++;
    insert += "\n";
    return insert;
  }
  
  /**
   * Makes n insert fine statements
   * @param n 
   */
  private String makeFine(int n)
  {
    String insert = "";
    for (int i = 0; i < n; i++)
    {
      insert += makeFine();
    }
    return insert;
  }
  
  
  private String makeBorrowerType()
  {
    return "INSERT INTO BorrowerType"
            + " VALUES('Student', 2);\n"
            + "INSERT INTO BorrowerType"
            + " VALUES('Staff', 6);\n"
            + "INSERT INTO BorrowerType"
            + " VALUES('Faculty', 12);\n"
            + "\n\n";
  }
  
  private String makeBorrower()
  {
    String[] types = {"Student", "Staff", "Faculty"};
    int minStNum = 100000;
    int maxStNum = 999999;
    int stNumRange = range(minStNum, maxStNum);
    String name = (String) getRand(NAME);
    String email = "";
    for (int i =0; i < name.length(); i++)
    {
      if (name.charAt(i) != ' ')
      {
        email += name.charAt(i);
      }
    }
    email+="@emailaddress.com";
    email = email.toLowerCase();
    return "INSERT INTO Borrower "
            + "VALUES(bidCounter.nextval, 'password', "
            + "'"+name+"'" + ",'123 Sesame Street',5555555," + "'"+email+"'"
            +","+(minStNum + (int)(stNumRange*Math.random()))+" ,null,"
            +"'"+types[(int)(types.length*Math.random())]+"');";
  }
  
  private String makeBorrower(int n)
  {
    String insert = "";
    for (int i = 0; i < n; i++)
    {
      insert += makeBorrower();
      insert += "\n\n";
    }
    numBorrowers = n;
    return insert;
  }
  
  private String makeHoldRequest()
  {
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    return "INSERT INTO HoldRequest "
            + "VALUES(hidCounter.nextval,"+(Integer) getRand(BID)+","
            + "to_date('"+df.format(((Calendar) getRand(DATE)).getTime())+"','DD-MON-YYYY'),"
            + "'"+(String) getRand(EXISTING_CALLNUMBER)+"');";
  }
  
  private String makeHoldRequest(int n)
  {
    String insert = "";
    for (int i = 0; i < n; i++)
    {
      insert += makeHoldRequest();
      insert += "\n\n";
    }
    return insert;
  }
  
  
  /**
   * Gets a random member of the specified element.
   * @param attr
   * @return 
   */
  private Object getRand(int attr)
  {
    int minYear = (attr == DUE_DATE) ?
            2011 : 1980;
    int minDay = 1;
    int minMonth = (attr == DUE_DATE) ?
            7 : 0;
    int maxYear = 2011;
    int maxDay = 28;
    int maxMonth = 11;
    int range, randomIndex;
    
    switch (attr)
    {
      case BID:
        return 1 + (int) (numBorrowers * Math.random());
      case CALLNUMBER:
        return primaryNumbers[(int) (primaryNumbers.length * Math.random())]
                + " "
                + secondaryNumbers[(int) (secondaryNumbers.length * Math.random())]
                + " "
                + (Integer) getRand(YEAR);
      case DATE:
        int year = minYear + (int) (range(minYear, maxYear)*Math.random());
        int month = minMonth + (int) (range(minMonth, maxMonth)*Math.random());
        int day = minDay + (int) (range(minDay, maxDay)*Math.random());
        return new GregorianCalendar(year, month, day);
      // return a random amount of money between $1 and $100 but in cents
      case AMOUNT:
        int mincents = 100;
        int maxcents = 10000;
        return new Integer(mincents + (int)(range(mincents,maxcents)*Math.random()));
      case BORID:
        range = boridsWhereAFineShouldExist.size();
        if (range == 0)
        {
          return -1;
        }
        randomIndex = (int) (range * Math.random());
        return boridsWhereAFineShouldExist.get(randomIndex);
      case NAME:
          return makeName();
      case TITLE:
          String[] firstWord = {"", "The", "A"};
          String[] noun = {"Cat","Dog","Mouse","House","Fox","Box","Locks",
              "Table","Chair","Window","Door"};
           
          String[] verb = {"Runs", "Moves", "Eats", "Studies","Plays","Breaks"};
          return (firstWord[0 + (int) (firstWord.length * Math.random())]
                  + " " 
                  +noun[(int) (noun.length * Math.random())]
                  + " "
                  +verb[(int) (verb.length * Math.random())]
                  + " " 
                  +noun[(int) (noun.length * Math.random())]).trim();
      case YEAR:
        return minYear + (int) (range(minYear, maxYear)*Math.random());
      case ISBN:
        char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
        int isbnLength = 13;
        String isbn = "";
        for (int i = 0; i < isbnLength; i++)
        {
          isbn += digits[(int) (digits.length*Math.random())];
        }
        return isbn;
      case PUBLISHER:
        String[] publishers = {"Random Home", "Willy and sons", "MiG Press",
          "Subtractison-Wesley", "BonTom", "Apprentice Hall"};
        return publishers[(int)(publishers.length*Math.random())];
      case SUBJECT:
        return subjects[(int)(subjects.length*Math.random())];
        
      case EXISTING_CALLNUMBER:
        range = usedCallNumbers.size();
        randomIndex = (int) (range * Math.random());
        return usedCallNumbers.get(randomIndex);
      case DUE_DATE:
        year = minYear + (int) (range(minYear, maxYear)*Math.random());
        month = minMonth + (int) (range(minMonth, maxMonth)*Math.random());
        day = minDay + (int) (range(minDay, maxDay)*Math.random());
        return new GregorianCalendar(year, month, day, 23, 59);
      case BORROWING_OUTDATE:
        range = borrowingOutdates.size();
        randomIndex = (int) (range * Math.random());
        return borrowingOutdates.get(randomIndex);
        
      default:
        ArrayList<Object> possibleValuesOfAttribute = this.attr.get(attr);
        range = possibleValuesOfAttribute.size();
        randomIndex = (int) (range * Math.random());
        return possibleValuesOfAttribute.get(randomIndex);
    }
    
  }
  
  /**
   * Returns the range between the
   * min and max
   * @param min
   * @param max
   * @return 
   */
  private int range(int min, int max)
  {
    return max - min + 1;
  }
  
}

package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import tables.Book;
import tables.Borrower;
import test.parser.InsertParser;
import users.Conn;

public class tablesTest{

	//setup essentially calls create_tables.sql as of Sunday November 20th 
	//changes in create_tables.sql should also be added here. Or maybe just here.


	//tables can be populated in the @Test methods or at the end of setup()


	//@Test Methods always run after @Before method  ( setup() ) 


	//Also, the clearDataBaseAfterTests() method clears the database of any changes, but leaves the structure
	Book b;
	static Connection c = Conn.getInstance().getConnection();

	@Test
	public void createBookObject() throws SQLException {
		//create a book object
		String cN = "123456789";
		String isBun = "123412341234A";
		String titleArg = "The Most Amazing Book";
		String mainAuthorArg = "The Most Amazing Author";
		String pub = "the most super publisher";
		int yr= 2012;
		ArrayList<String> authrs = new ArrayList<String>();
		authrs.add("someone boring");
		authrs.add("an even more boring person");

		ArrayList<String> subjectsArg = new ArrayList<String>();
		subjectsArg.add("astrobiology");
		subjectsArg.add("teutonicEpidemiology");

		b= new Book(cN,isBun,titleArg,mainAuthorArg,pub,yr,authrs,subjectsArg);
		//String sql1 = "INSERT INTO Book VALUES('1234','123','teilte','mainsss','pubb',1929)";
		//{"1234", "123          ","teilte","mainsss","pubb","1929"},
	}

	@Test public void testEquality(){
		String a = new String("test");
		String b = new String("test");
		assertEquals(a,b);
	}
	
	@Test public void testBookInsert() throws SQLException{
		createBookObject();
		assertTrue(b.insert());
	}
	/**
	 * Pre: runs after testBookInsert()
	 * @throws SQLException
	 */
	@Test
	public void testSearchBySubject() throws SQLException{
		Borrower bor = new Borrower();
		String[][] lob = bor.searchBookBySubject("astrobiology");
		assertEquals(lob[1][0], "123456789");
	}
	

	/**
	 * Pre: runs after testBookInsert();
	 * @throws SQLException
	 */
	@Test
	public void testBookDisplayandGet() throws SQLException {
		createBookObject();
		String test[][] = {
				{"CALLNUMBER", "ISBN", "TITLE", "MAINAUTHOR", "PUBLISHER","YEAR"},
				{"123456789", "123412341234A","The Most Amazing Book","The Most Amazing Author","the most super publisher","2012"}};

		String test1[][] = b.display();
		assertArrayEquals(test1,test);

		//try changing a field in b
		b.setMainAuthor("fake author");
		//assert that calling get updates b with the appropriate fields
		b = b.get();

		assertEquals(b.getMainAuthor(),"The Most Amazing Author");
	}
	/**
	 * pre: createBookObject() has been called, and it's book tuple exists as an instance and in the database;
	 * @throws SQLException 
	 */
	
	@Test
	public void testBookDeleteValid() throws SQLException{
		createBookObject();
		try {
			assertTrue(b.delete());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBookDeleteInvalid() throws SQLException{
		createBookObject();
		assertFalse(b.delete());
	}



	@BeforeClass
	public static void setup(){

		try{
			//clear database
			//drop sequences
			String[] seqName = {"bidCounter","hidCounter","boridCounter","fidCounter"};

			for(int i =0; i<=3; i++){
				Statement dropSeq = c.createStatement();
				String sql = "DROP SEQUENCE " + seqName[i];
				try{
				dropSeq.executeUpdate(sql);
				}
				catch(SQLException e){
					System.out.println("the sequence could not be dropped: " + e.getMessage());
				}
			}

			//create sequences
			for(int i =0; i<=3; i++){
				Statement createSeq = c.createStatement();
				createSeq.executeUpdate("CREATE SEQUENCE " + seqName[i]);
			}

			String[] viewNames = {"OnHoldBookCopy","CheckedOutBookCopy"};

			//drop views
			for(int i =0; i<=1; i++){
				Statement dropViews = c.createStatement();
				try{
					dropViews.executeUpdate("DROP VIEW " + viewNames[i]);
				}catch(SQLException s){
					System.out.println("the view could not be dropped: " + s.getMessage());
				}
			}


			//drop tables
			String[] tableNames = {"HoldRequest","HasSubject","HasAuthor","Fine","Borrowing","BookCopy",
					"Book","Borrower","BorrowerType"};

			for(int i =0; i<=8; i++){
				Statement dropTables = c.createStatement();
				try{
					dropTables.executeUpdate("DROP TABLE " + tableNames[i]);
				}catch(SQLException e){
					System.out.println("the table could not be dropped: " + e.getMessage());
				}
			}


			String BorrowerType = "CREATE TABLE BorrowerType(type VARCHAR(32) NOT NULL,bookTimeLimit INT,PRIMARY KEY(type))";
			String Book = "CREATE TABLE Book(callNumber VARCHAR(32) NOT NULL,isbn CHAR(13) NOT NULL UNIQUE,title VARCHAR(32),mainAuthor VARCHAR(32),publisher VARCHAR(32),year INT,PRIMARY KEY(callNumber))";

			String Borrower = "CREATE TABLE Borrower(bid INT NOT NULL,password VARCHAR(32) NOT NULL,name VARCHAR(32),address VARCHAR(100),phone INT,emailAddress VARCHAR(32),sinOrStNo INT NOT NULL UNIQUE,expiryDate DATE,type VARCHAR(32),PRIMARY KEY(bid),FOREIGN KEY(type) REFERENCES BorrowerType ON DELETE CASCADE)";


			String BookCopy = "CREATE TABLE BookCopy(callNumber VARCHAR(32) NOT NULL, copyNo CHAR(4) NOT NULL, status VARCHAR(7),PRIMARY KEY(callNumber, copyNo),FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";

			// --revaluate varchar length 
			String Borrowing = "CREATE TABLE Borrowing(borid INT NOT NULL,bid INT NOT NULL,callNumber VARCHAR(32) NOT NULL,copyNo CHAR(4) NOT NULL,outDate DATE,inDate DATE,PRIMARY KEY(borid),FOREIGN KEY(bid) REFERENCES Borrower ON DELETE CASCADE,FOREIGN KEY(callNumber, copyNo) REFERENCES BookCopy ON DELETE CASCADE)";
			//--in cents

			String Fine = "CREATE TABLE Fine(fid INT NOT NULL,amount INT, issuedDate DATE,paidDate DATE,borid INT NOT NULL,PRIMARY KEY(fid),FOREIGN KEY(borid) REFERENCES Borrowing ON DELETE CASCADE)";

			String HasAuthor = "CREATE TABLE HasAuthor(callNumber VARCHAR(32) NOT NULL,name VARCHAR(32) NOT NULL,PRIMARY KEY(callNumber, name),FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";

			String HasSubject = "CREATE TABLE HasSubject(callNumber VARCHAR(32) NOT NULL,subject VARCHAR(32) NOT NULL,PRIMARY KEY(callNumber, subject),FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";

			String HoldRequest = "CREATE TABLE HoldRequest(hid INT NOT NULL,bid INT NOT NULL,issuedDate DATE,callNumber VARCHAR(32) NOT NULL,PRIMARY KEY(hid),FOREIGN KEY(bid) REFERENCES Borrower ON DELETE CASCADE,FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";
			//add tables
			Statement addTable = c.createStatement();
			addTable.addBatch(BorrowerType);
			addTable.addBatch(Book);
			addTable.addBatch(Borrower);
			addTable.addBatch(BookCopy);
			addTable.addBatch(Borrowing);
			addTable.addBatch(Fine);
			addTable.addBatch(HasAuthor);
			addTable.addBatch(HasSubject);
			addTable.addBatch(HoldRequest);
			addTable.executeBatch();

			String view1 = "CREATE VIEW CheckedOutBookCopy AS SELECT R.callNumber, R.copyNo, R.outDate, R.outDate + T.bookTimeLimit AS dueDate, S.subject, C.status FROM Borrower B, BorrowerType T, BookCopy C, Borrowing R, HasSubject S WHERE (status = 'out'OR status = 'overdue') AND R.callNumber = C.callNumber AND R.copyNo = C.copyNo AND R.bid = B.bid AND B.type = T.type AND S.callNumber = R.callNumber ORDER BY callNumber ASC";

			String view2 = "CREATE VIEW OnHoldBookCopy AS SELECT callNumber, copyNo FROM BookCopy WHERE status = 'on-hold' ORDER BY callNumber ASC";

			Statement addView1 = c.createStatement();
			addView1.execute(view1);

			Statement addView2 = c.createStatement();
			addView2.execute(view2);


		}catch (SQLException s){
			System.out.println("a non dropping exception: " + s.getMessage());
			s.printStackTrace();
		}


	}

	public void insertSQLTuples() {
		(new InsertParser()).parse();
	}



	@AfterClass
	public static void clearDataBaseAfterTests(){
		try{
			System.out.println("inside the clean up method");
			//clear database
			//drop sequences
			String[] seqName = {"bidCounter","hidCounter","boridCounter","fidCounter"};

			for(int i =0; i<=3; i++){
				Statement dropSeq = c.createStatement();
				String sql = "DROP SEQUENCE " + seqName[i];
				dropSeq.executeUpdate(sql);
			}

			//create sequences
			for(int i =0; i<=3; i++){
				Statement createSeq = c.createStatement();
				createSeq.executeUpdate("CREATE SEQUENCE " + seqName[i]);
			}

			String[] viewNames = {"OnHoldBookCopy","CheckedOutBookCopy"};

			//drop views
			for(int i =0; i<=1; i++){
				Statement dropViews = c.createStatement();
				dropViews.executeUpdate("DROP VIEW " + viewNames[i]);
			}

			String[] tableNames = {"HoldRequest","HasSubject","HasAuthor","Fine","Borrowing","BookCopy",
					"Book","Borrower","BorrowerType"};

			//drop tables
			for(int i =0; i<=8; i++){
				Statement dropTables = c.createStatement();
				dropTables.executeUpdate("DROP TABLE " + tableNames[i]);
			}


			String BorrowerType = "CREATE TABLE BorrowerType(type VARCHAR(32) NOT NULL,bookTimeLimit INT,PRIMARY KEY(type))";
			String Book = "CREATE TABLE Book(callNumber VARCHAR(32) NOT NULL,isbn CHAR(13) NOT NULL UNIQUE,title VARCHAR(32),mainAuthor VARCHAR(32),publisher VARCHAR(32),year INT,PRIMARY KEY(callNumber))";

			String Borrower = "CREATE TABLE Borrower(bid INT NOT NULL,password VARCHAR(32) NOT NULL,name VARCHAR(32),address VARCHAR(100),phone INT,emailAddress VARCHAR(32),sinOrStNo INT NOT NULL UNIQUE,expiryDate DATE,type VARCHAR(32),PRIMARY KEY(bid),FOREIGN KEY(type) REFERENCES BorrowerType ON DELETE CASCADE)";


			String BookCopy = "CREATE TABLE BookCopy(callNumber VARCHAR(32) NOT NULL, copyNo CHAR(4) NOT NULL, status VARCHAR(7),PRIMARY KEY(callNumber, copyNo),FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";

			// --revaluate varchar length 
			String Borrowing = "CREATE TABLE Borrowing(borid INT NOT NULL,bid INT NOT NULL,callNumber VARCHAR(32) NOT NULL,copyNo CHAR(4) NOT NULL,outDate DATE,inDate DATE,PRIMARY KEY(borid),FOREIGN KEY(bid) REFERENCES Borrower ON DELETE CASCADE,FOREIGN KEY(callNumber, copyNo) REFERENCES BookCopy ON DELETE CASCADE)";
			//--in cents

			String Fine = "CREATE TABLE Fine(fid INT NOT NULL,amount INT, issuedDate DATE,paidDate DATE,borid INT NOT NULL,PRIMARY KEY(fid),FOREIGN KEY(borid) REFERENCES Borrowing ON DELETE CASCADE)";

			String HasAuthor = "CREATE TABLE HasAuthor(callNumber VARCHAR(32) NOT NULL,name VARCHAR(32) NOT NULL,PRIMARY KEY(callNumber, name),FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";

			String HasSubject = "CREATE TABLE HasSubject(callNumber VARCHAR(32) NOT NULL,subject VARCHAR(32) NOT NULL,PRIMARY KEY(callNumber, subject),FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";

			String HoldRequest = "CREATE TABLE HoldRequest(hid INT NOT NULL,bid INT NOT NULL,issuedDate DATE,callNumber VARCHAR(32) NOT NULL,PRIMARY KEY(hid),FOREIGN KEY(bid) REFERENCES Borrower ON DELETE CASCADE,FOREIGN KEY(callNumber) REFERENCES Book ON DELETE CASCADE)";
			//add tables
			Statement addTable = c.createStatement();
			addTable.addBatch(BorrowerType);
			addTable.addBatch(Book);
			addTable.addBatch(Borrower);
			addTable.addBatch(BookCopy);
			addTable.addBatch(Borrowing);
			addTable.addBatch(Fine);
			addTable.addBatch(HasAuthor);
			addTable.addBatch(HasSubject);
			addTable.addBatch(HoldRequest);
			addTable.executeBatch();

			String view1 = "CREATE VIEW CheckedOutBookCopy AS SELECT R.callNumber, R.copyNo, R.outDate, R.outDate + T.bookTimeLimit AS dueDate, S.subject, C.status FROM Borrower B, BorrowerType T, BookCopy C, Borrowing R, HasSubject S WHERE (status = 'out'OR status = 'overdue') AND R.callNumber = C.callNumber AND R.copyNo = C.copyNo AND R.bid = B.bid AND B.type = T.type AND S.callNumber = R.callNumber ORDER BY callNumber ASC";

			String view2 = "CREATE VIEW OnHoldBookCopy AS SELECT callNumber, copyNo FROM BookCopy WHERE status = 'on-hold' ORDER BY callNumber ASC";

			Statement addView1 = c.createStatement();
			addView1.execute(view1);

			Statement addView2 = c.createStatement();
			addView2.execute(view2);
			System.out.println("at the end of the clean up method");


		}catch (SQLException s){
			System.out.println("a tear down exception: " +s.getMessage());
			//s.printStackTrace();
		}
	}
}

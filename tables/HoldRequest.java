package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import users.Conn;

/**
 * This class represents the HoldRequest table in the database.
 * 
 * @author Christiaan Fernando
 * 
 */
public class HoldRequest implements Table {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    // The fields for HoldRequest in the table are hid, bid, callNo, issueDate,
    // in that order.
    private Integer hid;
    private Borrower borr;
    private Book b;
    private Calendar issueDate;
    private Connection c;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Default Constructor.
     */
    public HoldRequest() {
        c = Conn.getInstance().getConnection();
    }

    /**
     * HoldRequest Constructor.
     * 
     * This constructor takes in a Calendar, Book, Borrower and creates a
     * HoldRequest Object. This object has not been added to the SQL table yet,
     * so insert() will need to be called on this object in the future if it
     * needs to be added.
     * 
     * @param issueDate
     *            Issue Date for the HoldRequest
     * @param b
     *            Book for the HoldRequest
     * @param borr
     *            Borrower for the HoldRequest
     */
    public HoldRequest(Borrower borr, Book b, Calendar issueDate) {
        this.issueDate = issueDate;
        this.b = b;
        this.borr = borr;

        c = Conn.getInstance().getConnection();
    }

    /**
     * HoldRequest Constructor.
     * 
     * This constructor takes in an Integer and find the HoldRequest entry in
     * the SQL table with this hid. This assumes that the entry already exists.
     * If it does not, this will return an error value of -1 as the hid.
     * 
     * @param hid
     *            HoldRequest id in the SQL table.
     * @throws SQLException
     */
    public HoldRequest(Integer hid) throws SQLException {
        c = Conn.getInstance().getConnection();
        ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE hid = ?");
        ps.setInt(1, hid);

        rs = ps.executeQuery();

        if (rs.next()) {
            this.hid = hid;
            b = new Book();
            borr = new Borrower();
            borr.setBid(rs.getInt(2));
            borr = (Borrower) borr.get();
            b.setCallNumber(rs.getString(4));
            b = (Book) b.get();
            issueDate = new GregorianCalendar();
            this.issueDate.setTime(rs.getDate(3));
        } else {
            this.hid = -1;
        }
    }

    /**
     * Creates a new HoldRequest object based on a result set.
     * Call next() on result set before passing in.
     * @param rs
     * @throws SQLException 
     */
    private HoldRequest(ResultSet rs) throws SQLException {
        c = Conn.getInstance().getConnection();
        hid = rs.getInt(1);
        b = new Book();
        borr = new Borrower();
        borr.setBid(rs.getInt(2));
        borr = (Borrower) borr.get();
        b.setCallNumber(rs.getString(4));
        b = (Book) b.get();
        issueDate = new GregorianCalendar();
        this.issueDate.setTime(rs.getDate(3));
    }

    /**
     * Returns a String representation of the table.
     * 
     * Returns a 2-D String representation of the HoldRequest table.
     * 
     */
    @Override
    public String[][] display() throws SQLException {

        String sql = "SELECT * FROM HoldRequest";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int numCols = md.getColumnCount();
        ArrayList<String[]> requestsGrowable = new ArrayList<String[]>();
        String[] header = new String[numCols];
        for (int i = 0; i < numCols; i++) {
            header[i] = md.getColumnName(i + 1);
        }
        requestsGrowable.add(header);

        int colIndex, paramIndex;
        while (rs.next()) {
            String[] row = new String[numCols];
            colIndex = 0;
            paramIndex = 1;
            // hid
            row[colIndex++] = "" + rs.getInt(paramIndex++);

            // bid
            row[colIndex++] = "" + rs.getInt(paramIndex++);

            //issuedDate
            java.sql.Date sqlDate = rs.getDate(paramIndex++);
            row[colIndex++] = (sqlDate == null)
                    ? "null" : sdf.format(sqlDate);

            // callNo
            row[colIndex++] = rs.getString(paramIndex++);

            requestsGrowable.add(row);
        }

        int numRows = requestsGrowable.size();
        String[][] requests = new String[numRows][];
        for (int i = 0; i < numRows; i++) {
            requests[i] = requestsGrowable.get(i);
        }
        return requests;
    }

    /**
     * Updates the SQL table.
     * 
     * This updates this HoldRequest object in the HoldRequest table. This
     * assumes the item already exists.
     */
    @Override
    public void update() throws SQLException {
        ps = c.prepareStatement("UPDATE holdRequest SET bid = ?, issuedDate = ?, callNumber = ? WHERE hid = ?");

        ps.setInt(4, hid);
        ps.setInt(1, borr.getBid());
        ps.setDate(2, new java.sql.Date(issueDate.getTime().getTime()));
        ps.setString(3, b.getCallNumber());

        int rowCount = ps.executeUpdate();
        if (rowCount == 0) {
            ps.close();
        }
    }

    /**
     * Deletes from the SQL table.
     * 
     * This deletes the HoldRequest object from the HoldRequest table.
     */
    @Override
    public boolean delete() throws SQLException {
        ps = c.prepareStatement("DELETE FROM HoldRequest WHERE hid = ?");
        ps.setInt(1, hid);

        int rowCount = ps.executeUpdate();
        return rowCount == 1;
    }

    /**
     * Inserts into the SQL table.
     * 
     * This inserts the HoldRequest object into the HoldRequest table. This
     * assumes the item doesn't already exist.
     */
    @Override
    public boolean insert() throws SQLException {
        ps = c.prepareStatement("INSERT INTO HoldRequest VALUES (hidCounter.nextVal,?,?,?)");

        ps.setInt(1, borr.getBid());
        ps.setString(3, b.getCallNumber());
        ps.setDate(2, new java.sql.Date(issueDate.getTime().getTime()));

        int numRowsChanged = ps.executeUpdate();
        if (numRowsChanged == 1) {
            ps.close();
            ps = c.prepareStatement("SELECT hidCounter.currval FROM DUAL");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hid = rs.getInt(1);
                return true;
            }
        }
        return false;
    }

    /**
     * Return all HoldRequests.
     * 
     * This returns all HoldRequest objects in the SQL database.
     * 
     * @throws SQLException
     * 
     */
    @Override
    public Collection<Table> getAll() throws SQLException {
        Collection<Table> holdRequests = new ArrayList<Table>();

        ps = c.prepareStatement("SELECT * FROM HoldRequest");

        rs = ps.executeQuery();

        while (rs.next()) {
            holdRequests.add(new HoldRequest(rs));
        }

        return holdRequests;
    }

    /**
     * Returns the ResultSetMetaData object for the HoldRequest table.
     * 
     * Returns an object that contains the meta data for the HoldRequest table.
     * This is an internal helper method to be used by the display method.
     * 
     * @return
     * @throws SQLException
     */
    public ResultSetMetaData getMeta() throws SQLException {
        ps = c.prepareStatement("SELECT * FROM HoldRequest");

        rs = ps.executeQuery();
        return rs.getMetaData();
    }

    /**
     * Return the HoldRequest object corresponding with the set id.
     * 
     * Given a HoldRequest object with an initialized id field, this returns the
     * HoldRequest object with that id field that exists in the SQL database.
     * This is used if either the default constructor was called and the
     * parameters are required, or if some of the parameters are changed and the
     * user wants the original database object.
     * 
     * @throws SQLException
     * 
     */
    @Override
    public Table get() throws SQLException {
        if (hid != 0) {
            HoldRequest tempHR = new HoldRequest(hid);
            if (tempHR.getHid() > 0) {
                return (new HoldRequest((Integer) hid));
            }
        }
        return null;
    }

    /**
     * Return all HoldRequest objects from a given Borrower.
     * 
     * Given a borrower, this returns all the HoldRequests made by that
     * borrower.
     * 
     * @param borr
     *            Borrower whose bid is shared with the HoldRequest
     * @return ArrayList of HoldRequests
     * @throws SQLException
     */
    public Collection<Table> getAll(Borrower borr) throws SQLException {
        Collection<Table> holdRequests = new ArrayList<Table>();
        ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE bid = ?");
        ps.setInt(1, borr.getBid());

        rs = ps.executeQuery();

        while (rs.next()) {

            holdRequests.add(new HoldRequest(rs));
        }

        return holdRequests;
    }

    /**
     * Return all HoldRequest objects for a given Book.
     * 
     * Given a book, this returns all the HoldRequests for a particular book.
     * 
     * @param b
     *            Book whose callNo is shared with the HoldRequest
     * @return ArrayList of HoldRequests
     * @throws SQLException
     */
    public Collection<Table> getAll(Book b) throws SQLException {
        Collection<Table> holdRequests = new ArrayList<Table>();
        ps = c.prepareStatement("SELECT * FROM HoldRequest WHERE callNumber = ?");
        ps.setString(1, b.getCallNumber());

        rs = ps.executeQuery();

        while (rs.next()) {

            holdRequests.add(new HoldRequest(rs));
        }

        return holdRequests;
    }

    /**
     * @return the hid
     */
    public Integer getHid() {
        return hid;
    }

    /**
     * @param hid
     *            the hid to set
     */
    public void setHid(Integer hid) {
        this.hid = hid;
    }

    /**
     * @return the issueDate
     */
    public Calendar getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate
     *            the issueDate to set
     */
    public void setIssueDate(Calendar issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the b
     */
    public Book getB() {
        return b;
    }

    /**
     * @param b
     *            the b to set
     */
    public void setB(Book b) {
        this.b = b;
    }

    /**
     * @return the borr
     */
    public Borrower getBorr() {
        return borr;
    }

    /**
     * @param borr
     *            the borr to set
     */
    public void setBorr(Borrower borr) {
        this.borr = borr;
    }

    /**
     * Returns the attributes of the class as a string.
     * @return 
     */
    @Override
    public String toString() {
        String holdrequest = "";
        holdrequest += "hid = " + hid
                + "\ncall number = " + ((b == null)
                ? null : b.getCallNumber())
                + "\nbid = " + ((borr == null)
                ? null : borr.getBid())
                + "\nissue date = " + ((issueDate == null)
                ? null : sdf.format(issueDate.getTime()));
        return holdrequest;
    }
}

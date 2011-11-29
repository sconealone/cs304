/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import users.Conn;

/**
 * Represents the HasAuthor table in the database
 * @author Mitch
 */
public class HasAuthor implements Displayable {

    /**
     * The author name
     */
    private String name;
    /**
     * The call number
     */
    private String callNumber;
    private Connection con;

    public HasAuthor() {
        con = Conn.getInstance().getConnection();
    }

    /**
     * Creates a new HasAuthor from a result set. internal use only.
     * Call next() on rs before passing
     * @param rs 
     */
    private HasAuthor(ResultSet rs) throws SQLException {
        con = Conn.getInstance().getConnection();
        int paramIndex = 1;
        callNumber = rs.getString(paramIndex++);
        name = rs.getString(paramIndex++);

    }

    /**
     * create a HasAuthor object and add it to the database
     * @param nameArg
     * @param callNumArg
     * @throws SQLException
     */
    public HasAuthor(String nameArg, String callNumArg) throws SQLException {
        con = Conn.getInstance().getConnection();
        String sql = "INSERT INTO HasAuthor VALUES('" + callNumArg + "','" + nameArg + "')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        callNumber = callNumArg;
        name = nameArg;
    }

    /**
     * displays the table as a 2D array of Strings. The first row is the 
     * column names
     * @return
     * @throws SQLException 
     */
    @Override
    public String[][] display() throws SQLException {
        String sql = "SELECT * FROM HasAuthor";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData md = rs.getMetaData();
        int numCol = md.getColumnCount();
        String[] colNames = new String[numCol];
        colNames[0] = md.getColumnName(1);
        colNames[1] = md.getColumnName(2);

        ArrayList<String[]> tableGrowable = new ArrayList<String[]>();
        tableGrowable.add(colNames);

        while (rs.next()) {
            HasAuthor h = new HasAuthor(rs);
            String[] tuple = new String[numCol];
            tuple[0] = h.callNumber;
            tuple[1] = h.name;
            tableGrowable.add(tuple);
        }
        int numRow = tableGrowable.size();
        String[][] table = new String[numRow][];
        for (int i = 0; i < numRow; i++) {
            table[i] = tableGrowable.get(i);
        }
        return table;
    }
}

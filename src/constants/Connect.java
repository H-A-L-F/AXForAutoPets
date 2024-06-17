package constants;

import java.sql.*;

public class Connect {
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "auto_pets";
    private final String HOST = "localhost";
    private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    public ResultSet rs;
    public ResultSetMetaData rsmd;
    public PreparedStatement ps;

    private Connection con;
    private Statement st;

    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connect getInstance() {
        if (instance == null) instance = new Connect();
        return instance;
    }

    public long execQuery(String query) {
        try {
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            if(!rs.next()) return -1;
            return rs.getLong(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long execUpdate(String query) {
        try {
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            if(!rs.next()) return -1;
            return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

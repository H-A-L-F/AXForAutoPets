package constants;

import java.sql.*;

public class Connect {
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "auto_pets";
    private final String HOST = "10.22.68.105:3306";
    private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    public ResultSet rs;
    public ResultSetMetaData rsmd;

    private Connection con;
    private Statement st;

    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connect getInstance() {
        if (instance == null) instance = new Connect();
        return instance;
    }

    public ResultSet execQuery(String query) {
        try {
            rs = st.executeQuery(query);
            rsmd = rs.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void execUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

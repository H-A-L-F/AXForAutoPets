package constants;

import java.sql.*;

public class Connect {

    private static final String MYSQL_HOST = "roundhouse.proxy.rlwy.net";
    private static final String MYSQL_PORT = "43124";
    private static final String MYSQL_DATABASE = "railway";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "lWNLwSKOABTlbSXAMcxJmmeKzFGucGDP";
    private static final String constr = String.format("mysql://%s:%s@%s:%s/%s", MYSQL_USER, MYSQL_PASSWORD, MYSQL_HOST, MYSQL_PORT, MYSQL_DATABASE);

    public ResultSet rs;
    public ResultSetMetaData rsmd;

    private Connection con;
    private Statement st;

    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(constr);
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

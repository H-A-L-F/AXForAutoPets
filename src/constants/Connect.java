package constants;

import java.sql.*;

public class Connect {

    private static final String user = "arya";
    private static final String password = "nDp1Jx9TX1d7uAOwX2ZMcw";
    private static final String database = "autopets";
    private static final String constr = "postgresql://%s:%s@%s-6879.6xw.aws-ap-southeast-1.cockroachlabs.cloud:26257/defaultdb?sslmode=verify-full";

    public ResultSet rs;
    public ResultSetMetaData rsmd;

    private Connection con;
    private Statement st;

    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(String.format(constr, user, password, database));
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
}

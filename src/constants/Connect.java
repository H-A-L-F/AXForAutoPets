package constants;

import java.sql.*;

public class Connect {

    private static final String user = "root";
    private static final String password = "lWNLwSKOABTlbSXAMcxJmmeKzFGucGDP";
    private static final String proxy = "roundhouse.proxy.rlwy.net";
    private static final String port = "43124";
    private static final String database = "railway";
    private static final String constr = "mysql://%s:%s@%s:%s/%s";

    public ResultSet rs;
    public ResultSetMetaData rsmd;

    private Connection con;
    private Statement st;

    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(String.format(constr, user, password, proxy, port, database));
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

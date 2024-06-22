package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository extends ModelRepository {

    private String name;
    private String password;

    private static UserRepository instance;

    private UserRepository() {
    }

    private UserRepository(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    private UserRepository(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    public static UserRepository getInstance(int id, String name, String password) {
        if (instance == null) instance = new UserRepository(id, name, password);
        return instance;
    }

    public static UserRepository getInstance(String name, String password) {
        if (instance == null) instance = new UserRepository(name, password);
        return instance;
    }

    public static ArrayList<UserRepository> getTopWin(int limit) {
        String query = "SELECT * FROM `user` ORDER BY wins DESC LIMIT %d";
        ArrayList<UserRepository> res = new ArrayList<>();
        ResultSet rs = con.execQueryWithRes(String.format(query, limit));
        try {
            while(rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String userpass = rs.getString(3);
                res.add(new UserRepository(id, username, userpass));
            }
            return res;
        } catch (SQLException e) {
            System.out.println("Leaderboard failed");
            throw new RuntimeException(e);
        }
    }

    public void updateWins(int wins) {
        String query = "UPDATE `user` SET wins = wins + %d WHERE id = %d";
        con.execQueryWithKey(String.format(query, wins, id));
    }

    public long insert(String name, String password) {
        String query = "INSERT INTO User (name, password) values('%s', '%s')";
        return con.execQueryWithKey(String.format(query, name, password));
    }

    private UserRepository getUserRepoFromRs(ResultSet rs) {
        try{
            if(!rs.next()) {
                System.out.println("Failed to get last inserted user");
                return null;
            }
            this.id = rs.getInt(1);
            this.name = rs.getString(2);
            this.password = rs.getString(3);
            return new UserRepository(id, name, password);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void logout() {
        instance = null;
    }

    public static boolean checkName(String name) {
        String query = "SELECT * FROM user WHERE name = '%s'";
        ResultSet rs = con.execQueryWithRes(String.format(query, name));
        try {
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getWins() {
        String query = "SELECT sum(m.win) FROM `user` u join `match` m on u.id = match.user_id WHERE u.id = %d";
        ResultSet rs = con.execQueryWithRes(String.format(query, id));
        try {
            if(!rs.next()) {
                System.out.println("Fail to get wins for userid: " + id);
                return -1;
            }
            return rs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public String getName() {
        return name;
    }

}

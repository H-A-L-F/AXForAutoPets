package repository;

import java.sql.ResultSet;

public class MatchRepository extends ModelRepository {

    private int user_id;
    private String team_name;
    private int win;

    private static MatchRepository instance;

    private MatchRepository(int id, int user_id, String team_name, int win) {
        this.id = id;
        this.user_id = user_id;
        this.team_name = team_name;
        this.win = win;
    }

    public static MatchRepository getInstance() {
        return instance;
    }

    public static MatchRepository newInstance(int user_id, String team_name) {
        if (instance == null) {
            long id = insert(user_id, team_name);
            ResultSet rs = getRsFromId(id);
            return getMatchFromRs(rs);
        }
        return instance;
    }

    public static long insert(int user_id, String team_name) {
        String query = "INSERT INTO `match`(user_id, team_name, win) VALUES (%d, '%s', %d)";
        return con.execQuery(String.format(query, user_id, team_name, 0));
    }

    public static MatchRepository getMatchFromRs(ResultSet rs) {
        try {
            if(!rs.next()) {
                System.out.println("Failed to get match!");
                throw new Exception();
            }
            int id = rs.getInt(1);
            int curr_user_id = rs.getInt(2);
            String curr_team_name = rs.getString(3);
            int win = rs.getInt(4);
            return new MatchRepository(id, curr_user_id, curr_team_name, win);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateWin(int win) {
        String query = "UPDATE `match` SET win = %d WHERE id = %d";
        con.execUpdate(String.format(query, win, id));
    }
}

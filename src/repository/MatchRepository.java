package repository;

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
            insert(user_id, team_name);
            instance = getLastInserted(user_id, team_name);
        }
        return instance;
    }

    public static void insert(int user_id, String team_name) {
        String query = "INSERT INTO `match`(user_id, team_name, win) VALUES (%d, '%s', %d)";
        con.execUpdate(String.format(query, user_id, team_name, 0));
    }

    private static MatchRepository getLastInserted(int user_id, String team_name) {
        String query = "SELECT * FROM `match` WHERE user_id = %d and team_name = '%s' ORDER BY id DESC LIMIT 1";
        con.execQuery(String.format(query, user_id, team_name));
        try {
            if(!con.rs.next()) {
                System.out.println("Failed to get match!");
                throw new Exception();
            }
            int id = con.rs.getInt(1);
            int curr_user_id = con.rs.getInt(2);
            String curr_team_name = con.rs.getString(3);
            int win = con.rs.getInt(4);
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

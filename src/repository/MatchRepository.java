package repository;

public class MatchRepository extends ModelRepository {

    private int id;
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

    private MatchRepository(int user_id, String team_name) {
        this.user_id = user_id;
        this.team_name = team_name;
        this.win = 0;
    }

    public static MatchRepository getInstance(int id, int user_id, String player_name, int win) {
        if (instance == null) instance = new MatchRepository(id, user_id, player_name, win);
        return instance;
    }

    public static MatchRepository getInstance(int user_id, String team_name) {
        if (instance == null) instance = new MatchRepository(user_id, team_name);
        return instance;
    }

    public void insert() {
        String query = "INSERT INTO match(user_id, team_name, win) VALUES (%d, %s, %d)";
        con.execUpdate(String.format(query, user_id, team_name, win));
        getLastInserted(user_id, team_name);
    }

    public void insert(int user_id, String team_name) {
        String query = "INSERT INTO match(user_id, team_name, win) VALUES (%d, '%s', %d)";
        con.execUpdate(String.format(query, user_id, team_name, 0));
        getLastInserted(user_id, team_name);
    }

    private MatchRepository getLastInserted(int user_id, String team_name) {
        String query = "SELECT * FROM match WHERE user_id = %d and team_name = '%s' ORDER BY id DESC LIMIT 1";
        con.execQuery(String.format(query, user_id, team_name));
        try {
            if(!con.rs.next()) {
                System.out.println("Failed to get match!");
                return this;
            }
            this.id = con.rs.getInt(1);
            this.user_id = con.rs.getInt(2);
            this.team_name = con.rs.getString(3);
            this.win = con.rs.getInt(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void updateWin(int win) {
        String query = "UPDATE match SET win = %d WHERE id = %d";
        con.execUpdate(String.format(query, win, id));
    }
}

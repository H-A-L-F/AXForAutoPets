package repository;

public class RoundRepository extends ModelRepository{
    private int id;
    private int match_id;
    private int round;

    private static RoundRepository instance;

    private RoundRepository(int id, int match_id, int round) {
        this.id = id;
        this.match_id = match_id;
        this.round = round;
    }

    private RoundRepository(int match_id, int round) {
        this.match_id = match_id;
        this.round = round;
    }

    public RoundRepository getInstance() {
        return instance;
    }

    public static RoundRepository newInstance(int match_id, int round) {
        insert(match_id, round);
        instance = getLastInserted(match_id, round);
        return instance;
    }

    private static void insert(int match_id, int round) {
        String query = "INSERT INTO round (match_id, round) VALUES(%d, %d)";
        con.execUpdate(String.format(query, match_id, round));
    }

    private static RoundRepository getLastInserted(int match_id, int round) {
        String query = "SELECT * FROM round WHERE match_id = %d and round = %d ORDER BY id DESC LIMIT 1";
        con.execQuery(String.format(query, match_id, round));
        try {
            if(!con.rs.next()) {
                System.out.println("Failed to get round");
                throw new Exception();
            }
            int id = con.rs.getInt(1);
            int curr_match_id = con.rs.getInt(2);
            int curr_round = con.rs.getInt(3);
            return new RoundRepository(id, curr_match_id, curr_round);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

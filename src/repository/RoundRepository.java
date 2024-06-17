package repository;

import java.sql.ResultSet;

public class RoundRepository extends ModelRepository {

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

    public static RoundRepository getInstance() {
        return instance;
    }

    public static RoundRepository newInstance(int match_id, int round) {
        long id = insert(match_id, round);
        ResultSet rs = getRsFromId(id);
        instance = getRoundRepoFromRS(rs);
        return instance;
    }

    private static long insert(int match_id, int round) {
        String query = "INSERT INTO round (match_id, round) VALUES(%d, %d)";
        return con.execUpdate(String.format(query, match_id, round));
    }

    private static RoundRepository getRoundRepoFromRS(ResultSet rs) {
        try {
            if (!rs.next()) {
                System.out.println("Failed to get round");
                throw new Exception();
            }
            int id = rs.getInt(1);
            int curr_match_id = rs.getInt(2);
            int curr_round = rs.getInt(3);
            return new RoundRepository(id, curr_match_id, curr_round);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static RoundRepository getLastInserted(ResultSet rs) {
        return getRoundRepoFromRS(rs);
    }

    public static RoundRepository getRandRoundRepository(int round) {
        String query = "SELECT * FROM round WHERE round = %d ORDER BY RAND() LIMIT 1";
        con.execQuery(String.format(query, round));
        return getRoundRepoFromRS(con.rs);
    }
}

package repository;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RoundRepository extends ModelRepository {

    private int match_id;
    private int enm_round_id;
    private int round;

    private static RoundRepository instance;

    private RoundRepository(int id, int match_id, int enm_round_id, int round) {
        this.id = id;
        this.match_id = match_id;
        this.enm_round_id = enm_round_id;
        this.round = round;
    }

    public static RoundRepository getInstance() {
        return instance;
    }

    public static RoundRepository newInstance(int match_id, int enm_round_id, int round) {
        long id = insert(match_id, enm_round_id, round);
        ResultSet rs = getRsFromId(id, "round");
        instance = getRoundRepoFromRS(rs);
        return instance;
    }

    public static RoundRepository getRoundRepoById(int id) {
        ResultSet rs = getRsFromId(id, "round");
        return getRoundRepoFromRS(rs);
    }

    private static long insert(int match_id, int enm_round_id, int round) {
        String query = "INSERT INTO round (match_id, enm_round_id, round) VALUES(%d, %d, %d)";
        return con.execQueryWithKey(String.format(query, match_id, enm_round_id, round));
    }

    private static RoundRepository getRoundRepoFromRS(ResultSet rs) {
        try {
            if (!rs.next()) {
                System.out.println("Failed to get round");
                throw new Exception();
            }
            int id = rs.getInt(1);
            int curr_match_id = rs.getInt(2);
            int curr_enm_round_id = rs.getInt(3);
            int curr_round = rs.getInt(4);
            return new RoundRepository(id, curr_match_id, curr_enm_round_id, curr_round);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<RoundRepository> getRoundsForMatch(int match_id) {
        String query = "SELECT * FROM round WHERE match_id = %d ORDER BY round ASC";
        ResultSet rs = con.execQueryWithRes(String.format(query, match_id));
        ArrayList<RoundRepository> res = new ArrayList<RoundRepository>();
        try {
            while(rs.next()) {
                res.add(new RoundRepository(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static RoundRepository getLastInserted(ResultSet rs) {
        return getRoundRepoFromRS(rs);
    }

    public static RoundRepository getRandRoundRepository(int round) {
        String query = "SELECT * FROM round WHERE round = %d ORDER BY RAND() LIMIT 1";
        ResultSet rs = con.execQueryWithRes(String.format(query, round));
        return getRoundRepoFromRS(rs);
    }

    public int getEnmRoundId() {
        return enm_round_id;
    }

    public int getMatch_id() {
        return match_id;
    }
}

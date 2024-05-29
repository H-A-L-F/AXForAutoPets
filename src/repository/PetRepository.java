package repository;

import models.Pet;

public class PetRepository extends ModelRepository {
    private int id;
    private int round_id;
    private String name;
    private int atk;
    private int hp;
    private int lv;
    private int exp;
    private int pos;

    private PetRepository(int id, int round_id, String name, int atk, int hp, int lv, int exp, int pos) {
        this.id = id;
        this.round_id = round_id;
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.lv = lv;
        this.exp = exp;
        this.pos = pos;
    }

    private static PetRepository newRepository(int round_id, String name, int atk, int hp, int lv, int exp, int pos) {
        insert(round_id, name, atk, hp, lv, exp, pos);
        return getLastInserted(round_id, pos);
    }

    private static void insert(int round_id, String name, int atk, int hp, int lv, int exp, int pos) {
        String query = "INSERT INTO pet (round_id, name, atk, hp, lv, exp, pos) VALUES(%d, %s, %d, %d, %d, %d %d)";
        con.execUpdate(String.format(query, round_id, name, atk, hp, lv, exp, pos));
    }

    private static PetRepository getLastInserted(int round_id, int pos) {
        String query = "SELECT * FROM pet WHERE round_id = %d and pos = %d";
        con.execQuery(String.format(query, round_id, pos));
        try {
            if(!con.rs.next()) {
                System.out.println("Failed to get pet");
                throw new Exception();
            }
            int id = con.rs.getInt(1);
            int curr_round_id = con.rs.getInt(2);
            String name = con.rs.getString(3);
            int atk = con.rs.getInt(4);
            int hp = con.rs.getInt(5);
            int lv = con.rs.getInt(6);
            int exp = con.rs.getInt(7);
            int curr_pos = con.rs.getInt(8);
            return new PetRepository(id, curr_round_id, name, atk, hp, lv, exp, curr_pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package repository;

import constants.FruitFactory;
import constants.PetFactory;
import constants.PetList;
import models.Fruit;
import models.Pet;
import models.Team;

import java.sql.SQLException;

public class PetRepository extends ModelRepository {

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

    public static PetRepository newInstance(int round_id, String name, int atk, int hp, int lv, int exp, int pos) {
        insert(round_id, name, atk, hp, lv, exp, pos);
        return getLastInserted(round_id, pos);
    }

    private static void insert(int round_id, String name, int atk, int hp, int lv, int exp, int pos) {
        String query = "INSERT INTO pet (round_id, name, atk, hp, lv, exp, pos) VALUES(%d, %s, %d, %d, %d, %d %d)";
        con.execUpdate(String.format(query, round_id, name, atk, hp, lv, exp, pos));
    }

    private static PetRepository convertPetRepoFromRS() {
        try {
            int id = con.rs.getInt(1);
            int curr_round_id = con.rs.getInt(2);
            String name = con.rs.getString(3);
            int atk = con.rs.getInt(4);
            int hp = con.rs.getInt(5);
            int lv = con.rs.getInt(6);
            int exp = con.rs.getInt(7);
            int curr_pos = con.rs.getInt(8);
            return new PetRepository(id, curr_round_id, name, atk, hp, lv, exp, curr_pos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PetRepository getPetRepoFromRS() {
        try {
            if(!con.rs.next()) {
                System.out.println("Failed to get pet");
                throw new Exception();
            }
            return convertPetRepoFromRS();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PetRepository getLastInserted(int round_id, int pos) {
        String query = "SELECT * FROM pet WHERE round_id = %d and pos = %d";
        con.execQuery(String.format(query, round_id, pos));
        return getPetRepoFromRS();
    }

    public static Pet[] getPetsForRound(PetFactory petF, FruitFactory fruF, int round_id) {
        Pet[] pets = new Pet[Team.END_SIZE];
        String query = "SELECT * FROM pet WHERE round_id = %d";
        con.execQuery(String.format(query, round_id));
        for(int i = 1; i <= Team.END_SIZE; i++) {
            try {
                if(con.rs.absolute(i)) {
                    PetRepository petRepo = convertPetRepoFromRS();
                    pets[i] = petRepoToPet(petF, fruF, petRepo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pets;
    }

    private static Pet petRepoToPet(PetFactory petF, FruitFactory fruF, PetRepository petRepo) {
        Pet pet = petF.getPet(PetList.valueOf(petRepo.name.toUpperCase()));
        pet.setStats(petRepo.atk, petRepo.hp, petRepo.lv, petRepo.exp, petRepo.pos);
        Fruit fruit = FruitRepository.getFruit(fruF, petRepo.id);
        if(fruit != null) pet.eatFruit(fruit);
        return pet;
    }

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }

    public int getLv() {
        return lv;
    }

    public int getExp() {
        return exp;
    }

    public int getPos() {
        return pos;
    }
}

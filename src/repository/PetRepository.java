package repository;

import constants.FruitFactory;
import constants.PetFactory;
import constants.PetList;
import models.Fruit;
import models.Pet;
import models.Team;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        long id = insert(round_id, name, atk, hp, lv, exp, pos);
        ResultSet rs = getRsFromId(id, "pet");
        return convertPetRepoFromRS(rs);
    }

    private static long insert(int round_id, String name, int atk, int hp, int lv, int exp, int pos) {
        String query = "INSERT INTO pet (round_id, name, atk, hp, lv, exp, pos) VALUES(%d, '%s', %d, %d, %d, %d, %d)";
        return con.execQueryWithKey(String.format(query, round_id, name, atk, hp, lv, exp, pos));
    }

    private static PetRepository convertPetRepoFromRS(ResultSet rs) {
        try {
            if(!rs.next()) {
                System.out.println("Failed to get pet");
                return null;
            }
            int id = rs.getInt(1);
            int curr_round_id = rs.getInt(2);
            String name = rs.getString(3);
            int atk = rs.getInt(4);
            int hp = rs.getInt(5);
            int lv = rs.getInt(6);
            int exp = rs.getInt(7);
            int curr_pos = rs.getInt(8);
            return new PetRepository(id, curr_round_id, name, atk, hp, lv, exp, curr_pos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PetRepository getPetRepoFromRS(ResultSet rs) {
        try {
            if(!rs.next()) {
                System.out.println("Failed to get pet");
                throw new Exception();
            }
            return convertPetRepoFromRS(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PetRepository getLastInserted(ResultSet rs) {
        return getPetRepoFromRS(rs);
    }

    public static Pet[] getPetsForRound(PetFactory petF, FruitFactory fruF, int round_id) {
        Pet[] pets = new Pet[Team.END_SIZE];
        String query = "SELECT * FROM pet WHERE round_id = %d LIMIT %d";
        ResultSet rs = con.execQueryWithRes(String.format(query, round_id, Team.END_SIZE));
        ArrayList<PetRepository> temp_pets = getPetReposFromRs(rs);
        if(temp_pets == null) return null;
        for(PetRepository p : temp_pets) {
            pets[p.pos] = petRepoToPet(petF, fruF, p);
        }
        return pets;
    }

    private static ArrayList<PetRepository> getPetReposFromRs(ResultSet rs) {
        ArrayList<PetRepository> res = new ArrayList<>();
        try {
            while(rs.next()) {
                int id = rs.getInt(1);
                int curr_round_id = rs.getInt(2);
                String name = rs.getString(3);
                int atk = rs.getInt(4);
                int hp = rs.getInt(5);
                int lv = rs.getInt(6);
                int exp = rs.getInt(7);
                int curr_pos = rs.getInt(8);
                res.add(new PetRepository(id, curr_round_id, name, atk, hp, lv, exp, curr_pos));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

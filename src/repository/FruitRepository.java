package repository;

import constants.FruitFactory;
import constants.FruitList;
import models.Fruit;

import java.sql.ResultSet;

public class FruitRepository extends ModelRepository{

    private int pet_id;
    private String name;

    private FruitRepository(int id, int pet_id, String name) {
        this.id = id;
        this.pet_id = pet_id;
        this.name = name;
    }

    public static FruitRepository newInstance(int pet_id, String name) {
        long id = insert(pet_id, name);
        ResultSet rs = getRsFromId(id, "fruit");
        return getFruitRepoFromRS(rs);
    }

    private static long insert(int pet_id, String name) {
        String query = "INSERT INTO fruit (pet_id, name) VALUES (%d, '%s')";
        return con.execQueryWithKey(String.format(query, pet_id, name));
    }

    private static FruitRepository getFruitRepoFromRS(ResultSet rs) {
        try {
            if(!rs.next()) {
//                System.out.println("Failed to get fruit");
                throw new Exception();
            }
            int id = rs.getInt(1);
            int curr_pet_id = rs.getInt(2);
            String name = rs.getString(3);
            return new FruitRepository(id, curr_pet_id, name);
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    private static FruitRepository getLastInserted(ResultSet rs) {
        try {
            if(!rs.next()) {
                System.out.println("Failed to get fruit");
                throw new Exception();
            }
            int id = rs.getInt(1);
            int curr_pet_id = rs.getInt(2);
            String name = rs.getString(3);
            return new FruitRepository(id, curr_pet_id, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Fruit getFruit(FruitFactory fruitFactory, int pet_id) {
        String query = "SELECT * FROM fruit WHERE pet_id = %d";
        ResultSet rs = con.execQueryWithRes(String.format(query, pet_id));
        FruitRepository fruitRepo = getFruitRepoFromRS(rs);
        if(fruitRepo == null) return null;
        return fruitFactory.getFruit(FruitList.valueOf(fruitRepo.name.toUpperCase()));
    }
}

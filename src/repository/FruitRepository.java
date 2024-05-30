package repository;

import constants.FruitFactory;
import constants.FruitList;
import models.Fruit;

public class FruitRepository extends ModelRepository{

    private int pet_id;
    private String name;

    private FruitRepository(int id, int pet_id, String name) {
        this.id = id;
        this.pet_id = pet_id;
        this.name = name;
    }

    public static FruitRepository newInstance(int pet_id, String name) {
        insert(pet_id, name);
        return getLastInserted(pet_id);
    }

    private static void insert(int pet_id, String name) {
        String query = "INSERT INTO fruit (pet_id, name) VALUES (%d, '%s')";
        con.execUpdate(String.format(query, pet_id, name));
    }

    private static FruitRepository getFruitRepoFromRS() {
        try {
            if(!con.rs.next()) {
//                System.out.println("Failed to get fruit");
                throw new Exception();
            }
            int id = con.rs.getInt(1);
            int curr_pet_id = con.rs.getInt(2);
            String name = con.rs.getString(3);
            return new FruitRepository(id, curr_pet_id, name);
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    private static FruitRepository getLastInserted(int pet_id) {
        String query = "SELECT * FROM fruit WHERE pet_id = %d ORDER BY id DESC LIMIT 1";
        con.execQuery(String.format(query, pet_id));
        return getFruitRepoFromRS();
    }

    public static Fruit getFruit(FruitFactory fruitFactory, int pet_id) {
        String query = "SELECT * FROM fruit WHERE pet_id = %d";
        con.execQuery(String.format(query, pet_id));
        FruitRepository fruitRepo = getFruitRepoFromRS();
        if(fruitRepo == null) return null;
        return fruitFactory.getFruit(FruitList.valueOf(fruitRepo.name.toUpperCase()));
    }
}

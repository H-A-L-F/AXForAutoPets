package constants;

import main.Arena;
import models.Fruit;
import models.Pet;
import models.Team;

public class FruitFactory {
    private Arena arena;
    private final Team pTeam;

    public FruitFactory(Arena arena) {
        this.arena = arena;
        this.pTeam = arena.getPlayerTeam();
    }

    public Fruit getApple() {
        return new Fruit(FruitList.APPLE, 1) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.buff(1, 1);
            }
        };
    }

    public Fruit getHoney() {
        return new Fruit(FruitList.HONEY, 1) {
            @Override
            public void onFaint(int pos) {
                super.onFaint(pos);
                Pet temp = PetFactory.getBee();
                pTeam.addPet(temp, pos);
            }
        };
    }

    public Fruit getFood(FruitList name) {
        return switch (name) {
            case APPLE -> getApple();
            case HONEY -> getHoney();
//            case CUPCAKE -> getCupcake();
//            case MEAT_BONE -> getMeatBone();
//            case SALAD_BOWL -> getSaladBowl();
//            case GARLIC -> getGarlic();
//            case CANNED_FOOD -> getCannedFood();
//            case PEAR -> getPear();
//            case CHILI -> getChili();
//            case CHOCOLATE -> getChocolate();
//            case SUSHI -> getSushi();
//            case MELON -> getMelon();
//            case MUSHROOM -> getMushroom();
//            case PIZZA -> getPizza();
//            case STEAK -> getSteak();
            default -> null;
        };
    }

}

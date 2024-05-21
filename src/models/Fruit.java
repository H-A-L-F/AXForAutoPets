package models;

import constants.FruitList;

public class Fruit extends Entity{
    private FruitList name;

    public Fruit(FruitList name, int tier) {
        super(tier);
        this.name = name;
    }

    public void onEaten(Pet pet) {
    }

    public void onFaint(int pos) {
    }

    public int onDamaged(int dmg) {
        return dmg;
    }

    @Override
    public String getName() {
        return this.name.toString();
    }
}

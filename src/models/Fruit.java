package models;

import constants.FruitList;

public class Fruit {
    private FruitList name;
    private int tier;

    public Fruit(FruitList name, int tier) {
        this.name = name;
        this.tier = tier;
    }

    public void onEaten(Pet pet) {
    }

    public void onFaint(int pos) {
    }

    public String getName() {
        return name.getName();
    }
}

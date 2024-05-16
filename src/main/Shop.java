package main;

import constants.ShopStat;
import models.Fruit;
import models.Pet;

import java.util.Vector;

public class Shop {
    private ShopStat shopStat;

    private Vector<Pet> pets;
    private Vector<Fruit> fruits;

    private Vector<Pet> frozenPet;
    private Vector<Fruit> frozenFruit;

    public Shop() {
        shopStat = ShopStat.TIER1;

        pets = new Vector<>(shopStat.getPET_SLOT());
        fruits = new Vector<>(shopStat.getFRUIT_SLOT());
        frozenPet = new Vector<>(shopStat.getPET_SLOT());
        frozenFruit = new Vector<>(shopStat.getFRUIT_SLOT());
    }
}

package main;

import constants.FruitFactory;
import constants.PetFactory;
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

    private PetFactory petFactory;
    private FruitFactory fruitFactory;

    public Shop(PetFactory petFactory) {
        shopStat = ShopStat.TIER1;

        pets = new Vector<>(shopStat.getPET_SLOT());
        fruits = new Vector<>(shopStat.getFRUIT_SLOT());
        frozenPet = new Vector<>(shopStat.getPET_SLOT());
        frozenFruit = new Vector<>(shopStat.getFRUIT_SLOT());

        this.petFactory = petFactory;
        this.fruitFactory = fruitFactory;
    }

    public void nextRound(int round) {
        switch (round) {
            case 1:
                shopStat = ShopStat.TIER1;
                break;
            case 3:
                shopStat = ShopStat.TIER2;
                break;
            case 5:
                shopStat = ShopStat.TIER3;
                break;
            case 7:
                shopStat = ShopStat.TIER4;
                break;
            case 9:
                shopStat = ShopStat.TIER5;
                break;
            case 11:
                shopStat = ShopStat.TIER6;
                break;
        }
    }

    private void generateShop() {
        generatePetShop();
        generateFruitShop();
    }

    private void generatePetShop() {
        for(int i = 0; i < shopStat.getPET_SLOT(); i++) {
            if(frozenPet.get(i) != null) {
                pets.insertElementAt(frozenPet.get(i), i);
                continue;
            }
            petFactory.getPet(shopStat.getTIER());
        }
    }

    private void generateFruitShop() {
        for(int i = 0; i < shopStat.getFRUIT_SLOT(); i++) {
            if(frozenFruit.get(i) != null) {
                fruits.insertElementAt(frozenFruit.get(i), i);
                continue;
            }
            fruitFactory.getFruit(shopStat.getTIER());
        }
    }
}

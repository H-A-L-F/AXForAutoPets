package main;

import console_input.ConsoleInput;
import constants.FruitFactory;
import constants.Lib;
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

    private ConsoleInput in;

    public Shop(PetFactory petFactory, FruitFactory fruitFactory) {
        shopStat = ShopStat.TIER1;

        pets = new Vector<>(shopStat.getPET_SLOT());
        fruits = new Vector<>(shopStat.getFRUIT_SLOT());
        frozenPet = new Vector<>(shopStat.getPET_SLOT());
        frozenFruit = new Vector<>(shopStat.getFRUIT_SLOT());

        this.petFactory = petFactory;
        this.fruitFactory = fruitFactory;
        in = ConsoleInput.getInstance();
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

    public void startShop() {
        showShop();
        menuShop();
    }

    private void optShop() {
        System.out.println("1. Buy");
        System.out.println("2. Freeze");
        System.out.println("3. Roll");
        System.out.println("4. Arrange");
        System.out.println("5. Sell");
        System.out.println("6. Exit");
    }

    private void menuShop() {
        boolean run = true;
        int opt = 0;
        while (run) {
            optShop();
            opt = in.getIntInRange(1, 6, ">> ");

            switch (opt) {
                case 1:
                    menuBuy();
                    break;
                case 2:
                    menuFreeze();
                    break;
                case 3:
                    menuRoll();
                    break;
                case 4:
                    menuArrange();
                    break;
                case 5:
                    menuSell();
                    break;
                case 6:
                    run = false;
                    break;
            }
        }
    }

    private void menuBuy() {

    }

    private void menuFreeze() {

    }

    private void menuRoll() {

    }

    private void menuArrange() {

    }

    private void menuSell() {

    }

    public void showShop() {
        generateShop();
        printShop();
    }

    private void printShop() {
        System.out.println("Shop:");
        Lib.printSlots(pets);
        Lib.printFruits(fruits);
    }

    private void generateShop() {
        generatePetShop();
        generateFruitShop();
    }

    private void generatePetShop() {
        for (int i = 0; i < shopStat.getPET_SLOT(); i++) {
            if (frozenPet.get(i) != null) {
                pets.insertElementAt(frozenPet.get(i), i);
                continue;
            }
            petFactory.getPet(shopStat.getTIER());
        }
    }

    private void generateFruitShop() {
        for (int i = 0; i < shopStat.getFRUIT_SLOT(); i++) {
            if (frozenFruit.get(i) != null) {
                fruits.insertElementAt(frozenFruit.get(i), i);
                continue;
            }
            fruitFactory.getFruit(shopStat.getTIER());
        }
    }
}

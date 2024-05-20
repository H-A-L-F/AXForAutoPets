package main;

import console_input.ConsoleInput;
import constants.FruitFactory;
import constants.Lib;
import constants.PetFactory;
import constants.ShopStat;
import models.Entity;
import models.Fruit;
import models.Pet;
import models.Team;

import java.util.Vector;

public class Shop {
    private ShopStat shopStat;

    private Vector<Pet> pets;
    private Vector<Fruit> fruits;

    private Vector<Pet> frozenPet;
    private Vector<Fruit> frozenFruit;

    private PetFactory petFactory;
    private FruitFactory fruitFactory;

    private Arena arena;
    private Team pteam;
    private ConsoleInput in;

    public Shop(Arena arena, PetFactory petFactory, FruitFactory fruitFactory, Team pteam) {
        shopStat = ShopStat.TIER1;

        pets = new Vector<>();
        fruits = new Vector<>();
        frozenPet = new Vector<>();
        frozenFruit = new Vector<>();

        updateSlot();

        this.petFactory = petFactory;
        this.fruitFactory = fruitFactory;

        this.arena = arena;
        this.pteam = pteam;
        in = ConsoleInput.getInstance();
    }

    public void nextRound(int round) {
        switch (round) {
            case 1:
                updateShopStat(ShopStat.TIER1);
                break;
            case 3:
                updateShopStat(ShopStat.TIER2);
                break;
            case 5:
                updateShopStat(ShopStat.TIER3);
                break;
            case 7:
                updateShopStat(ShopStat.TIER4);
                break;
            case 9:
                updateShopStat(ShopStat.TIER5);
                break;
            case 11:
                updateShopStat(ShopStat.TIER6);
                break;
        }
    }

    private void updateShopStat(ShopStat shopStat) {
        this.shopStat = shopStat;
        updateSlot();
    }

    private void updateSlot() {
        pets.setSize(shopStat.getPET_SLOT());
        fruits.setSize(shopStat.getFRUIT_SLOT());
        frozenPet.setSize(shopStat.getPET_SLOT());
        frozenFruit.setSize(shopStat.getFRUIT_SLOT());
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
        if(arena.getMoney() < ShopStat.BUY_PRICE) {
            System.out.println("You have insufficient funds to buy!");
            System.out.println("Purchase requires at least 3 gold.");
            return;
        }
        String type = in.getString((str) -> str.equals("Pet") || str.equals("Food"), "Choose [Pet | Food]: ");
        if(type.equals("Pet")) menuBuyPet();
        else if(type.equals("Food")) menuBuyFood();
    }

    private void menuBuyPet() {
        int opt = in.getInt((x) -> pets.get(x) != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 -" + shopStat.getPET_SLOT() + "]: ");
        int pos = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Slot [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]");
        if(pteam.getPetAtIdx(pos) != null) {
            System.out.println("That spot is filled!");
            return;
        }
        Pet pet = buyPet(opt);
        pteam.insertPetAt(pet, pos);
    }

    private Pet buyPet(int idx) {
        arena.incMoney(-ShopStat.BUY_PRICE);
        Pet temp = pets.get(idx);
        frozenPet.removeElementAt(idx);
        pets.removeElementAt(idx);
        return temp;
    }

    private void menuBuyFood() {
        int opt = in.getInt((x) -> fruits.get(x) != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 -" + shopStat.getPET_SLOT() + "]: ");
        int pos = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Feed [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]");
        if(pteam.getPetAtIdx(pos) == null) {
            System.out.println("You must feed a pet!");
            return;
        }
        Fruit fruit = buyFruit(opt);
        pteam.feedPetAt(fruit, pos);
    }

    private Fruit buyFruit(int idx) {
        arena.incMoney(-ShopStat.BUY_PRICE);
        Fruit temp = fruits.get(idx);
        frozenFruit.removeElementAt(idx);
        fruits.removeElementAt(idx);
        return temp;
    }

    private void menuFreeze() {
        System.out.println("Choose unfrozen item to freeze or frozen item to unfreeze.");
        String type = in.getString((str) -> str.equals("Pet") || str.equals("Food"), "Choose [Pet | Food]: ");
        if(type.equals("Pet")) menuFreezePet();
        else if(type.equals("Food")) menuFreezeFood();
    }

    private void menuFreezePet() {
        int opt = in.getIntInRange(1, shopStat.getPET_SLOT(), "Choose [1 - "+ shopStat.getPET_SLOT() + "]: ");
        if(frozenPet.get(opt) != null) {
            unfreeze(frozenPet.get(opt), opt);
        } else {
            freeze(pets.get(opt), opt);
        }
    }

    private void menuFreezeFood() {
        int opt = in.getIntInRange(1, shopStat.getFRUIT_SLOT(), "Choose [1 - "+ shopStat.getFRUIT_SLOT() + "]: ");
        if(frozenFruit.get(opt) != null) {
            unfreeze(frozenFruit.get(opt), opt);
        } else {
            freeze(fruits.get(opt), opt);
        }
    }

    private <T extends Entity> void freeze(T o, int idx) {
        if(o instanceof Pet) {
            frozenPet.insertElementAt(pets.get(idx), idx);
        } else if(o instanceof Fruit) {
            frozenFruit.insertElementAt(fruits.get(idx), idx);
        }
    }

    private <T extends Entity> void unfreeze(T o, int idx) {
        if(o instanceof Pet) {
            frozenPet.removeElementAt(idx);
        } else if(o instanceof Fruit) {
            frozenFruit.removeElementAt(idx);
        }
    }

    private void menuRoll() {
        arena.incMoney(-ShopStat.ROLL_PRICE);
        generateShop();
    }

    private void menuArrange() {
        int opt = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Choose [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        int target = in.getInt((x) -> x != opt && x >= Team.START_SIZE && x <= Team.END_SIZE, "Choose [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        pteam.swapPet(opt, target);
    }

    private void menuSell() {
        int opt = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Choose [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        if(pteam.getPet(opt) == null) {
            System.out.println("You can only sell a valid pet");
            return;
        }
        arena.incMoney(pteam.getTier(opt) * ShopStat.SELL_PRICE);
        pteam.removePet(opt);
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
            pets.insertElementAt(petFactory.getPet(shopStat.getTIER()), i);
        }
    }

    private void generateFruitShop() {
        for (int i = 0; i < shopStat.getFRUIT_SLOT(); i++) {
            if (frozenFruit.get(i) != null) {
                fruits.insertElementAt(frozenFruit.get(i), i);
                continue;
            }
            fruits.insertElementAt(fruitFactory.getFruit(shopStat.getTIER()), i);
        }
    }

    public void buffShop(int atk, int hp) {
        for(int i = 0; i < shopStat.getPET_SLOT(); i++) {
            if(frozenPet.get(i) != null) {
                frozenPet.get(i).buff(atk, hp);
                pets.insertElementAt(frozenPet.get(i), i);
            } else {
                pets.get(i).buff(atk, hp);
            }
        }
    }
}

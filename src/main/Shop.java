package main;

import console_input.ConsoleInput;
import constants.FruitFactory;
import constants.Lib;
import constants.PetFactory;
import constants.ShopStat;
import models.Fruit;
import models.Pet;
import models.Team;

import java.util.ArrayList;

public class Shop {
    private ShopStat shopStat;

    private ArrayList<ShopItem<Pet>> pets;
    private ArrayList<ShopItem<Fruit>> fruits;

    private PetFactory petFactory;
    private FruitFactory fruitFactory;

    private Arena arena;
    private Team pteam;
    private ConsoleInput in;

    public Shop(Arena arena, PetFactory petFactory, FruitFactory fruitFactory, Team pteam) {
        shopStat = ShopStat.TIER1;

        pets = new ArrayList<ShopItem<Pet>>();
        fruits = new ArrayList<ShopItem<Fruit>>();

        for(int i = 0; i < ShopStat.MAX_PET_SLOT; i++) pets.add(new ShopItem<>(null));
        for(int i = 0; i < ShopStat.MAX_FRUIT_SLOT; i++) fruits.add(new ShopItem<>(null));

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
        if(pteam.getPet(pos) != null) {
            System.out.println("That spot is filled!");
            return;
        }
        ShopItem<Pet> pet = buyShopItem(pets, opt);
        pteam.insertPetAt(pet.item, pos);
    }

    private void menuBuyFood() {
        int opt = in.getInt((x) -> fruits.get(x) != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 -" + shopStat.getPET_SLOT() + "]: ");
        int pos = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Feed [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]");
        if(pteam.getPet(pos) == null) {
            System.out.println("You must feed a pet!");
            return;
        }
        ShopItem<Fruit> fruit = buyShopItem(fruits, opt);
        pteam.feedPetAt(fruit.item, pos);
    }

    private <T> ShopItem<T> buyShopItem(ArrayList<ShopItem<T>> shopItems, int idx) {
        arena.incMoney(-ShopStat.BUY_PRICE);
        return shopItems.remove(idx);
    }

    private void menuFreeze() {
        System.out.println("Choose unfrozen item to freeze or frozen item to unfreeze.");
        String type = in.getString((str) -> str.equals("Pet") || str.equals("Food"), "Choose [Pet | Food]: ");
        if(type.equals("Pet")) menuFreezePet();
        else if(type.equals("Food")) menuFreezeFood();
    }

    private void menuFreezePet() {
        int opt = in.getIntInRange(1, shopStat.getPET_SLOT(), "Choose [1 - "+ shopStat.getPET_SLOT() + "]: ");
        freezeShopItem(pets, opt);
    }

    private void menuFreezeFood() {
        int opt = in.getIntInRange(1, shopStat.getFRUIT_SLOT(), "Choose [1 - "+ shopStat.getFRUIT_SLOT() + "]: ");
        freezeShopItem(fruits, opt);
    }

    private <T> void freezeShopItem(ArrayList<ShopItem<T>> shopItems, int idx) {
        if(shopItems.get(idx).state == ShopState.NORMAL) {
            shopItems.get(idx).state = ShopState.FROZEN;
        } else {
            shopItems.get(idx).state = ShopState.NORMAL;
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
        pteam.sellPet(opt);
    }

    public void showShop() {
        generateShop();
        printShop();
    }

    private void printShop() {
        System.out.println("Shop:");
        Lib.printSlots(pets.stream().map(o->o.item).toArray(Pet[]::new));
        Lib.printDivider();
        Lib.printFruits(fruits.stream().map(o->o.item).toArray(Fruit[]::new));
    }

    private void generateShop() {
        generatePetShop();
        generateFruitShop();
    }

    private void generatePetShop() {
        for (int i = 0; i < shopStat.getPET_SLOT(); i++) {
            if(pets.get(i).state == ShopState.FROZEN) continue;
            pets.set(i, new ShopItem<>(petFactory.getPet(shopStat.getTIER())));
        }
    }

    private void generateFruitShop() {
        for (int i = 0; i < shopStat.getFRUIT_SLOT(); i++) {
            if(fruits.get(i).state == ShopState.FROZEN) continue;
            fruits.set(i, new ShopItem<>(fruitFactory.getFruit(shopStat.getTIER())));
        }
    }

    public void buffShop(int atk, int hp) {
        for(int i = 0; i < shopStat.getPET_SLOT(); i++) {
            pets.get(i).item.buff(atk, hp);
        }
    }
}

enum ShopState {
    NORMAL,
    FROZEN;
}

class ShopItem<T> {
    public ShopState state;
    public T item;

    public ShopItem(T item) {
        this.state = ShopState.NORMAL;
        this.item = item;
    }
}
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
        generateShop();
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
            arena.printStats();
            pteam.printTeam();
            printShop();
            optShop();
            opt = in.getIntInRange(1, 6, ">> ");
            Lib.clear();

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
            Lib.clear();
        }
    }

    private void menuBuy() {
        printShop();
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
        int opt = in.getInt((x) -> pets.get(x) != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 - " + shopStat.getPET_SLOT() + "]: ");
        int pos = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Slot [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        opt--;
        pos--;
        if(pteam.getPet(pos) != null) {
            System.out.println("That spot is filled!");
            return;
        }
        ShopItem<Pet> pet = buyShopItem(pets, opt);
        pteam.insertPetAt(pet.item, pos);
    }

    private void menuBuyFood() {
        int opt = in.getInt((x) -> fruits.get(x) != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 - " + shopStat.getPET_SLOT() + "]: ");
        int pos = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Feed [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        opt--;
        pos--;
        if(pteam.getPet(pos) == null) {
            System.out.println("You must feed a pet!");
            return;
        }
        ShopItem<Fruit> fruit = buyShopItem(fruits, opt);
        pteam.feedPetAt(fruit.item, pos);
    }

    private <T> ShopItem<T> buyShopItem(ArrayList<ShopItem<T>> shopItems, int idx) {
        arena.incMoney(-ShopStat.BUY_PRICE);
        ShopItem<T> shopItem = shopItems.get(idx);
        shopItems.set(idx, new ShopItem<T>(null));
        return shopItem;
    }

    private void menuFreeze() {
        printShop();
        System.out.println("Choose unfrozen item to freeze or frozen item to unfreeze.");
        String type = in.getString((str) -> str.equals("Pet") || str.equals("Food"), "Choose [Pet | Food]: ");
        int opt;
        if(type.equals("Pet")) {
            opt = in.getIntInRange(1, shopStat.getPET_SLOT(), "Choose [1 - "+ shopStat.getPET_SLOT() + "]: ");
            freezeShopItem(pets, opt - 1);
        }
        else if(type.equals("Food")) {
            opt = in.getIntInRange(1, shopStat.getFRUIT_SLOT(), "Choose [1 - "+ shopStat.getFRUIT_SLOT() + "]: ");
            freezeShopItem(fruits, opt - 1);
        }
    }

    private <T> void freezeShopItem(ArrayList<ShopItem<T>> shopItems, int idx) {
        if(shopItems.get(idx).state == ShopState.NORMAL) {
            shopItems.get(idx).state = ShopState.FROZEN;
        } else {
            shopItems.get(idx).state = ShopState.NORMAL;
        }
    }

    private void menuRoll() {
        if(arena.getMoney() < ShopStat.ROLL_PRICE) {
            System.out.println("You have insufficient funds to roll!");
            in.enter();
            return;
        }
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

    private void printShop() {
        System.out.println("Shop:");
        printShopItem(pets, petPrinter, emptyPet);
        Lib.printDivider();
        printShopItem(fruits, fruitPrinter, emptyFruit);
    }

    ShopPrint petPrinter = new ShopPrint() {
        @Override
        public <T extends Entity> void print(ShopItem<T> obj, StringBuilder firstLn, StringBuilder secondLn, char open, char close) {
            if(!(obj.item instanceof Pet pet)) return;
            String first = String.format("%c %-5s %c", open, Lib.center(pet.getName(), 5), close);
            String second = String.format("%d | %d", pet.getAtk(), pet.getHp());
            int len = first.length() - 4;
            String secondFormat = open + " %-" + len + "s " + close;
            firstLn.append(first).append(" ");
            secondLn.append(String.format(secondFormat, Lib.center(second, len))).append(" ");
        }
    };

    EmptyPrint emptyPet = new EmptyPrint() {
        @Override
        public void print(StringBuilder firstLn, StringBuilder secondLn) {
            firstLn.append(String.format("[ %-5s ] ", ""));
            secondLn.append(String.format("[ %-5s ] ", ""));
        }
    };

    ShopPrint fruitPrinter = new ShopPrint() {
        @Override
        public <T extends Entity> void print(ShopItem<T> obj, StringBuilder firstLn, StringBuilder secondLn, char open, char close) {
            firstLn.append(String.format("%c %s %c ", open, obj.item.getName(), close));
        }
    };

    EmptyPrint emptyFruit = new EmptyPrint() {
        @Override
        public void print(StringBuilder firstLn, StringBuilder secondLn) {
            firstLn.append(String.format("[ %-5s ] ", ""));
        }
    };

    private <T extends Entity> void printShopItem(ArrayList<ShopItem<T>> items, ShopPrint shopPrint, EmptyPrint emptyPrint) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        for(ShopItem<T> o: items) {
            if(o == null) {
                emptyPrint.print(firstLn, secondLn);
                continue;
            }
            if(o.item == null) continue;
            char x, y;
            if(o.state == ShopState.FROZEN) {
                x = '{';
                y = '}';
            } else {
                x = '[';
                y = ']';
            }
            shopPrint.print(o, firstLn, secondLn, x, y);
        }
        System.out.println(firstLn);
        System.out.println(secondLn);
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

interface ShopPrint {
    public abstract <T extends Entity> void print(ShopItem<T> obj, StringBuilder firstLn, StringBuilder secondLn, char open, char close);
}

interface EmptyPrint {
    public abstract void print(StringBuilder firstLn, StringBuilder secondLn);
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
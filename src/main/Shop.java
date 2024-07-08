package main;

import console_input.ConsoleInput;
import constants.*;
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

        for(int i = 0; i < ShopStat.MAX_PET_SLOT; i++) pets.add(new ShopItem<>(ShopState.LOCKED, null));
        for(int i = 0; i < ShopStat.MAX_FRUIT_SLOT; i++) fruits.add(new ShopItem<>(ShopState.LOCKED, null));

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
        System.out.println("6. Battle");
        System.out.println("7. Exit");
    }

    private void menuShop() {
        boolean run = true;
        int opt = 0;
        boolean firstFlag = true;
        while (run) {
            arena.printStats();
            pteam.printTeam();
            if(firstFlag) {
                firstFlag = false;
                pteam.onTurnStart();
            }
            printShop();
            optShop();
            opt = in.getIntInRange(1, 7, ">> ");
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
                    pteam.saveTeam();
                    run = false;
                    pteam.onTurnEnd();
                    break;
                case 7:
                    arena.quit();
                    run = false;
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
        int opt = in.getInt((x) -> pets.get(x - 1).item != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 - " + shopStat.getPET_SLOT() + "]: ");
        int pos = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Slot [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        opt--;
        pos--;
        Pet curr = pets.get(opt).item;
        Pet target = pteam.getPet(pos);
        if(target != null && (target.getPetListName() != curr.getPetListName() || target.getLv() == Pet.MAX_LV)) {
            System.out.println("Fail to buy pet, make sure the spot is empty or the target pet is not max level.");
            in.enter();
            return;
        }
        ShopItem<Pet> pet = buyShopItem(pets, opt);
        if(target == null) pteam.boughtPet(pet.item, pos);
        else pteam.mergeBoughtPet(pet.item, pos);
    }

    private void menuBuyFood() {
        int opt = in.getInt((x) -> fruits.get(x - 1).item != null && x >= 1 && x <= shopStat.getPET_SLOT(), "Choose [1 - " + fruits.size() + "]: ");
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
        shopItems.set(idx, new ShopItem<T>(ShopState.NORMAL, null));
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
        if(shopItems.get(idx).item == null) {
            System.out.println("You can't freeze empty slot");
            in.enter();
            return;
        }
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
        pteam.printTeam();
        int opt = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Choose [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        int target = in.getInt((x) -> x != opt && x >= Team.START_SIZE && x <= Team.END_SIZE, "Choose [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        Pet curr = pteam.getPet(opt - 1);
        Pet targetPet = pteam.getPet(target - 1);
        if(curr.getPetListName() == targetPet.getPetListName()) {
            if(targetPet.getLv() == Pet.MAX_LV) {
                System.out.println("Can't merge pet!");
                System.out.println("The target pet is max level!");
            }
            pteam.mergePet(curr, target - 1);
            pteam.removePet(opt - 1);
        }
        else pteam.swapPet(opt - 1, target - 1);
    }

    private void menuSell() {
        int opt = in.getIntInRange(Team.START_SIZE, Team.END_SIZE, "Choose [" + Team.START_SIZE +" - "+ Team.END_SIZE + "]: ");
        opt--;
        if(pteam.getPet(opt) == null) {
            System.out.println("You can only sell a valid pet");
            return;
        }
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
            if(!(obj.item instanceof Pet)) return;
            Pet pet = (Pet) obj.item;
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
        for(ShopItem<T> o : items) {
            if(o.state == ShopState.LOCKED) continue;
            if(o.item == null) {
                emptyPrint.print(firstLn, secondLn);
                continue;
            }
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
        int tier = shopStat.getTIER();
        if(DevelopmentState.state.contains(DevelopmentState.CAP_SHOP_TIER))
            tier = Math.min(tier, 3);
        for (int i = 0; i < shopStat.getPET_SLOT(); i++) {
            if(pets.get(i).state == ShopState.FROZEN) continue;
            pets.set(i, new ShopItem<>(petFactory.getPet(tier)));
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
            Pet p = pets.get(i).item;
            if(p != null) p.buff(atk, hp);
        }
    }

    public void addFruit(Fruit fruit) {
        fruits.add(new ShopItem<>(ShopState.NORMAL, fruit));
    }
}

interface ShopPrint {
    public abstract <T extends Entity> void print(ShopItem<T> obj, StringBuilder firstLn, StringBuilder secondLn, char open, char close);
}

interface EmptyPrint {
    public abstract void print(StringBuilder firstLn, StringBuilder secondLn);
}

enum ShopState {
    LOCKED,
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

    public ShopItem(ShopState state, T item) {
        this.state = state;
        this.item = item;
    }
}
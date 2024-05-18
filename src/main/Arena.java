package main;

import constants.FruitFactory;
import constants.PetFactory;
import constants.ShopStat;
import models.Pet;
import models.Team;

import java.util.Vector;

public class Arena {
    private Team playerTeam;
    private Team enmTeam;
    private int round, win, life;
    private int money;
    private static Arena instance;

    private Shop shop;
    private PetFactory petFactory;
    private FruitFactory fruitFactory;

    private Arena() {
        playerTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 0;

        petFactory = new PetFactory(this);
        fruitFactory = new FruitFactory(this);
        shop = new Shop(this, this.petFactory, this.fruitFactory);
    }

    public static Arena getInstance() {
        if (instance == null) instance = new Arena();
        return instance;
    }

    public void play() {
        while (life > 0) {
            nextRound();
            shop();
            int res = battle();
            if (res == 0) {
                // draw
            } else if (res == -1) {
                // lose
                life--;
            } else if (res == 1) {
                // win
                win++;
            }
        }
    }

    public void shop() {
        printPTeam();
        shop.startShop();
    }

    private void nextRound() {
        round++;
        shop.nextRound(round);
    }

    public int battle() {
        return 0;
    }

    private void printPTeam() {
        System.out.println("Player Team:");
        playerTeam.printTeam();
    }

    public Team getPTeam() {
        return this.playerTeam;
    }

    private void getTeam() {
    }

    public void incMoney(int amt) {
        this.money += amt;
    }

    public Team getPlayerTeam() {
        return this.playerTeam;
    }

    public Team getEnmTeam() {
        return this.enmTeam;
    }

    public int getMoney() {
        return this.money;
    }
}

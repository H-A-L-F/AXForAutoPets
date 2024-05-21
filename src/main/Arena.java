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
    private PetFactory playerPetFactory;
    private FruitFactory fruitFactory;

    private Arena() {
        playerTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 10;

        playerPetFactory = new PetFactory(this, playerTeam, enmTeam);
        fruitFactory = new FruitFactory(this, playerTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.playerTeam);
    }

    public static Arena getInstance() {
        if (instance == null) instance = new Arena();
        return instance;
    }

    public void newGame() {
        reset();
        play();
    }

    private void reset() {
        playerTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 10;

        playerPetFactory = new PetFactory(this, playerTeam, enmTeam);
        fruitFactory = new FruitFactory(this, playerTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.playerTeam);
    }

    private void play() {
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

    private void shop() {
        shop.startShop();
    }

    private void nextRound() {
        round++;
        shop.nextRound(round);
    }

    private int battle() {
        return 0;
    }

    public void printStats() {
        System.out.printf("[Money: %d | Round: %d | Life %d | Win %d]\n", money, round, life, win);
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

    public Shop getShop() {
        return this.shop;
    }
}

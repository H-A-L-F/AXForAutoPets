package main;

import constants.FruitFactory;
import constants.PetFactory;
import models.Team;

public class Arena {
    private Team pTeam;
    private Team enmTeam;
    private int round, win, life;
    private int money;
    private static Arena instance;

    private Shop shop;
    private PetFactory playerPetFactory;
    private FruitFactory fruitFactory;

    private Arena() {
        pTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 10;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        fruitFactory = new FruitFactory(this, pTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.pTeam);
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
        pTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 10;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        fruitFactory = new FruitFactory(this, pTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.pTeam);
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
        while()
        return 0;
    }

    public void printStats() {
        System.out.printf("[Money: %d | Round: %d | Life %d | Win %d]\n", money, round, life, win);
    }

    public void incMoney(int amt) {
        this.money += amt;
    }

    public int getMoney() {
        return this.money;
    }

    public Shop getShop() {
        return this.shop;
    }
}

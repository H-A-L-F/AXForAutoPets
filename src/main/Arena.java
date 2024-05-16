package main;

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

    private ShopStat shopStat;
    private Shop shop;
    private PetFactory petFactory;

    private Arena() {
        playerTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 0;

        shopStat = ShopStat.TIER1;
        petFactory = new PetFactory(this);
        shop = new Shop(this.petFactory);
    }

    public static Arena getInstance() {
        if(instance == null) instance = new Arena();
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
}

package main;

import models.Pet;
import models.Team;

import java.util.Vector;

public class Arena {
    private Team pets;
    private Team enemy;
    private int round, win, life;
    private int money;
    private static Arena instance;

    private Arena() {
        pets = new Team();
        enemy = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 0;
    }

    public static Arena getInstance() {
        if(instance == null) instance = new Arena();
        return instance;
    }

    public void play() {
        while (life > 0) {
            round++;
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

    }

    public int battle() {
        return 0;
    }

    private void getTeam() {
    }

    public Team getEnemy() {
        return this.enemy;
    }

    public void incMoney(int amt) {
        this.money += amt;
    }
}

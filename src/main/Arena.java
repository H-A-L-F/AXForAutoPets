package main;

import models.Pet;

import java.util.Vector;

public class Arena {
    private Vector<Pet> pets, enemy;
    int round, win, life;

    public Arena() {
        pets = new Vector<>(5);
        enemy = new Vector<>(5);
        round = 0;
        win = 0;
        life = 5;
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
}

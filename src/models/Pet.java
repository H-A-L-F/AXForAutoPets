package models;

import constants.PetList;
import constants.PetStatus;
import interfaces.EventListener;

public abstract class Pet {
    private PetList name;
    private int atk;
    private int hp;
    private int lv;
    private PetStatus status;

    public Pet(PetList name, int atk, int hp) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
    }

    public void onPurchase() {
    }

    public void onFaint() {
    }

    public void buff(int atk, int hp) {
        this.atk += atk;
        this.hp += hp;
    }

    public int getLv() {
        return lv;
    }
}

package models;

import constants.PetList;
import constants.PetStatus;
import interfaces.EventListener;
import main.Arena;

public abstract class Pet {
    private PetList name;
    private int tier;
    private int atk;
    private int hp;
    private int lv;
    private PetStatus status;

    public Pet(PetList name, int tier, int atk, int hp) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
    }

    public void onPurchase() {
    }

    public void onSell() {
        Arena.getInstance().incMoney(this.lv);
    }

    public void onFaint() {
    }

    public void onLvUp() {
        this.lv++;
    }

    public void buff(int atk, int hp) {
        this.atk += atk;
        this.hp += hp;
    }

    public int getLv() {
        return lv;
    }
}

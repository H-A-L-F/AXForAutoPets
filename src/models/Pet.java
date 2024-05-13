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
        this.tier = tier;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
    }

    public Pet(PetList name, int tier, int lv, int atk, int hp) {
        this.name = name;
        this.tier = tier;
        this.lv = lv;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
    }

    public void onPurchase() {
    }

    public void onSell() {
        Arena.getInstance().incMoney(this.lv);
    }

    public void onFaint(int pos) {
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

    public void setStats(int atk, int hp) {
        this.atk = atk;
        this.hp = hp;
    }
}

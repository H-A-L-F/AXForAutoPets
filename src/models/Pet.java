package models;

import constants.PetList;
import constants.PetStatus;
import interfaces.EventListener;
import main.Arena;

public abstract class Pet extends Entity {
    private PetList name;
    private int atk;
    private int hp;
    private int lv;
    private PetStatus status;
    private Fruit fruit;

    public Pet(PetList name, int tier, int atk, int hp) {
        super(tier);
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
    }

    public Pet(PetList name, int tier, int lv, int atk, int hp) {
        super(tier);
        this.name = name;
        this.lv = lv;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
    }

    public void damage(int damage) {
        this.hp -= damage;
    }

    public void eatFruit(Fruit fruit) {
        this.fruit = fruit;
        this.fruit.onEaten(this);
    }

    public void onPurchase() {
    }

    public void onBattleStart() {
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

    @Override
    public String getName() {
        return this.name.toString();
    }

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }
}

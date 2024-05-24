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
    private int exp;
    private int pos;
    private PetStatus status;
    private Fruit fruit;

    private static int EXP_LV1 = 2;
    private static int EXP_LV2 = 3;

    public Pet(PetList name, int tier, int atk, int hp) {
        super(tier);
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
        this.exp = 0;
        this.pos = 0;
        this.status = PetStatus.NORMAL;
    }

    public int damage(int damage, int pos) {
        int dmg = fruit.onDamaged(damage);
        this.hp -= dmg;
        this.onDamaged();
        if(hp <= 0) onFaint(pos);
        return dmg;
    }

    public void eatFruit(Fruit fruit) {
        this.fruit = fruit;
        this.fruit.onEaten(this);
    }

    private void beforeAttack() {
    }

    public int attack() {
        beforeAttack();
        return this.atk;
    }

    public void onDamaged() {
    }

    public void onPurchase() {
    }

    public void onBattleStart() {
    }

    public void onSell() {
        Arena.getInstance().incMoney(this.lv);
    }

    public void onFaint(int pos) {
        status = PetStatus.FAINT;
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

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }

    public PetStatus getStatus() {
        return status;
    }
}

package models;

import constants.PetList;
import constants.PetStatus;
import interfaces.EventListener;
import interfaces.OnPlaced;
import main.Arena;

public abstract class Pet extends Entity implements OnPlaced {
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

    // region<On...>

    protected void onFaint() {
        status = PetStatus.FAINT;
    }

    protected void onSell() {
        Arena.getInstance().incMoney(getLv());
    }

    protected void onLevelUp() {
        lv++;
    }

    // endregion

    public int damage(int damage) {
        int dmg = fruit.onDamaged(damage);
        this.hp -= dmg;
        this.onDamaged();
        if(hp <= 0) onFaint();
        return dmg;
    }

    public void eatFruit(Fruit fruit) {
        this.fruit = fruit;
        this.fruit.onEaten(this);
    }

    public int attack() {
        return this.atk;
    }

    public void onDamaged() {
    }

    public void sell() {
        Arena.getInstance().incMoney(this.lv);
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

    public void setStats(int lv, int atk, int hp) {
        this.lv = lv;
        this.atk = atk;
        this.hp = hp;
    }

    public void setHp(int hp) {
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

    public int getPos() {
        return pos;
    }

    public PetStatus getStatus() {
        return status;
    }
}

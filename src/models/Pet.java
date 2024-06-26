package models;

import console_input.ConsoleInput;
import constants.PetList;
import constants.PetStatus;
import interfaces.EventListener;
import interfaces.OnPlaced;
import main.Arena;

public abstract class Pet extends Entity {
    private PetList name;
    private int atk;
    private int hp;
    private int lv;
    private int exp;
    private int currExpLimitIdx;
    private int pos;
    private PetStatus status;
    private Fruit fruit;

    private final int[] EXP_LIMITS = {2, 3, 0};
    public static int MAX_LV = 3;

    public Pet(PetList name, int tier, int atk, int hp) {
        super(tier);
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.lv = 1;
        this.currExpLimitIdx = 0;
        this.exp = 0;
        this.pos = 0;
        this.status = PetStatus.NORMAL;

    }

    public Pet(Pet p) {
        super(p.getTier());
        this.name = p.name;
        this.atk = p.atk;
        this.hp = p.hp;
        this.lv = p.lv;
        this.exp = p.exp;
        this.currExpLimitIdx = p.currExpLimitIdx;
        this.pos = p.pos;
        this.status = p.status;
        this.fruit = p.fruit;
    }

    // region<On...>

    protected void onFaint() {
        System.out.printf("%s fainted\n", getName());
        if(fruit != null) fruit.onFaint(this);
        status = PetStatus.FAINT;
    }

    protected void onSell() {
        System.out.printf("Sold %s for %d coins\n", getName(), getLv());
        Arena.getInstance().incMoney(getLv());
    }

    protected void onMerge() {
        exp++;
        atk++;
        hp++;
        if(exp == EXP_LIMITS[currExpLimitIdx]) onLevelUp();
    }

    protected void onLevelUp() {
        lv++;
        exp = 0;
        currExpLimitIdx++;
    }

    protected void onPurchased() {
    }

    protected void onHurt() {
    }

    public void onAttack(Pet enm) {
        System.out.printf("%s attacked %s for %d dmg\n", name, enm.getName(), atk);
        if(fruit != null) fruit.onAttack(this);
    }

    public void onAfterAttack() {
    }

    protected void onEatFruit() {
    }

    public void onBattleEnd() {
        if(fruit != null) fruit.onBattleEnd(this);
    }

    // endregion

    public int damage(int damage) {
        int dmg = 0;
        if(fruit!= null) dmg = fruit.onDamaged(damage);
        else dmg = damage;
        this.hp -= dmg;
        if(hp <= 0) onFaint();
        else onHurt();
        return dmg;
    }

    public void eatFruit(Fruit fruit) {
        onEatFruit();
        if(this.fruit != null) this.fruit.onEaten(this);
        ConsoleInput.getInstance().enter();
    }

    public void setFruit(Fruit fruit) {
        if(this.fruit != null) this.fruit.onReplaced(this);
        this.fruit = fruit;
    }

    public void removeFruit(Fruit fruit) {
        this.fruit = null;
    }

    public int attack() {
        return this.atk;
    }

    public void buff(int atk, int hp) {
        System.out.printf("%s is buffed for %d | %d\n", getName(), atk, hp);
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

    public void setStatsWPos(int atk, int hp, int pos) {
        this.atk = atk;
        this.hp = hp;
        this.pos = pos;
    }

    public void setStats(int lv, int atk, int hp) {
        this.lv = lv;
        this.atk = atk;
        this.hp = hp;
    }

    public void setStats(int atk, int hp, int lv, int exp, int pos) {
        this.atk = atk;
        this.hp = hp;
        this.lv = lv;
        this.exp = exp;
        this.pos = pos;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStatus(PetStatus petStatus) {
        this.status = petStatus;
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

    public int getExp() {
        return exp;
    }

    public int getExpLimit() {
        return EXP_LIMITS[currExpLimitIdx];
    }

    public Fruit getFruit() {
        return fruit;
    }

    public PetList getPetListName() {
        return name;
    }

    public PetStatus getStatus() {
        return status;
    }
}

package models;

import constants.Lib;
import interfaces.*;
import main.EventManager;

import java.util.ArrayList;

public class Team {
    private EventManager eventManager;
    private Pet[] pets;
    private int slot;

    private ArrayList<OnFaint> onFaints;
    private ArrayList<OnSell> onSells;
    private ArrayList<OnBattleStart> onBattleStarts;
    private ArrayList<OnLevelup> onLevelups;
    private ArrayList<OnPurchase> onPurchases;
    private ArrayList<OnSummon> onSummons;

    public static final int START_SIZE = 1;
    public static final int END_SIZE = 5;
    public static final int FRONT_INDEX = END_SIZE - 1;
    public static final int BACK_INDEX = START_SIZE - 1;

    public Team() {
        this.eventManager = new EventManager(this);
        pets = new Pet[END_SIZE];
        slot = END_SIZE;

        onFaints = new ArrayList<>();
        onSells = new ArrayList<>();
        onBattleStarts = new ArrayList<>();
        onLevelups = new ArrayList<>();
        onPurchases = new ArrayList<>();
        onSummons = new ArrayList<>();
    }

    public void printTeam() {
        Lib.printSlots(pets);
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void feedPetAt(Fruit fruit, int idx) {
        pets[idx].eatFruit(fruit);
    }

    public int getAtk(int idx) {
        return pets[idx].attack();
    }

    public int takeDamage(int damage, int idx) {
        return pets[idx].damage(damage, idx);
    }

    public void doAll(DoPet doPet) {
        for(int i = BACK_INDEX; i < END_SIZE; i++) {
            if(pets[i] == null) continue;
            doPet.doPet(pets[i]);
        }
    }

    public void doRandom(DoPet doPet) {
        doPet.doPet(getRandPet());
    }

    public void doBehind(int pos, int n, DoPet doPet) {
        for(int i = pos; i >= BACK_INDEX; i--) {
            if(pets[i] == null) continue;
            doPet.doPet(pets[i]);
        }
    }

    public void arrangeBattleTeam() {
        for (int i = FRONT_INDEX; i >= 0; i--) {
            if(pets[i] != null) continue;
            for(int j = i - 1; j >= 0; j--) {
                if(pets[j] == null) continue;
                pets[i] = pets[j];
                pets[j] = null;
                break;
            }
        }
    }

    private Pet getRandPet() {
        Pet res = null;
        while (res == null) {
            int idx = (int) (Math.random() * END_SIZE);
            res = pets[idx];
        }
        return res;
    }

    public int getRandIdx() {
        int idx = -1;
        do {
            idx = (int) (Math.random() * END_SIZE);
        } while(pets[idx] == null);
        return idx;
    }

    public void summonPet(Pet pet, int pos) {
        if(slot == 0) {
            failSpawn(pet);
            return;
        }
        if(pets[pos] == null) {
            pets[pos] = pet;
            return;
        }
        for(int i = FRONT_INDEX; i > pos; i--) {
            pets[i] = pets[i-1];
        }
        pets[pos] = pet;
    }

    public void insertPetAt(Pet pet, int pos) {
        pet.setPos(pos);
        pets[pos] = pet;
    }

    public void swapPet(int idx1, int idx2) {
        Pet temp = pets[idx1];
        pets[idx1] = pets[idx2];
        pets[idx2] = temp;
    }

    public void sellPet(int idx) {
        pets[idx].sell();
        pets[idx] = null;
    }

    public void addOnFaint(OnFaint onFaint) {
        onFaints.add(onFaint);
    }

    public void addOnSell(OnSell onSell) {
        onSells.add(onSell);
    }

    public void addOnLvUp(OnLevelup onLevelup) {
        onLevelups.add(onLevelup);
    }

    public void addOnBattleStart(OnBattleStart onBattleStart) {
        onBattleStarts.add(onBattleStart);
    }

    public void addOnPurchase(OnPurchase onPurchase) {
        onPurchases.add(onPurchase);
    }

    public void addOnSummon(OnSummon onSummon) {
        onSummons.add(onSummon);
    }

    public int getTier(int idx) {
        return pets[idx].getTier();
    }

    public Pet getPet(int idx) {
        return pets[idx];
    }

    private void failSpawn(Pet pet) {
        System.out.println("Failed to spawn pet");
    }

    public int getSlot() {
        return slot;
    }
}

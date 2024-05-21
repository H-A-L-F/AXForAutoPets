package models;

import constants.Lib;
import main.EventManager;

public class Team {
    private EventManager eventManager;
    private Pet[] pets;
    public static final int START_SIZE = 1;
    public static final int END_SIZE = 5;
    private int slot;

    public Team() {
        this.eventManager = new EventManager(this);
        pets = new Pet[END_SIZE];
        slot = END_SIZE;
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

    public void arrangeBattleTeam() {
        for (int i = END_SIZE - 1; i >= 0; i--) {
            if(pets[i] != null) continue;
            for(int j = i - 1; j >= 0; j--) {
                if(pets[j] == null) continue;
                pets[i] = pets[j];
                pets[j] = null;
            }
        }
    }

    public Pet getRandPet() {
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

    public void addPet(Pet pet, int pos) {
        if(slot == 0) {
            failSpawn(pet);
            return;
        }
        if(pets[pos] == null) {
            pets[pos] = pet;
            return;
        }
        for(int i = END_SIZE - 1; i > pos; i--) {
            pets[i] = pets[i-1];
        }
        pets[pos] = pet;
    }

    public void insertPetAt(Pet pet, int pos) {
        pets[pos] = pet;
    }

    public void swapPet(int idx1, int idx2) {
        Pet temp = pets[idx1];
        pets[idx1] = pets[idx2];
        pets[idx2] = temp;
    }

    public void sellPet(int idx) {
        pets[idx].onSell();
        pets[idx] = null;
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

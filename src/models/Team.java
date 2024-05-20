package models;

import constants.Lib;
import main.EventManager;

import java.util.Vector;

public class Team {
    private EventManager eventManager;
    private Vector<Pet> pets;
    public static final int START_SIZE = 1;
    public static final int END_SIZE = 5;
    private int slot;

    public Team() {
        this.eventManager = new EventManager(this);
        pets = new Vector<>(END_SIZE);
        slot = END_SIZE;
    }

    public void printTeam() {
        Lib.printSlots(pets);
    }

    public void roundStart() {
    }

    public void roundEnd() {
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public Vector<Pet> getPets() {
        return this.pets;
    }

    public void feedPetAt(Fruit fruit, int idx) {
        pets.get(idx).eatFruit(fruit);
    }

    public Pet getRandPet() {
        int idx = (int) (Math.random() * pets.size());
        return pets.get(idx);
    }

    public void addPet(Pet pet, int pos) {
        if(slot == 0) {
            failSpawn(pet);
            return;
        }
        if(pets.get(pos) == null) {
            pets.insertElementAt(pet, pos);
            return;
        }
        for(int i = END_SIZE - 1; i > pos; i--) {
            pets.insertElementAt(pets.get(i - 1), i);
        }
        pets.insertElementAt(pet, pos);
    }

    public void insertPetAt(Pet pet, int pos) {
        pets.insertElementAt(pet, pos);
    }

    public void swapPet(int idx1, int idx2) {
        Pet temp = pets.get(idx1);
        pets.set(idx1, pets.get(idx2));
        pets.set(idx2, temp);
    }

    public void removePet(int idx) {
        pets.get(idx).onSell();
        pets.removeElementAt(idx);
    }

    public int getTier(int idx) {
        return pets.get(idx).getTier();
    }

    public Pet getPet(int idx) {
        return pets.get(idx);
    }

    private void failSpawn(Pet pet) {
        System.out.println("Failed to spawn pet");
    }

    public Pet getPetAtIdx(int idx) {
        return this.pets.get(idx);
    }

    public int getSlot() {
        return slot;
    }
}

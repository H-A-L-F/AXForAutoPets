package models;

import constants.Lib;
import main.EventManager;

import java.util.Vector;

public class Team {
    private EventManager eventManager;
    private Vector<Pet> pets;
    private final int SIZE = 5;

    public Team() {
        this.eventManager = new EventManager(this);
        pets = new Vector<>(SIZE);
    }

    public void printTeam() {
        for (int i = 0; i < SIZE; i++) Lib.printSlot(pets.get(i));
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

    public Pet getRandPet() {
        int idx = (int) (Math.random() * pets.size());
        return pets.get(idx);
    }

    public void addPet(Pet pet, int pos) {
        pets.insertElementAt(pet, pos);
    }
}

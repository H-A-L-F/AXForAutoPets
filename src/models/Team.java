package models;

import main.EventManager;

import java.util.Vector;

public class Team {
    private EventManager eventManager;
    private Vector<Pet> pets;

    public Team() {
        this.eventManager = new EventManager(this);
        pets = new Vector<>(5);
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

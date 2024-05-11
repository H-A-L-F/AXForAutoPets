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

    
}
